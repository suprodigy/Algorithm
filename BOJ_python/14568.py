import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
count = 0
for i in range(2, N+1, 2):
    for j in range(1, N+1-i):
        k = N - (i + j)
        if k >= j+2 and k != 0:
            count += 1
print(count)
