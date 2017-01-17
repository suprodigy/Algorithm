#include <iostream>
#include <cstring>
#include <string>
using namespace std;
const int MAX = 111;

int N;
int a[MAX][MAX];
int d[MAX][MAX];

int solve(int i, int j)
{
    if(i >= N || j >= N) return 0;
    if(i == N-1 && j == N-1) return 1;
    int& ret = d[i][j];
    if(ret != -1) return ret;
    int jump = a[i][j];
    return ret = solve(i + jump, j) || solve(i, j + jump);
}

int main()
{
    int C;
    cin >> C;

    while(C--)
    {
        memset(d, -1, sizeof(d));
        cin >> N;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                cin >> a[i][j];
            }
        }
        string ans = solve(0, 0) ? "YES" : "NO";
        cout << ans << "\n";
    }

    return 0;
}