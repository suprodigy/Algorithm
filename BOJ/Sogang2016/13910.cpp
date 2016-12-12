#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 987654321;
int a[1111];
vector<int> plate;
vector<int> d;

int main()
{
    int N, M;
    scanf("%d%d", &N, &M);

    for(int i=0; i<M; i++){
        scanf("%d", &a[i]);
        plate.push_back(a[i]);
    }

    for(int i=0; i<M-1; i++)
        for(int j=i+1; j<M; j++)
            plate.push_back(a[i]+a[j]);

    sort(plate.begin(), plate.end());
    vector<int>::iterator itr = unique(plate.begin(), plate.end());
    plate.erase(itr, plate.end());

    d = vector<int>(N+1, INF);
    d[0] = 0;

    for(int i=1; i<=N; i++){
        for(int j=0; j<plate.size(); j++){
            if(i - plate[j] >= 0)
                d[i] = min(d[i], d[i-plate[j]]+1);
            else
                break;
        }
    }

    printf("%d\n", d[N] == INF ? -1 : d[N]);
    return 0;
}