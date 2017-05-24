import sys
sys.stdin = open('input.txt', 'r')

MAX = 10001
d, primes = [True] * MAX, []
d[0] = d[1] = False
for i in range(2, MAX):
    if d[i] == False: continue
    primes.append(i)
    for j in range(i + i, MAX, i):
        d[j] = False

TC = int(input())
for i in range(TC):
    n = int(input())
    n1, n2 = 0, 0
    for num in primes:
        if n - num < num: break
        if d[n - num]:
            n1, n2 = num, n - num
    print(n1, n2)
