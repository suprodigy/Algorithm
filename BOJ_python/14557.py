import sys
sys.stdin = open('input.txt', 'r')

import math
R, C = map(int, input().split())
pair_count = (R * C) // 2
extra = (R * C) // 2 - 1
print(pair_count, pair_count + extra)
