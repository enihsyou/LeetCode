import collections
from typing import *


class Solution:
    def numIdenticalPairs(self, nums: List[int]) -> int:
        m = collections.Counter(nums)
        return sum(v * (v - 1) // 2 for k, v in m.items())


assert Solution().numIdenticalPairs([1, 2, 3, 1, 1, 3]) == 4
