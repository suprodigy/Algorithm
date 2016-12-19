#include <cstdio>
#include <vector>
using namespace std;

int main()
{
    int T;
    scanf("%d", &T);

    while(T--)
    {
        int N, M, K;
        scanf("%d%d%d", &N, &M, &K);

        vector<int> a(N+1, 0), psum(N+1, 0);
        for(int i=1; i<=N; i++){
            scanf("%d", &a[i]);
            psum[i] = psum[i-1] + a[i];
        }

        int ret = 0;
        for(int i=1; i<=N; i++) {
            int amount = 0;
            if(i+M-1 <= N) amount += (psum[i+M-1] - psum[i-1]);
            else {
                amount += (psum[N] - psum[i-1]);
                amount += psum[(i+M-1)%N];
            }
            if(amount < K) ret += 1;
        }

        if(N == M && ret >= 1) ret = 1;
        printf("%d\n", ret);
    }

    return 0;
}