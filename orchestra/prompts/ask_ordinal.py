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

    return await session.prompt(
        message="Enter the problem URL from LeetCode site: ",
        validator=LeetCodeUrlValidator(session)
    )


class LeetCodeUrlValidator(Validator):

    def __init__(self, session: AskSession):
        self.session = session

    def validate(self, document: Document) -> None:
        raise AssertionError("should use async version of validate function.")

    async def validate_async(self, document: Document) -> None:
        (_, hostname, path, _, _, _) = urllib.parse.urlparse(
            url=document.text)

        if 'leetcode' not in hostname.lower():
            raise ValidationError(
                message='Please enter a URL points to LeetCode site.')

        if (match := re.search(r'/problems/([^/]+)/?', path)) is None:
            raise ValidationError(
                message='Please enter a valid URL of LeetCode problem')

        title_slug = match.group(1)

        if (prob := await fetch_problem_graphql(hostname, title_slug)) is None:
            raise ValidationError(
                message=f'Cannot fetch metadata for problem "{title_slug}"')
        else:
            self.session.metadata = prob
            prob["siteUrlPrefix"] = "https://" + hostname
