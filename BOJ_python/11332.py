import sys
sys.stdin = open('input.txt', 'r')

def fact(N):
    ret, i = 1, N
    global op_per_sec, L
    while i >= 1:
        if ret > op_per_sec * L:
            return op_per_sec * L + 1
        ret *= i
        i -= 1
    return ret

op_per_sec = 100000000
TC = int(input())
for test_case in range(TC):
    input_data = input().split()
    complexity = input_data[0]
    N, T, L = map(int, input_data[1:])

    total = 0
    if complexity == 'O(N)':
        total = N * T
    elif complexity == 'O(N^2)':
        total = (N ** 2) * T
    elif complexity == 'O(N^3)':
        total = (N ** 3) * T
    elif complexity == 'O(2^N)':
        total = pow(2, N) * T
    else:
        total = fact(N) * T

    if total <= L * op_per_sec:
        print('May Pass.')
    else:
        print('TLE!')
