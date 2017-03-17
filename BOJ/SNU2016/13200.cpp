#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int N;
bool flag;
vector<vector<int> > a, ans;

int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

bool checkCondition()
{
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            if (a[i][j] >= 0) {
                int cnt = 0;
                for (int k=0; k<4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (0<=ny && ny<N && 0<=nx && nx<N) {
                        if (ans[ny][nx] == 1) cnt++;
                    }
                }

                if (a[i][j] != cnt) {
                    return false;
                }
            } else if (a[i][j] == -2) {
                return false;
            }
        }
    }

    return true;
}

void turnOn(int i, int j)
{
    ans[i][j] = 1;

    for (int col=j; col>=0; col--) {
        if (a[i][col] == -2) a[i][col] = -3;
        else if (a[i][col] >= -1) break;
    }
    for (int col=j; col<N; col++) {
        if (a[i][col] == -2) a[i][col] = -3;
        else if (a[i][col] >= -1) break;
    }
    for (int row=i; row>=0; row--) {
        if (a[row][j] == -2) a[row][j] = -3;
        else if (a[row][j] >= -1) break;
    }
    for (int row=i; row<N; row++) {
        if (a[row][j] == -2) a[row][j] = -3;
        else if (a[row][j] >= -1) break;
    }
}

void turnOff(int i, int j)
{
    ans[i][j] = 0;

    for (int col=j; col>=0; col--) {
        if (a[i][col] == -3) a[i][col] = -2;
        else if (a[i][col] >= -1) break;
    }
    for (int col=j; col<N; col++) {
        if (a[i][col] == -3) a[i][col] = -2;
        else if (a[i][col] >= -1) break;
    }
    for (int row=i; row>=0; row--) {
        if (a[row][j] == -3) a[row][j] = -2;
        else if (a[row][j] >= -1) break;
    }
    for (int row=i; row<N; row++) {
        if (a[row][j] == -3) a[row][j] = -2;
        else if (a[row][j] >= -1) break;
    }
}

void go (int i, int j) {
    if (i == N-1 && j == N) {
        if (checkCondition()) {
            flag = true;
        }
        return;
    }

    if (j >= N) {
        go(i+1, 0);
        return;
    }

    if (a[i][j] >= -1 || a[i][j] == -3) {
        go(i, j+1);
        return;
    }
    else {
        // put a light bulb
        turnOn(i, j);
        go(i, j+1);

        if (!flag) {
            // clean the light bulb
            turnOff(i, j);
            go(i, j+1);
        }
    }
}

int main()
{
    int T;
    cin >> T;

    while(T--)
    {
        flag = false;

        cin >> N;

        a = vector<vector<int> >(N);
        ans = vector<vector<int> >(N);

        for (int i=0; i<N; i++) {
            a[i] = vector<int>(N, 0);
            ans[i] = vector<int>(N, 0);
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                cin >> a[i][j];
            }
        }

        go(0, 0);

        // print answer;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                cout << ans[i][j] << ' ';
            }
            cout << '\n';
        }
    }
}