import sys
sys.stdin = open('input.txt', 'r')

ans = ['E', 'A', 'B', 'C', 'D']
for i in range(3):
    print(ans[len(list(filter(lambda x: x=='0', input().split())))])
