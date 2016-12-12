#include <cstdio>
#include <vector>
using namespace std;

int main()
{
    int n;
    scanf("%d", &n);

    vector<int> a(n+1);
    vector<int> psum(n+1, 0);

    for(int i=1; i<=n; i++){
        scanf("%d", &a[i]);
        psum[i] = psum[i-1] + a[i];
    }

    long long ans = 0;
    for(int i=1; i<=n-1; i++){
        ans += (long long)a[i]*(psum[n] - psum[i]);
    }
    printf("%lld\n", ans);
    return 0;
}