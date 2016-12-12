#include <iostream>
#include <string>
using namespace std;

const int MOD = 1000000007;
const int MAX = 1111;
int d[MAX];

int main()
{
    string str;
    cin >> str;

    for(int i=str.size(); i>=0; i--) {
        int a['z'-'a'+1] = {0};
        d[i] = 1;

        for(int j=i; j<str.size(); j++) {
            if(++a[str[j]-'a'] == 3) {
                d[i] += d[j+1];
                d[i] %= MOD;
            }
        }
    }
    cout << (d[0]+MOD-1)%MOD << '\n';
    return 0;
}