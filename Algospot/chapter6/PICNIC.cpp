#include <cstdio>
#include <vector>
using namespace std;

int mating(int n, vector<bool>& checked,
    const vector<vector<bool> >& isFriend, int cnt)
{
    if(cnt == 0) return 1;
    int ret = 0;

    int now;
    for(int i=0; i<n; i++) {
        if(!checked[i]) {
            now = i; break;
        }
    }

    checked[now] = true;
    for(int i=now; i<n; i++) {
        if(checked[i]) continue;
        if(isFriend[now][i]) {
            checked[i] = true;
            ret += mating(n, checked, isFriend, cnt-2);
            checked[i] = false;
        }
    }
    checked[now] = false;

    return ret;
}

int main()
{
    int C;
    scanf("%d", &C);

    while(C--)
    {
        int n, m;
        scanf("%d%d", &n, &m);

        vector<vector<bool> > isFriend(n);
        vector<bool> checked(n, false);
        for(int i=0; i<n; i++)
            isFriend[i] = vector<bool>(n, false);
        for(int i=0; i<m; i++) {
            int s1, s2;
            scanf("%d%d", &s1, &s2);
            isFriend[s1][s2] = true;
            isFriend[s2][s1] = true;
        }

        printf("%d\n", mating(n, checked, isFriend, n));
    }

    return 0;
}