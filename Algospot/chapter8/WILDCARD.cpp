#include <iostream>
#include <cstring>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;
const int MAX = 111;

string str1, str2;
int strlen1, strlen2;
int d[MAX][MAX];

int compare(int i, int j)
{
    if (i == strlen1 && j == strlen2) return 1;
    if (i == strlen1 || j == strlen2) {
        if (j == strlen2) {
            bool flag = true;
            for(int k=i; k<strlen1; k++) {
                if(str1[k] != '*') {
                    flag = false;
                    break;
                }
            }
            if(flag) return 1;
        }
        return 0;
    }

    int & ret = d[i][j];
    if(ret != -1) return ret;

    ret = 0;
    if (str1[i] == '*') {
        for(int k=0; k<=strlen2-j; k++) {
            ret = ret || compare(i+1, j+k);
        }
    }
    else if (str1[i] == '?') {
        ret = ret || compare(i+1, j+1);
    }
    else {
        if(str1[i] == str2[j]) ret = ret || compare(i+1, j+1);
    }

    return ret;
}

int main()
{
    int C;
    cin >> C;

    while(C--)
    {
        cin >> str1;
        strlen1 = str1.size();
        vector<string> ans;

        int N;
        cin >> N;
        for(int i=0; i<N; i++) {
            memset(d, -1, sizeof(d));
            cin >> str2;
            strlen2 = str2.size();
            if(compare(0, 0))
                ans.push_back(str2);
        }

        sort(ans.begin(), ans.end());
        for(int i=0; i<ans.size(); i++)
            cout << ans[i] << '\n';
    }

    return 0;
}