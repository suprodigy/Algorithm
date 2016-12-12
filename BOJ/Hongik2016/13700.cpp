#include <cstdio>
#include <vector>
#include <queue>
using namespace std;

typedef pair<int, int> ii;
int N, S, D, F, B, K;

int go(vector<bool>& check, vector<bool>& police)
{
    queue<ii> q;
    q.push(make_pair(0, S));
    check[S] = true;
    int d[2] = {F, -B};

    while(!q.empty()) {
        int cnt = q.front().first;
        int now = q.front().second;
        q.pop();
        if(now == D) return cnt;
        for(int i=0; i<2; i++){
            int next = now + d[i];
            if(1<=next && next<=N && !check[next] && !police[next]){
                check[next] = true;
                q.push(make_pair(cnt+1, next));
            }
        }
    }
    return -1;
}

int main()
{
    scanf("%d%d%d", &N, &S, &D);
    scanf("%d%d%d", &F, &B, &K);

    vector<bool> check(N+1, false);
    vector<bool> police(N+1, false);

    for(int i=0; i<K; i++) {
        int num; scanf("%d", &num);
        police[num] = true;
    }

    int ans = go(check, police);
    if(ans == -1) puts("BUG FOUND");
    else printf("%d\n", ans);
    return 0;
}