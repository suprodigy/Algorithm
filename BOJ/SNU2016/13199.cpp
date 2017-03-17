#include <cstdio>

int main()
{
    int T;
    scanf("%d", &T);

    while(T--)
    {
        int P, M, F, C;
        scanf("%d%d%d%d", &P, &M, &F, &C);

        int ans = 0;
        int coupons = ((((M/P) * C) / F) * C) + (((M/P) * C) % F);
        while(coupons >= F) {
            int remainder = coupons % F;
            int new_order = coupons / F;
            ans += new_order;
            coupons = new_order * C + remainder;
        }

        printf("%d\n", ans);
    }

    return 0;
}