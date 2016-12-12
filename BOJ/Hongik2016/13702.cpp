#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

bool isPossible(int v, const vector<int>& a, int N, int K)
{
    int total = 0;
    for(int i=0; i<N; i++)
        total += (int)(a[i]/v);
    if(total >= K) return true;
    else return false;
}

int main()
{
    int N, K;
    scanf("%d%d", &N, &K);

    int right=0, left=0;
    vector<int> a(N);
    for(int i=0; i<N; i++) {
        scanf("%d", &a[i]);
        right = max(right, a[i]);
    }

    while(left <= right) {
        int mid = ((long long)(left+right))/2;
        if(isPossible(mid, a, N, K)) left = mid+1;
        else right = mid-1;
    }
    printf("%d\n", right);
    return 0;
}