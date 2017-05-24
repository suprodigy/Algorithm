import sys
sys.stdin = open('input.txt', 'r')

# MAX = 16777217
# N, K = map(int, input().split())
# d, ans = [0] * MAX, -1
# d[0], nth = 1, 1
# for i in range(1, MAX):
#     if nth * 2 == i: nth *= 2
#     d[i] = d[nth] + d[i - nth]
#     if i >= N and d[i] <= K: ans = i; break
# print(ans - N if ans != -1 else -1)

ans = 0
N, K = map(int, input().split())
while bin(N).count('1') > K:
    a = 2 ** (bin(N)[::-1].index('1'))
    ans += a; N += a
print(ans)
