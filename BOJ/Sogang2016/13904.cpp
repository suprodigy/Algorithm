#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> info;

int main()
{
    int N;
    scanf("%d", &N);

    vector<info> a(N);
    for(int i=0; i<N; i++)
        scanf("%d%d", &a[i].second, &a[i].first);

    sort(a.begin(), a.end());
    reverse(a.begin(), a.end());

    int ans = 0;
    vector<bool> c(1001, false);
    for(int i=0; i<a.size(); i++){
        int pos = -1;
        for(int j=1; j<=a[i].second; j++){
            if(!c[j]) pos = j;
        }
        if(pos != -1){
            ans += a[i].first;
            c[pos] = true;
        }
    }

    printf("%d\n", ans);
    return 0;
}