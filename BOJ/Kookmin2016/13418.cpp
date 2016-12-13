#include <cstdio>
#include <vector>
#include <queue>
using namespace std;

typedef pair<int, int> edge;
typedef pair<int, int> info;
const int MAX = 1111;
vector<edge> adj[MAX];

int go(int N, bool best)
{
    priority_queue<info, vector<info>, greater<info> > pq;
    vector<bool> check(N+1, false);
    pq.push(make_pair(0, 0));
    int result = 0;

    while(!pq.empty()) {
        int c = pq.top().first;
        int now = pq.top().second;
        pq.pop();
        if(check[now]) continue;
        check[now] = true;
        result += c;
        for(int i=0; i<adj[now].size(); i++) {
            int next = adj[now][i].first;
            int nc = adj[now][i].second;
            if(best) nc *= -1;
            if(!check[next])
                pq.push(make_pair(nc, next));
        }
    }

    if(!best) return (N-result)*(N-result);
    else return (N+result)*(N+result);
}

int main()
{
    int N, M;
    scanf("%d%d", &N, &M);

    for(int i=0; i<M+1; i++) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        adj[u].push_back(make_pair(v, w));
        adj[v].push_back(make_pair(u, w));
    }

    int worst = go(N, false);
    int best = go(N, true);
    printf("%d\n", worst-best);
    return 0;
}