#include <iostream>
#include <string>
using namespace std;

int main()
{
    int T;
    cin >> T;

    while(T--)
    {
        string str;
        cin >> str;

        int len = str.size();
        if(len == 1) {
            cout << str << '\n' << str << '\n';
            continue;
        }

        for(int i=0; i<2; i++) {
            string ans;
            ans.push_back(str[i]);
            for(int j=(i+2)%len; j!=i; j=(j+2)%len) {
                ans += str[j];
            }
            cout << ans << '\n';
        }
    }

    return 0;
}