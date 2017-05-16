import sys
sys.stdin = open('input.txt', 'r')

from queue import Queue

def span(adj, S, T):
    global N
    checked = [False] * (N+1)
    pq = Queue()
    pq.put(S)
    checked[S] = True
    count = 0
    
    while not pq.empty():
        now = pq.get()
        count += 1

        for next in adj[now]:
            if not checked[next]:
                checked[next] = True
                pq.put(next)

    if not checked[T]:
        return 0
    else:
        return count

data = [int(x) for x in input().split()]
N, M, S, T =  data[0], data[1], data[2], data[3]
adj = [[] for i in range(N+1)]
for i in range(M):
    temp = input().split()
    u, v = int(temp[0]), int(temp[1])
    adj[u].append(v)
ans = span(adj, S, T)
print(ans)
