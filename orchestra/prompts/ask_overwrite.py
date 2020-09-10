# coding=utf-8
# promote user for question ordinal.

from prompt_toolkit.formatted_text import StyleAndTextTuples
from prompt_toolkit.styles import Style

from prompts import AskSession


async def question(session: AskSession):
    """
    Ask user for his answer on which LeetCode problem
    he whats to anticipate.
    """
    file_path = session.metadata["output_path"]

    # TODO: replace with prompt session
    message: StyleAndTextTuples = [
        ('', 'Solution '),
        ('class:file_path', file_path),
        ('', ' exists, overwrite? ')
    ]

    styles: Style = Style.from_dict({
        'file_path': '#FF9D00 bold',
    })

    answer = await session.prompt(
        message=message, style=styles
    )

    return answer and answer.lower() in ('y', 'yes')
