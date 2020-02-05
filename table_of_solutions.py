#!/usr/bin/env python3
# -*- coding: UTF-8 -*-

# 生成用于README.md文件的解法文件目录
# 通过扫描src的子文件夹，解析文件名，生成Markdown规范的文件

import datetime
import enum
import os
import re
from typing import Dict, Iterable, List


class Language(enum.Enum):
    Java = enum.auto()
    Kotlin = enum.auto()
    MySQL = enum.auto()
    Bash = enum.auto()


class Solution:
    problem_no: int
    category: Language
    solution: str
    last_upd: datetime.datetime

    def __init__(self, metadata):
        (
            self.problem_no,
            self.category,
            self.solution,
            self.last_upd
        ) = metadata

    def __str__(self) -> str:
        return f"No.{self.problem_no}: [{self.category}] {self.solution} @ {self.last_upd}"

    def __repr__(self) -> str:
        return self.__str__()


class Problem:
    ordinal: int
    problem: str
    solutions: List[Solution]

    def __init__(self, metadata):
        (
            self.ordinal,
            self.problem,
        ) = metadata
        self.solutions = []

    def __eq__(self, o: object) -> bool:
        if not isinstance(o, self.__class__):
            return False
        return self.ordinal == o.ordinal

    def __hash__(self) -> int:
        return self.ordinal

    def __str__(self) -> str:
        return f"No.{self.ordinal}: {self.problem} ({len(self.solutions)})"

    def __repr__(self) -> str:
        return self.__str__()

    def __lt__(self, o: object) -> bool:
        if not isinstance(o, Problem):
            return False
        return self.ordinal < o.ordinal


def scan_for_solutions():
    solutions: Dict[int, Problem] = {}

    def scan_for_solutions_internal(root_path, what_todo):
        """
        :type root_path: str
        :type what_todo: (os.DirEntry) -> None
        """
        with os.scandir(root_path) as scanner:  # type: Iterable[os.DirEntry]
            for entry in scanner:
                if not entry.name.startswith('.'):
                    what_todo(entry)

    def scan_language_dir(entry: os.DirEntry):
        if not entry.is_dir():
            return
        scan_for_solutions_internal(entry.path, scan_solution_file)

    def scan_solution_file(entry: os.DirEntry):
        if not entry.is_file():
            return

        if search := re.search(r"#(\d+) (.+)\.", entry.name):
            ordinal = int(search.group(1))
            problem = search.group(2)
        else:
            return
        category = os.path.basename(os.path.dirname(entry.path))
        filepath = entry.path
        last_upd = datetime.datetime.utcfromtimestamp(entry.stat().st_mtime)

        if ordinal not in solutions:
            solutions[ordinal] = Problem((ordinal, problem,))

        solution = Solution((ordinal, category, filepath, last_upd))
        solutions[ordinal].solutions.append(solution)

    scan_for_solutions_internal('src/main', scan_language_dir)
    return sorted(solutions.values())


a = scan_for_solutions()
print(a)
