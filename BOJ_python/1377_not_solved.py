import sys
sys.stdin = open('input.txt', 'r')

from queue import Queue
q, a = Queue(), []

N = int(input())
for i in range(N):
    temp = int(input())
    q.put(temp); a.append(temp)
a.sort()

ans = 0
for num in a:
    while not q.empty() and q.get() != num:
        ans += 1
    if q.empty(): break
print(ans + 1)
