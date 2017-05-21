import sys
sys.stdin = open('input.txt', 'r')

import math

M, N = map(int, [input() for i in range(2)])
ans = list(filter(lambda x: math.ceil(math.sqrt(x)) == int(math.sqrt(x)), range(M, N+1)))
if len(ans) == 0:
    print(-1)
else:
    print(sum(ans))
    print(min(ans))
