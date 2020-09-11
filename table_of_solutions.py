#!/usr/bin/env python3.8
# -*- coding: UTF-8 -*-

# 生成用于README.md文件的解法文件目录
# 通过扫描src的子文件夹，解析文件名，生成Markdown规范的文件

import datetime
import enum
import hashlib
import re
from typing import *

import git


class Language(enum.Enum):
    """ 代表解法使用的编程语言 """
    Kotlin = enum.auto()
    Java = enum.auto()
    Python = enum.auto()
    MySQL = enum.auto()
    Bash = enum.auto()


class Solution:
    """ 代表一道题目的一个解法

    :type problem_no: int
    :type category: Language
    :type solution: str
    :type last_upd: datetime.datetime
    """

    def __init__(self, metadata):
        self.problem_no = metadata[0]
        """ 所解的题目 """
        self.category = metadata[1]
        """ 使用语言 """
        self.solution = metadata[2]
        """ 文件在项目中的相对路径 """
        self.last_upd = metadata[3]
        """ 文件最后更新时间 """

    def __repr__(self) -> str:
        return f"No.{self.problem_no}: [{self.category}] {self.solution} @ {self.last_upd}"


class Problem:
    """ 代表一道LeetCode题目

    :type ordinal: int
    :type problem: str
    :type solutions: list of Solution
    """

    def __init__(self, metadata):
        self.ordinal = metadata[0]
        """ 序号 """
        self.problem = metadata[1]
        """ 题目名字 """
        self.solutions = []
        """ 已实现的解法 """

    def __eq__(self, o: object) -> bool:
        if not isinstance(o, self.__class__):
            return False
        return self.ordinal == o.ordinal

    def __hash__(self) -> int:
        return self.ordinal

    def __repr__(self) -> str:
        return f"No.{self.ordinal}: {self.problem} ({len(self.solutions)})"

    def __lt__(self, o: object) -> bool:
        if not isinstance(o, Problem):
            return False
        return self.ordinal < o.ordinal


def scan_for_problems():
    solutions: Dict[int, Problem] = {}

    repo = git.Repo('.')

    def scan_for_solutions_internal(root_tree, what_todo):
        """
        :type root_tree: git.Tree
        :type what_todo: (git.Blob) -> None
        """
        for blob in root_tree:
            if not blob.name.startswith('.'):
                what_todo(blob)

    def scan_language_dir(tree):
        """
        :type tree: git.Blob | git.Tree
        """
        if not isinstance(tree, git.Tree):
            return

        if search := re.search(r"#(\d+) (.+)", tree.name):
            ordinal = int(search.group(1))
            problem = search.group(2)
        else:
            return

        if ordinal not in solutions:
            problem = solutions[ordinal] = Problem((ordinal, problem,))

            scan_for_solutions_internal(
                tree, lambda p: scan_solution_file(problem, p))

    def scan_solution_file(problem, blob):
        """
        :type problem: Problem
        :type blob: git.Blob | git.Tree
        """
        if not isinstance(blob, git.Blob):
            return

        if not blob.name.startswith('Solution'):
            return

        commit = next(repo.iter_commits(paths=blob.path, max_count=1))
        category = resolve_language(blob.name)
        filepath = blob.path
        last_upd = commit.authored_datetime

        solution = Solution((problem.ordinal, category, filepath, last_upd))
        problem.solutions.append(solution)

    def resolve_language(file_name: str):
        return next(iter([v for k, v in {
            '.java'     : Language.Java,
            '.kt'       : Language.Kotlin,
            '.py'       : Language.MySQL,
            '.bash.sh'  : Language.Bash,
            '.mysql.sql': Language.MySQL,
        }.items() if file_name.endswith(k)]), None)

    scan_for_solutions_internal(repo.tree() / 'solution', scan_language_dir)
    return sorted(solutions.values())


class MarkdownTableGenerator:
    """
    Markdown Table Generator

    :type table: MarkdownTableGenerator.ElasticTable
    :type links: list of MarkdownTableGenerator.MarkdownLink
    """

    def __init__(self, problems: Iterable[Problem]):
        self.table = self.ElasticTable(
            ("No.", "Name", "Solutions", "Last Update"))
        self.links = list()

        self.pad_column = True
        """ 在元素左右两边添加一个空格 """

        for problem in problems:
            def for_solution(s: Solution):
                link = self.MarkdownLink(
                    solution=s,
                    text=s.category.name,
                    label=f"#{s.problem_no} {s.category.name.lower()}",
                    href=s.solution, )
                self.links.append(link)
                return link.render_in_table()

            # some string conversions.
            self.table.add_row((
                str(problem.ordinal),
                problem.problem,
                "<br/>".join(map(for_solution, problem.solutions)),
                max([s.last_upd for s in problem.solutions]).strftime(
                    "%Y-%m-%d %H:%M"),
            ))

    class ElasticTable:
        """ 可自适应宽度的表格

        :type header: tuple[str]
        :type column: int
        :type widths: tuple[int]
        :type bodies: list[tuple[str]]
        """

        def __init__(self, header):
            self.header = header
            """ 表格标题 """
            self.column = len(header)
            """ 列数 """
            self.widths = tuple(len(s) for s in self.header)
            """ 各列的宽度 """
            self.bodies = list()
            """ 表格的内容 不包括标题、分隔行 """

        def add_row(self, row: Tuple[str, ...]):
            assert len(row) == self.column
            width_calculator = len
            self.widths = tuple(
                max(old, width_calculator(new)) for old, new in
                zip(self.widths, row))
            self.bodies.append(row)

        def __repr__(self) -> str:
            return f"{dict(zip(self.header, self.widths))}({len(self.bodies)})"

    class MarkdownLink:
        """ Markdown Link reference ::

            [text][label]
            [label]: destination

        https://github.github.com/gfm/#link-reference-definition

        :type solution: Solution
        :type text: str
        :type label: str
        :type destination: str
        """

        def __init__(self, solution, *, text, label, href):
            self.solution = solution
            """ 指向的解法对象 """
            self.text = text
            """ 可见文字 """
            self.label = label
            """ 内部标志 """
            self.destination = href
            """ 目标地址 """

        def render_in_table(self):
            return f"[{self.text}][{self.label}]"

        def render_in_footer(self):
            import urllib.parse
            return f"[{self.label}]: {urllib.parse.quote(self.destination)}"

        def __repr__(self) -> str:
            return self.destination

        def __str__(self) -> str:
            return f"[{self.text}][{self.label}]: {self.destination}"

    def generate(self):
        lines = []
        links = []
        pad = 2 if self.pad_column else 0

        def p_fix_join(s: Iterable[str]):
            """ Append prefix and postfix to string """
            return "|".join(('', *s, ''))

        def print_header():
            lines.append(p_fix_join(
                col.center(max(w, w + pad))
                for col, w in zip(self.table.header, self.table.widths)))

        def print_separator():
            lines.append(p_fix_join(
                "-" * max(w, w + pad)
                for w in self.table.widths))

        def print_rows():
            lines.extend(p_fix_join(
                col.ljust(w).center(max(w, w + pad))
                for col, w in zip(row, self.table.widths))
                         for row in self.table.bodies)

        def print_links():
            sorted_links = sorted(self.links, key=lambda s: (
                s.solution.category.value, s.solution.problem_no))
            links.extend(
                link.render_in_footer()
                for link in sorted_links)

        print_header()
        print_separator()
        print_rows()
        print_links()
        return lines, links


def inplace_replace_readme_file(generator) -> bool:
    """ 按需更新README.md文件

    :type generator: ()-> tuple[list[str], list[str]]
    :return 实际更新了没
    """

    file_hash = content_hasher()
    gens_hash = content_hasher()

    start_mark = '<!-- table of solutions -->'
    end_mark = '<!-- end of table of solutions -->'
    with open('README.md', 'r') as reader:
        processing = False
        processed = False
        file_lines = []
        for line in reader:
            file_hash += line
            if start_mark in line and line.startswith('<'):
                processing = True
            elif not processing:
                file_lines.append(line)
            elif end_mark in line:
                processing = False
                processed = True
                (lines, links) = generator()

                def new_line(s):
                    return s + "\n"

                file_lines.append(new_line(start_mark))
                file_lines.extend(map(new_line, lines))
                file_lines.append("\n")
                file_lines.extend(map(new_line, links))
                file_lines.append(new_line(end_mark))
        else:
            import sys
            if not processed:
                print(f"No {start_mark} found in README.md",
                      file=sys.stderr)
                exit(1)
            if processing:
                print(f"No {end_mark} found in README.md",
                      file=sys.stderr)
                exit(1)

            gens_hash += file_lines

    if file_hash == gens_hash:
        print("File content not changed, no writing is preformed.")
        return False

    with open('README.md', 'w') as writer:
        writer.writelines(file_lines)
        return True


# noinspection PyPep8Naming
class content_hasher:

    def __init__(self) -> None:
        self._md5 = hashlib.md5()

    def __iadd__(self, other):
        if isinstance(other, bytes):
            self._md5.update(other)
        if isinstance(other, str):
            self._md5.update(other.encode())
        elif isinstance(other, Iterable):
            for element in other:
                self.__iadd__(element)
        return self

    def __eq__(self, other):
        return isinstance(other, content_hasher) and \
               self._md5.digest() == other._md5.digest()

    def __hash__(self) -> int:
        return hash(self._md5.digest())


def main():
    """ Main entry point.
    Other file could import and call this function."""
    inplace_replace_readme_file(
        lambda: MarkdownTableGenerator(scan_for_problems()).generate())


if __name__ == '__main__':
    main()
