#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int cutFence(const vector<int>& h, int left, int right)
{
    if(right == left) return h[left];

    int mid = (left+right)/2;
    int leftArea = cutFence(h, left, mid);
    int rightArea = cutFence(h, mid+1, right);
    int ret = max(leftArea, rightArea);

    int center = h[mid] <= h[mid+1] ? mid : mid+1;

    int width=0, i=mid, j=mid+1;
    while(left <= i && h[i] >= h[center]) {
        width++;
        i--;
    }
    while(j <= right && h[j] >= h[center]) {
        width++;
        j++;
    }
    return max(ret, h[center] * width);
}

int main()
{
    int C;
    cin >> C;

    while(C--)
    {
        int N;
        cin >> N;
        vector<int> h(N);
        for(int i=0; i<N; i++) { cin >> h[i]; }
        cout << cutFence(h, 0, N-1) << '\n';
    }

    return 0;
}