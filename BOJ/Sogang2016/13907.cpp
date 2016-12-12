#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> edge;
const int MAX = 1111;
const int INF = 987654321;
vector<int> tax;
vector<edge> adj[MAX];
int d[MAX][MAX];

void go(int N, int S, int D)
{
    for(int i=0; i<N; i++)
        for(int j=1; j<=N; j++)
            d[i][j] = INF;
    d[0][S] = 0;

    for(int i=1; i<N; i++){
        for(int now=1; now<=N; now++){
            for(int k=0; k<adj[now].size(); k++){
                int j= adj[now][k].first;
                int cost = adj[now][k].second;
                if(d[i-1][now] != INF)
                    d[i][j] = min(d[i][j], d[i-1][now]+cost);
            }
        }
    }
}

int main()
{
    int N, M, K;
    scanf("%d%d%d", &N, &M, &K);
    int S, D;
    scanf("%d%d", &S, &D);

    for(int i=0; i<M; i++) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        adj[u].push_back(make_pair(v, w));
        adj[v].push_back(make_pair(u, w));
    }

    tax = vector<int>(K+1, 0);
    for(int i=1; i<=K; i++)
        scanf("%d", &tax[i]);

    go(N, S, D);

    int raise=0;
    for(int i=0; i<=K; i++) {
        int ans = INF;
        raise += tax[i];
        for(int j=1; j<N; j++)
            ans = min(ans, d[j][D] + raise*j);
        printf("%d\n", ans);
    }

    return 0;
}