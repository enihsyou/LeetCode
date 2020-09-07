# coding=utf-8
# promote user for language used to implement solution.
from dataclasses import dataclass
from typing import List

from prompt_toolkit import Application
from prompt_toolkit.formatted_text import StyleAndTextTuples
from prompt_toolkit.key_binding import KeyBindings
from prompt_toolkit.layout import *
from prompt_toolkit.styles import Style

from prompts import AskSession


async def question(session: AskSession):
    """
    Ask user for his answer on which language he whats to
    use in order to complete the LeetCode question.
    """

    choices = _get_languages_for_code_snippets(session)

    ic = InquirerControl(choices, default='Java')

    return await Application(
        layout=application_layout(ic),
        key_bindings=application_keybinding(ic),
        style=application_style(),
    ).run_async()


def application_layout(ic: 'InquirerControl'):
    def get_prompt_tokens():
        tokens = [('class:questionmark', '?'),
                  ('class:question',
                   ' %s ' % 'Using which language to complete the question.')]

        if ic.answered:
            tokens.append(('class:answer', ' ' + ic.get_selection().name))
        else:
            tokens.append(('class:instruction', ' (Use arrow keys)'))
        return tokens

    root_container = HSplit([
        Window(FormattedTextControl(get_prompt_tokens), height=D.exact(1)),
        Window(ic)
    ])

    return Layout(root_container)


def application_keybinding(ic: 'InquirerControl'):
    kb = KeyBindings()

    @kb.add('c-q', eager=True)
    @kb.add('c-c', eager=True)
    def escape(_):
        raise KeyboardInterrupt()

    @kb.add('up', eager=True)
    def move_cursor_up(_):
        ic.selected_option_index = (
                (ic.selected_option_index - 1) % ic.choice_count)

    @kb.add('down', eager=True)
    def move_cursor_down(_):
        ic.selected_option_index = (
                (ic.selected_option_index + 1) % ic.choice_count)

    @kb.add('enter', eager=True)
    def set_answer(event):
        ic.answered = True
        event.app.exit(result=ic.get_selection().value)

    return kb


def application_style():
    return Style.from_dict({
        'questionmark': '#5F819D',
        'question'    : 'bold',
        'selected'    : '',  # default
        'pointer'     : '#FF9D00 bold',  # AWS orange
        'instruction' : '',  # default
        'answer'      : '#FF9D00 bold',  # AWS orange
    })


class InquirerControl(FormattedTextControl):
    def __init__(self, choices, default, **kwargs):
        self.selected_option_index = 0
        self.answered = False
        self.choices = choices
        self._init_choices(choices, default)
        super().__init__(self._get_choice_tokens, **kwargs)

    def _init_choices(self, choices, default):
        # helper to convert from question format to internal format
        @dataclass
        class Choice:
            name: str
            value: object
            chosen: bool = False

        self.choices: List[Choice] = []

        searching_first_choice = True
        for i, c in enumerate(choices):
            if isinstance(c, str):
                self.choices.append(Choice(c, c))
            else:
                name = c.get('name')
                value = c.get('value', name)
                self.choices.append(Choice(name, value))
            if self.choices[-1].value == default:
                self.selected_option_index = i
                searching_first_choice = False
            if searching_first_choice:
                self.selected_option_index = i  # found the first choice
                searching_first_choice = False
            if default and (default == i or default == c):
                self.selected_option_index = i  # default choice exists
                searching_first_choice = False

    @property
    def choice_count(self):
        return len(self.choices)

    def _get_choice_tokens(self) -> StyleAndTextTuples:
        tokens: StyleAndTextTuples = []

        def append():
            selected = (index == self.selected_option_index)

            if selected:
                tokens.extend((
                    ('class:pointer', ' \u276f '),
                    ('[SetCursorPosition]', ''),
                    ('class:selected', choice.name),
                    ('', '\n')))
            else:
                tokens.extend((
                    ('class:pointer', '   '),
                    ('', choice.name),
                    ('', '\n')))

        # prepare the select choices
        for index, choice in enumerate(self.choices):
            append()
        tokens.pop()  # Remove last newline.
        return tokens

    def get_selection(self):
        return self.choices[self.selected_option_index]


def _get_languages_for_code_snippets(session: AskSession):
    metadata = session.metadata

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
