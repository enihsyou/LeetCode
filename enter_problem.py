#!/usr/bin/env python3.8
# coding=utf-8

# 初始化新的题解
import re

import requests
from PyInquirer import Token, prompt, style_from_dict
from prompt_toolkit.validation import ValidationError, Validator

custom_style_1 = style_from_dict({
    Token.Separator   : '#cc5454',
    Token.QuestionMark: '#673ab7 bold',
    Token.Selected    : '#cc5454',  # default
    Token.Pointer     : '#673ab7 bold',
    Token.Instruction : '',  # default
    Token.Answer      : '#f44336 bold',
    Token.Question    : '',
})

metadata = {}


class LeetCodeUrlValidator(Validator):
    def validate(self, document):
        """
        :type document: prompt_toolkit.document.Document
        """
        (_, hostname, path, _, _, _) = requests.utils.urlparse(
            url=document.text)

        if 'leetcode' not in hostname.lower():
            raise ValidationError(
                message='Please enter a valid LeetCode site URL.')

        if (match := re.search(r'/problems/([^/]+)/?', path)) is None:
            raise ValidationError(
                message='Please enter a valid URL of LeetCode problem')

        title_slug = match.group(1)

        if (prob := fetch_problem_graphql(hostname, title_slug)) is None:
            raise ValidationError(
                message=f'Cannot fetch metadata for problem "{title_slug}"')
        else:
            global metadata
            metadata = prob


def fetch_problem_graphql(hostname, title_slug):
    """
    Fetch problem properties via LeetCode GraphQL endpoint.
    :param hostname: hostname of LeetCode site
    :type hostname: str
    :param title_slug: Problem name in lower snake_case.
    :type title_slug: str
    """
    req_json = {
        "operationName": "questionData",
        "variables"    : {
            "titleSlug": title_slug
        },
        "query"        : "query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    questionId\n    titleSlug\n    title\n    translatedTitle\n    difficulty\n    codeSnippets {\n      lang\n      langSlug\n      code\n    }\n  }\n}\n"
    }
    resp = requests.get(f"https://{hostname}/graphql/",
                        json=req_json, timeout=1)

    json = resp.json() or {}
    data = json.get('data') or {}

    return data.get('question')


def get_category_options(_):
    code_snippets = metadata['codeSnippets']
    options = [it['lang'] for it in code_snippets]

    if 'Python' in options and 'Python3' in options:
        # 用Python3替换Python2
        python2 = [s for s in code_snippets if s['lang'] == 'Python'][0]
        python3 = [s for s in code_snippets if s['lang'] == 'Python3'][0]

        python2['code'] = python3['code']
        code_snippets.remove(python3)
        options.remove('Python3')

    return options


def finish_question(answers):
    code_snippet = [o for o in metadata['codeSnippets']
                    if o['lang'] == answers['category']][0]

    lang = code_snippet['lang']
    lang_slug = code_snippet['langSlug']
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

    file_folder = lang_slug
    file_name = f"#{question_id} {title}.{ext_mapping[lang]}"

    import os

    os.makedirs(folder_path := os.path.join('src/main/', file_folder),
                exist_ok=True)
    if os.path.isfile(file_path := os.path.join(folder_path, file_name)):
        overwrite = prompt({
            'type'   : 'confirm',
            'message': f'Solution {file_path} exists, overwrite?',
            'name'   : 'overwrite',
            'default': False,
        })['overwrite']
        if not overwrite:
            exit(0)

    with open(file_path, 'w') as writer:
        writer.write(lang_code)

    import table_of_solutions
    table_of_solutions.main()


questions = [
    {
        'type'    : 'input',
        'name'    : 'problem',
        'message' : 'Enter problem URL from LeetCode site.',
        'validate': LeetCodeUrlValidator
    },
    {
        'type'   : 'list',
        'name'   : 'category',
        'message': 'What language do you wish to use?',
        'choices': get_category_options
    },
]

if __name__ == '__main__':
    """
    WARNING: 
        There is bug on mouse support. Answers won't update its content 
        when click on options.
    """
    finish_question(
        prompt(questions, style=custom_style_1))
