#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> ii;

int main()
{
    int N;
    scanf("%d", &N);
    vector<ii> a(N);
    for(int i=0; i<N; i++)
        scanf("%d%d", &a[i].second, &a[i].first);
    sort(a.begin(), a.end());

    int ans = 1111111111;
    for(int i=N-1; i>=0; i--) {
        if(a[i].first < ans) ans = a[i].first;
        int c = a[i].second;
        ans -= c;
    }

    printf("%d\n", ans);
    return 0;
}