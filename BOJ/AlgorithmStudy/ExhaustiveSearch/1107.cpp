#include <cstdio>
#include <cmath>
#include <algorithm>
using namespace std;

const int INF = 987654321;
bool buttons[10];
int N;

int countCipher(int N)
{
    int ret = 0;
    while(N) {
        N /= 10;
        ret++;
    }
    return ret;
}

int makeNum(int n, int num)
{
    if(n == 0) return (int)abs(N-num);

    int ret = INF;
    for(int i=0; i<10; i++) {
        if(buttons[i]) {
            num += i * (int)pow(10, n-1);
            ret = min(ret, 1 + makeNum(n-1, num));
            num -= i * (int)pow(10, n-1);
        }
    }

    return ret;
}

int main()
{
    scanf("%d", &N);

    int M;
    scanf("%d", &M);

    for(int i=0; i<M; i++) {
        int num;
        scanf("%d", &num);
        buttons[num] = true;
    }
    for(int i=0; i<10; i++)
        buttons[i] = !buttons[i];

    int cipher = countCipher(N);

    int ans = (int)abs(N-100);
    if(M != 10) {
        for(int i=cipher-1; i<=cipher+1; i++) {
            if(i <= 0) continue;
            ans = min(ans, makeNum(i, 0));
        }
    }

    printf("%d\n", ans);
    return 0;
}