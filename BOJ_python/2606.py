import sys
sys.stdin = open('input.txt', 'r')

def go():
    st, checked = [], [False] * (N+1)
    st.append(1)
    ret, checked[1] = 0, True

    while len(st) != 0:
        now = st.pop()
        ret += 1
        for next in adj[now]:
            if not checked[next]:
                checked[next] = True
                st.append(next)

    return (ret - 1)


N, M = int(input()), int(input())
adj = [[] for i in range(N+1)]
for i in range(M):
    u, v = map(int, input().split())
    adj[u].append(v)
    adj[v].append(u)
print(go())
