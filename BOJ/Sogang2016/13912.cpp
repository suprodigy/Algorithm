#include <cstdio>
#include <cmath>
const int MAX = 2049;
const int MOD = 1000000007;
long long c[MAX][MAX];
long long d[11];

int main()
{
    int H;
    scanf("%d", &H);
    c[0][0] = 1;
    for(int i=1; i<=(1<<(H+1)); i++){
        c[i][0] = 1;
        for(int j=1; j<=i; j++){
            c[i][j] = (c[i-1][j-1] + c[i-1][j]) % MOD;
        }
    }

    d[0] = 1;
    for(int i=1; i<=H; i++){
        d[i] = (d[i-1] * d[i-1]) % MOD * c[(1<<(i+1)) - 2][(1<<i)-1];
        d[i] %= MOD;
    }
    printf("%d\n", (int)d[H]);
    return 0;
}