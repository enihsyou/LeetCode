# coding=utf-8
import sys

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
    file_name = "Solution." + file_ext

    import os

    os.makedirs(folder_path := os.path.join('../', file_folder),
                exist_ok=True)
    if os.path.isfile(file_path := os.path.join(folder_path, file_name)):
        session.metadata["output_path"] = os.path.abspath(file_path)
        overwrite = await ask_overwrite.question(session)
        if not overwrite:
            exit(0)

    with open(file_path, 'w') as writer:
        enhancers = [obj for (name, obj) in
                     inspect.getmembers(sys.modules[__name__])
                     if (inspect.isfunction(obj) and
                         obj.__module__ == __name__ and
                         name == "enhance_" + file_ext)]
        for enhancer in enhancers:
            lang_code = enhancer(session, lang_code)
        writer.write(lang_code)


def enhance_java(session: AskSession, code_snippet: str):
    question_id = session.metadata['questionId']

    statements = (
        f"package leetcode.q{question_id}.java;",
        "",
        code_snippet,
        "",
    )

    return '\n'.join(statements)
