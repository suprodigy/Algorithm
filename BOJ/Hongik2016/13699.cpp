#include <cstdio>
long long t[36];
int main()
{
    int n;
    scanf("%d", &n);
    t[0] = 1;
    for(int i=1; i<=n; i++) {
        for(int j=0; j<=i-1; j++)
            t[i] += (t[j] * t[i-1-j]);
    }
    printf("%lld\n", t[n]);
    return 0;
}