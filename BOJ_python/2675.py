import sys
sys.stdin = open('input.txt', 'r')

T = int(input())
for test_case in range(T):
    temp = input().split()
    R, S = int(temp[0]), temp[1]
    print(''.join(list(map(lambda x: x*R, S))))
