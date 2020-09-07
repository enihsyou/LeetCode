# coding=utf-8
# promote user for question ordinal.
import re
import urllib.parse

from prompt_toolkit.document import Document
from prompt_toolkit.validation import ValidationError, Validator

from prompts import AskSession
from prompts.leetcode import fetch_problem_graphql


async def question(session: AskSession):
    """
    Ask user for his answer on which LeetCode problem
    he whats to anticipate.
    """
    file_path = session.metadata["output_path"]

    answer = await session.prompt(
        message=f"Solution '{file_path}' exists, overwrite? "
    )

    return answer and answer.lower() in ('y', 'yes')
