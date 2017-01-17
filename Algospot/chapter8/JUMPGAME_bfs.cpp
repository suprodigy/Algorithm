#include <iostream>
#include <cstring>
#include <queue>
#include <string>
using namespace std;
const int MAX = 111;
typedef pair<int, int> ii;

int N;
int a[MAX][MAX];
bool check[MAX][MAX];

bool bfs()
{
    queue<ii> q;
    q.push(make_pair(0, 0));
    check[0][0] = true;
    bool ret = false;

    while(!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();

        if(y == N-1 && x == N-1) {
            ret = true;
            break;
        }

        int jump = a[y][x];
        ii go[2] = { make_pair(jump, 0), make_pair(0, jump) };
        for(int i=0; i<2; i++) {
            int nextY = y + go[i].first;
            int nextX = x + go[i].second;
            if(nextY > N-1 || nextX > N-1) continue;
            if(!check[nextY][nextX]) {
                q.push(make_pair(nextY, nextX));
                check[nextY][nextX] = true;
            }
        }
    }

    return ret;
}

int main()
{
    int C;
    cin >> C;

    while(C--)
    {
        memset(check, 0, sizeof(check));
        cin >> N;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                cin >> a[i][j];
            }
        }
        string ans = bfs() ? "YES" : "NO";
        cout << ans << '\n';
    }

    return 0;
}