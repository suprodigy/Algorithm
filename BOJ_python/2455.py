import sys
sys.stdin = open('input.txt', 'r')
readline = sys.stdin.readline

cur, ans = 0, 0
for i in range(4):
    getout, getin = map(int, readline().split())
    cur = cur - getout + getin
    ans = max(ans, cur)
print(ans)
