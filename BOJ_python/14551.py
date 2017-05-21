import sys
sys.stdin = open('input.txt', 'r')

N, M = map(int, input().split())
ans = 1
for i in range(N):
    Ai = int(input())
    if Ai != 0: ans *= Ai
    ans %= M
print(ans % M)
