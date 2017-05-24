import sys
sys.stdin = open('input.txt')

import math
TC = int(input())
for test_case in range(TC):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    max_r, min_r = max(r1, r2), min(r1, r2)
    d = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)
    if x1 == x2 and y1 == y2 and r1 == r2: print(-1)
    elif (max_r - min_r) < d < (r1 + r2): print(2)
    elif (max_r - min_r) == d or (r1 + r2) == d: print(1)
    else: print(0)
