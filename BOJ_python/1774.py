import sys
sys.stdin = open('input.txt', 'r')

import math
from queue import PriorityQueue

def dist(i, j):
    return math.sqrt(pow(pos[i][0] - pos[j][0], 2) + pow(pos[i][1] - pos[j][1], 2))

def min_dist():
    pq = PriorityQueue()
    checked = [False] * N
    pq.put((0, 0))
    ret, count = 0, 0

    while not pq.empty():
        now = pq.get()

        if checked[now[1]]:
            continue
        checked[now[1]] = True
        ret += now[0]; count += 1

        if count == N:
            break

        for next in range(len(adj[now[1]])):
            if not checked[next]:
                pq.put((adj[now[1]][next], next))

    return ret

N, M = map(int, input().split())
pos = []

for i in range(N):
    pos.append(tuple(map(int, input().split())))
adj = [[0] * N for i in range(N)]

for i in range(N):
    for j in range(N):
        adj[i][j] = dist(i, j)

for i in range(M):
    u, v = map(int, input().split())
    adj[u-1][v-1] = 0
    adj[v-1][u-1] = 0

print('%.2f' % min_dist())
