#include <cstdio>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

typedef pair<int, int> edge;
typedef pair<int, int> info;
const int MAX = 11111;
const int INF = 987654321;
vector<edge> adj[MAX];
bool check[MAX];

void dist(int limit, vector<int> start, int startCnt, vector<int>& d)
{
    priority_queue<info, vector<info>, greater<info> > pq;
    for(int i=0; i<startCnt; i++)
        pq.push(make_pair(0, start[i]));

    while(!pq.empty()) {
        int c = pq.top().first;
        int now = pq.top().second;
        pq.pop();
        if(check[now]) continue;
        check[now] = true;
        if(c > limit) continue;
        if(c != 0) d[now] = min(d[now], c);
        for(int i=0; i<adj[now].size(); i++) {
            int next = adj[now][i].first;
            int nextC = adj[now][i].second;
            if(!check[next]) pq.push(make_pair(c+nextC, next));
        }
    }
}

int main()
{
    int V, E;
    scanf("%d%d", &V, &E);
    for(int i=0; i<E; i++) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        adj[u].push_back(make_pair(v, w));
        adj[v].push_back(make_pair(u, w));
    }
    vector<int> d1(V+1, INF);
    vector<int> d2(V+1, INF);

    int M, x;
    scanf("%d%d", &M, &x);
    vector<int> mac(M);
    for(int i=0; i<M; i++)
        scanf("%d", &mac[i]);
    int S, y;
    scanf("%d%d", &S, &y);
    vector<int> star(S);
    for(int i=0; i<S; i++)
        scanf("%d", &star[i]);

    memset(check, 0, sizeof(check));
    dist(x, mac, M, d1);
    memset(check, 0, sizeof(check));
    dist(y, star, S, d2);

    int ans = INF;
    for(int i=1; i<=V; i++){
        ans = min(ans, d1[i]+d2[i]);
    }
    printf("%d\n", ans>=INF ? -1 : ans);
}