# coding=utf-8

from prompt_toolkit import PromptSession
from prompt_toolkit.formatted_text import AnyFormattedText
from prompt_toolkit.styles import DummyStyle, Style
from prompt_toolkit.validation import DummyValidator, Validator


class AskSession:
    """Global session object"""

    def __init__(self):
        self._prompt_session = PromptSession()
        self._answers = [None, None]
        self._metadata = None

    async def prompt(self, message: AnyFormattedText, *,
                     validator: Validator = DummyValidator(),
                     style: Style = DummyStyle()):
        return await self._prompt_session.prompt_async(
            message, validator=validator, style=style)

    @property
    def answer_for_ordinal(self):
        return self._answers[0]

    @answer_for_ordinal.setter
    def answer_for_ordinal(self, value):
        self._answers[0] = value

    @property
    def answer_for_language(self):
        return self._answers[1]

    @answer_for_language.setter
    def answer_for_language(self, value):
        self._answers[1] = value

    @property
    def metadata(self):
        if not self._metadata:
            raise ValueError("no metadata provided.")
        return self._metadata

    @metadata.setter
    def metadata(self, value):
        if not value:
            raise ValueError("no metadata provided.")
        self._metadata = value

    async def __aenter__(self):
        return self

    async def __aexit__(self, exc_type, exc_val, exc_tb):
        import prompts.assemble_file
        await prompts.assemble_file.assemble(self)
