import sys
sys.stdin = open('input.txt', 'r')

import math
def is_prime(num):
    if num == 1:
        return False
    return all(num % i for i in range(2, math.floor(math.sqrt(num)) + 1))

M, N = map(int, [input() for i in range(2)])
prime_list = list(filter(is_prime, list(range(M, N+1))))

if len(prime_list) == 0:
    print(-1)
else:
    print(sum(prime_list))
    print(min(prime_list))
