# coding=utf-8
import asyncio

import prompts
from prompts import ask_ordinal, ask_language


async def main():
    async with prompts.AskSession() as session:
        session.answer_for_ordinal = await \
            prompts.ask_ordinal.question(session)
        session.answer_for_language = await \
            prompts.ask_language.question(session)


if __name__ == '__main__':
    asyncio.get_event_loop().run_until_complete(main())
