# coding=utf-8
import sys
import re

from prompts import AskSession, ask_overwrite
import inspect


async def assemble(session: AskSession):
    metadata = session.metadata

    code_snippet = [o for o in metadata['codeSnippets']
                    if o['lang'] == session.answer_for_language][0]

    lang = code_snippet['lang']
    lang_code = code_snippet['code']

    title = metadata['title']
    question_id = metadata['questionId']

    ext_mapping = {
        'C++'       : 'cpp',
        'Java'      : 'java',
        'Python'    : 'py',
        'C'         : 'c',
        'JavaScript': 'js',
        'Ruby'      : 'ruby',
        'Swift'     : 'swift',
        'Go'        : 'go',
        'Scala'     : 'scala',
        'Kotlin'    : 'kt',
        'PHP'       : 'php',
    }

    file_folder = f"solution/#{question_id} {title}"
    file_ext = ext_mapping[lang]

    # 调用enhance函数组，修改源代码
    enhancers = [obj for (name, obj) in
                 inspect.getmembers(sys.modules[__name__])
                 if (inspect.isfunction(obj) and
                     obj.__module__ == __name__ and
                     name == "enhance_" + file_ext)]
    for enhancer in enhancers:
        lang_code = enhancer(session, lang_code)

    if (basename := session.metadata.get('file_basename')) is not None:
        file_name = f"{basename}.{file_ext}"
    else:
        file_name = f"Solution.{file_ext}"

    import os

    os.makedirs(folder_path := os.path.join('../', file_folder),
                exist_ok=True)
    if os.path.isfile(file_path := os.path.join(folder_path, file_name)):
        session.metadata["output_path"] = os.path.abspath(file_path)
        overwrite = await ask_overwrite.question(session)
        if not overwrite:
            exit(0)

    with open(file_path, 'w') as writer:
        writer.write(lang_code)

    import webbrowser
    import urllib.parse

    webbrowser.open("idea://open?file=" +
                    urllib.parse.quote(os.path.abspath(file_path)))


def enhance_java(session: AskSession, code_snippet: str):
    question_id = session.metadata['questionId']
    question_frontend_id = session.metadata['questionFrontendId']
    # questionId can be differed with questionFrontendId,
    # such as find-common-characters

    question_title = session.metadata['title']
    question_url = session.metadata["siteUrlPrefix"] + \
                   session.metadata['questionDetailUrl']

    code_snippet = code_snippet[:code_snippet.rindex('}')]
    if (match := re.search(r'^class (.+?)\s?[{]', code_snippet)) is not None:
        class_name = match.group(1)
        session.metadata['file_basename'] = class_name
    else:
        class_name = 'Solution'  # fallback

    def indent(size, string):
        import inspect
        return '\n'.join([
            " " * size + line if line else ''
            for line in inspect.cleandoc(string).splitlines()])

    statements = (
        f"package leetcode.q{question_id}.java;",
        "",
        indent(0, """
        import java.util.stream.Stream;

        import leetcode.base.java.JavaTest;
        import org.junit.jupiter.params.provider.Arguments;"""),
        "",
        indent(0, f"""
        /**
         * <a href="{question_url}">
         *     {question_frontend_id}. {question_title}
         * </a>
         */"""),
        code_snippet,
        indent(4, f"""
        static class SolutionTest extends JavaTest<{class_name}> {{

            @Override
            protected Stream<Arguments> provider() {{
                return Stream.of(
                    Arguments.of()
                );
            }}
        }}"""),
        "}"
    )

    return '\n'.join(statements) + '\n'
