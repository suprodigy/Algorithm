#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> ii;
typedef vector<int> vi;
vector<ii> shock;
vector<vi> adj;
vector<int> d;

int turnOff(int now)
{
    int& ret = d[now];
    if(ret != -1) return ret;
    ret = shock[now].second;
    int temp = shock[now].first;
    for(int i=0; i<adj[now].size(); i++) {
        int next = adj[now][i];
        temp += turnOff(next);
    }
    ret = min(ret, temp);
    return ret;
}

int main()
{
    int N;
    scanf("%d", &N);

    shock = vector<ii>(N+1);
    adj = vector<vi>(N+1);
    d = vector<int>(N+1, -1);

    for(int i=1; i<=N; i++) {
        int r;
        scanf("%d%d%d", &shock[i].first, &shock[i].second, &r);
        for(int j=0; j<r; j++) {
            int g;
            scanf("%d", &g);
            adj[i].push_back(g);
        }
    }

    printf("%d\n", turnOff(1));
    return 0;
}