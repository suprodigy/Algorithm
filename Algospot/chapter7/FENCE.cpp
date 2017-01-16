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

    int height = min(h[mid], h[mid+1]);
    int i = mid, j = mid+1, width = 2;
    ret = max(ret, height*width);
    while(left <= i-1 || j+1 <= right) {
        if(j+1 > right || (left <= i-1 && h[i-1] >= h[j+1])) {
            i--;
            width++;
            height = min(height, h[i]);
        }
        else {
            j++;
            width++;
            height = min(height, h[j]);
        }

        ret = max(ret, width * height);
    }
    return ret;
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