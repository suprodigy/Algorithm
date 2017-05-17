import sys
sys.stdin = open('input.txt', 'r')

a, b = input().split()
print(max(int(a[::-1]), int(b[::-1])))
