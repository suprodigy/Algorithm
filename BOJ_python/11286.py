import sys
sys.stdin = open('input.txt', 'r')
readline = sys.stdin.readline

# class myInt():
#     def __init__(self, val):
#         self.val = val
#     def __lt__(self, comp):
#         if (abs(self.val) == abs(comp.val)):
#             return self.val < comp.val
#         else:
#             return abs(self.val) < abs(comp.val)
#     def __eq__(self, comp):
#         return abs(self.val) == abs(comp.val) and self.val == comp.val
#
# from queue import PriorityQueue
#
# N, pq = int(readline()), PriorityQueue()
# for i in range(N):
#     now = int(readline())
#     if now != 0:
#         pq.put(myInt(now))
#     else:
#         print(pq.get().val if not pq.empty() else 0)

from heapq import heappush, heappop

N, heap = int(readline()), []
for _ in range(N):
    this = int(readline())
    if this != 0:
        heappush(heap, (abs(this), this))
    else:
        out = heappop(heap)[1] if len(heap) != 0 else 0
        sys.stdout.write(str(out) + '\n')
