import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
classes = [set(input().split()[1:]) for i in range(N)]
M = int(input())
for i in range(M):
    empty = set(input().split()[1:])
    count = 0
    for c in classes:
        if c.issubset(empty):
            count += 1
    print(count)
