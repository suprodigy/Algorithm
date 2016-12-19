#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

bool search(vector<int>& a, int value) {
    int left = 0, right = a.size()-1;
    while(left <= right){
        int mid = (left+right)/2;
        if(a[mid] == value) return true;
        else if(a[mid] > value) right = mid-1;
        else left= mid+1;
    }
    return false;
}

int main()
{
    int T;
    scanf("%d", &T);

    while(T--)
    {
        int N;
        scanf("%d", &N);

        vector<int> a(N);

        for(int i=0; i<N; i++)
            scanf("%d", &a[i]);
        sort(a.begin(), a.end());

        int ret = 0;
        for(int i=0; i<N-2; i++)
            for(int j=i+1; j<N-1; j++)
                if(search(a, a[j] + a[j]-a[i])) ret++;

        printf("%d\n", ret);
    }

    return 0;
}