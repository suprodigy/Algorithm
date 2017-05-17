import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
roads = list(map(int, input().split()))
print(sum(roads) - max(roads))
