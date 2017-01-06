#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 987654321;
int button[10][5] = {0,1,2,-1,-1,3,7,9,11,-1,4,10,14,15,-1,0,4,5,6,7,6,7,8,10,12,
                        0,2,14,15,-1,3,14,15,-1,-1,4,5,7,14,15,1,2,3,4,5,3,4,5,9,13};

void press(int nth, int count, vector<int>& a)
{
    int i=0;
    while(i<5 && button[nth][i] != -1) {
        int now = button[nth][i];
        a[now] = (a[now] + count) % 4;
        i++;
    }
}

void cancel(int nth, int count, vector<int>& a)
{
    int i=0;
    while(i<5 && button[nth][i] != -1) {
        int now = button[nth][i];
        a[now] = (a[now] - count) % 4;
        i++;
    }
}

int go(int nth, vector<int>& a, int count)
{
    if(nth == 10) {
        bool flag = true;
        for(int i=0; i<16; i++) {
            if(a[i] != 0) {
                flag = false;
                break;
            }
        }
        if(flag) return count;
        else return INF;
    }
    int ret = INF;
    for(int i=0; i<4; i++) {
        press(nth, i, a);
        ret = min(ret, go(nth+1, a, count + i));
        cancel(nth, i, a);
    }
    return ret;
}

int main()
{
    int C;
    scanf("%d", &C);

    while(C--) {
        vector<int> a(16);
        for(int i=0; i<16; i++) {
            int num;
            scanf("%d", &num);
            a[i] = (num%12)/3;
        }
        int ans = go(0, a, 0);
        printf("%d\n", ans == INF ? -1 : ans);
    }

    return 0;
}