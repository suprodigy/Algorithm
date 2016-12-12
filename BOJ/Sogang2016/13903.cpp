#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

typedef pair<int, int> ii;
typedef pair<int, ii> info;
const int MAX = 1111;
const int INF = 987564321;
bool a[MAX][MAX], c[MAX][MAX];
int R, C, N;
int dy[11], dx[11];

bool inRange(int y, int x)
{
    return 0<=y && y<R && 0<=x && x<=C;
}

int main()
{
    scanf("%d%d", &R, &C);
    for(int i=0; i<R; i++) {
        for(int j=0; j<C; j++) {
            int num;
            scanf("%d", &num);
            if(num == 1) a[i][j] = true;
            else a[i][j] = false;
        }
    }

    scanf("%d", &N);
    for(int i=0; i<N; i++)
        scanf("%d%d", &dy[i], &dx[i]);

    int ans = INF;
    priority_queue<info, vector<info>, greater<info> > pq;
    for(int j=0; j<C; j++)
        if(a[0][j]) pq.push(make_pair(0, make_pair(0, j)));

    while(!pq.empty()) {
        int cnt = pq.top().first;
        int y = pq.top().second.first;
        int x = pq.top().second.second;
        pq.pop();

        if(c[y][x]) continue;
        c[y][x] = true;
        if(y == R-1) {
            ans = cnt;
            break;
        }

        for(int i=0; i<N; i++) {
            int nextY = y+dy[i];
            int nextX = x+dx[i];
            if(inRange(nextY, nextX) && a[nextY][nextX] && !c[nextY][nextX])
                pq.push(make_pair(cnt+1, make_pair(nextY, nextX)));
        }
    }

    printf("%d\n", ans == INF ? -1 : ans);
    return 0;
}