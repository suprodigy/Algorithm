#include <cstdio>
#include <algorithm>
using namespace std;

int main()
{
    int T;
    scanf("%d", &T);

    while(T--)
    {
        int N;
        scanf("%d", &N);

        int ans = 0;
        for(int i=0; i<N; i++) {
            int A, B, C;
            scanf("%d%d%d", &A, &B, &C);
            int now = max(0, max(max(A, B), C));
            ans += now;
        }
        printf("%d\n", ans);
    }

    return 0;
}