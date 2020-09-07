# coding=utf-8
from typing import Optional

import requests


async def fetch_problem_graphql(hostname, title_slug) -> Optional:
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
