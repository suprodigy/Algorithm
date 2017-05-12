# import sys
# sys.stdin = open('input.txt', 'r')

# main
N = int(input())
data = [int(x) for x in input().split()]
S = set(data)
M = int(input())
nums = [int(x) for x in input().split()]
for i in range(M):
    if {nums[i],}.issubset(S):
        print(1)
    else:
        print(0)
