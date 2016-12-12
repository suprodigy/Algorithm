#include <cstdio>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> edge;
typedef pair<int, int> info;
const int MAX = 111111;
const int INF = 987654321;
vector<edge> adj[MAX];

int go(int N, int start, int end)
{
    vector<bool> c(N+1, false);
    priority_queue<info> pq;
    pq.push(make_pair(INF, start));

    while(!pq.empty()) {
        int now = pq.top().second;
        int w = pq.top().first;
        pq.pop();
        if(c[now]) continue;
        c[now] = true;
        if(now == end) return w;
        for(int i=0; i<adj[now].size(); i++){
            int next = adj[now][i].first;
            int nextW = adj[now][i].second;
            if(!c[next]) pq.push(make_pair(min(w, nextW), next));
        }
    }
    return 0;
}

int main()
{
    int N, M;
    scanf("%d%d", &N, &M);
    int start, end;
    scanf("%d%d", &start, &end);
    for(int i=0; i<M; i++) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        adj[u].push_back(make_pair(v, w));
        adj[v].push_back(make_pair(u, w));
    }

    int ans = go(N, start, end);
    printf("%d\n", ans);
    return 0;
}