#include <cstdio>
#include <vector>
using namespace std;

double a[8][8];

double go(int N, vector<bool>& visited, int pre, int cnt)
{
    if(N == cnt) return 0.0;
    double ret = 987654321.0;
    for(int i=0; i<N; i++) {
        if(!visited[i]) {
            visited[i] = true;
            double dist = 0.0;
            if(pre != -1) dist += a[pre][i];
            dist += go(N, visited, i, cnt+1);
            visited[i] = false;
            ret = min(ret, dist);
        }
    }
    return ret;
}

int main()
{
    int C;
    scanf("%d", &C);

    while(C--)
    {
        int N;
        scanf("%d", &N);

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                scanf("%lf", &a[i][j]);

        vector<bool> visited(N, false);
        printf("%.10lf\n", go(N, visited, -1, 0));
    }

    return 0;
}