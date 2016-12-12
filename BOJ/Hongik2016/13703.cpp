#include <cstdio>
#include <cstring>
long long d[66][200];

long long go(int i, int now)
{
    if(now == 0) return 0;
    if(i == 0) return 1;
    long long & ret = d[i][now];
    if(ret != -1) return ret;
    ret = 0;
    ret += go(i-1, now-1);
    ret += go(i-1, now+1);
    return ret;
}

int main()
{
    int n, k;
    scanf("%d%d", &k, &n);
    memset(d, -1, sizeof(d));
    long long ans = go(n, k);
    printf("%lld\n", ans);
    return 0;
}