import sys
sys.stdin = open('input.txt', 'r')

def calculate(M, N, x, y):
    ret, kth_year = -1, x
    while kth_year <= M * N:
        temp = kth_year % N if kth_year % N else N
        if temp == y:
            ret = kth_year
            break
        kth_year += M
    return ret

TC = int(input())
for test_case in range(TC):
    M, N, x, y = map(int, input().split())
    ans = calculate(M, N, x, y)
    print(ans)
