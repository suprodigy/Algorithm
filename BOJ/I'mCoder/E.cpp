#include <cstdio>
#include <vector>
using namespace std;

int main()
{
    int N, Q;
    scanf("%d%d", &N, &Q);
    vector<bool> a(N+1, false);

    int ans = N;
    for(int i=0; i<Q; i++) {
        int L, I;
        scanf("%d%d", &L, &I);
        for(int j=L; j<=N; j+=I) {
            if(!a[j]) {
                ans--;
                a[j] = true;
            }
        }
    }
    printf("%d\n", ans);

    return 0;
}