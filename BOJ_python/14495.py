import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
d = [1] * (N+1)
for i in range(4, N+1):
    d[i] = d[i-1] + d[i-3]
print(d[N])
