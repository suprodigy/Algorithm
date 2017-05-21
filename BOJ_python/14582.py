import sys
sys.stdin = open('input.txt', 'r')

a, a_score = list(map(int, input().split())), 0
b, b_score = list(map(int, input().split())), 0

ans = False

for i in range(9):
    a_score += a[i]
    if a_score > b_score:
        ans = True; break
    b_score += b[i]

if ans:
    print('Yes')
else:
    print('No')
