#include <cstdio>

int N, M;
int a[7], num[7];

bool check()
{
    for(int i=0; i<M; i++){
        bool flag = false;
        for(int j=0; j<N; j++){
            if(a[j] == num[i]){
                flag = true;
                break;
            }
        }
        if(!flag) return false;
    }
    return true;
}

int go(int idx)
{
    if(idx == N){
        if(check()) return 1;
        else return 0;
    }
    int ret = 0;
    for(int i=0; i<=9; i++){
        a[idx] = i;
        ret += go(idx+1);
    }
    return ret;
}

int main()
{
    scanf("%d%d", &N, &M);
    for(int i=0; i<M; i++)
        scanf("%d", &num[i]);
    printf("%d\n", go(0));
}