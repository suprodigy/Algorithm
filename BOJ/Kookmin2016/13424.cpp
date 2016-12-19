#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
const int MAX = 111;
const int INF = 987654321;

int main()
{
    int T;
    scanf("%d", &T);

    while(T--)
    {
        int N, M;
        scanf("%d%d", &N, &M);
        int d[MAX][MAX];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) d[i][j] = 0;
                else d[i][j] = INF;
            }
        }

        for(int i=0; i<M; i++) {
            int u, v, w;
            scanf("%d%d%d", &u, &v, &w);
            d[u][v] = w;
            d[v][u] = w;
        }

        int K;
        scanf("%d", &K);
        vector<int> friends(K);
        for(int i=0; i<K; i++)
            scanf("%d", &friends[i]);

        for(int k=1; k<=N; k++)
            for(int i=1; i<=N; i++)
                for(int j=1; j<=N; j++)
                    d[i][j] = min(d[i][j], d[i][k]+d[k][j]);

        int min_val = INF;
        int ans;
        for(int i=1; i<=N; i++) {
            int dist = 0;
            for(int j=0; j<K; j++)
                dist += d[i][friends[j]];
            if(dist < min_val) {
                min_val = dist;
                ans = i;
            }
        }

        printf("%d\n", ans);
    }

    return 0;
}