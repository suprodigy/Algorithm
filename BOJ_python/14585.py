import sys
sys.stdin = open('input.txt', 'r')

N, M = map(int, input().split())
D = [[0] * 301 for i in range(301)]
for i in range(N):
    x, y = map(int, input().split())
    D[x][y] = M - (x + y)

for i in range(301):
    for j in range(301):
        temp = 0
        if i != 0: temp = max(temp, D[i-1][j])
        if j != 0: temp = max(temp, D[i][j-1])
        D[i][j] += temp

print(D[300][300])
