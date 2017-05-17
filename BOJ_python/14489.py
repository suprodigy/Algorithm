import sys
sys.stdin = open('input.txt', 'r')

A, B = map(int, input().split())
C = int(input())
if A+B >= 2*C:
    print(A + B - 2*C)
else:
    print(A + B)
