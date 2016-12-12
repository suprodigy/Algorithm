#include <iostream>
#include <string>
#include <algorithm>
using namespace std;
int a[5] = {0, 1, 0, 0, 2};

int main()
{
    string str;
    cin >> str;
    for(int i=0; i<str.size(); i++) {
        switch(str[i]) {
            case 'A': swap(a[1], a[2]); break;
            case 'B': swap(a[1], a[3]); break;
            case 'C': swap(a[1], a[4]); break;
            case 'D': swap(a[2], a[3]); break;
            case 'E': swap(a[2], a[4]); break;
            case 'F': swap(a[3], a[4]); break;
        }
    }
    pair<int, int> ans;
    for(int i=1; i<=4; i++){
        if(a[i] == 1) ans.first = i;
        else if(a[i] == 2) ans.second = i;
    }
    cout << ans.first << '\n' << ans.second << '\n';
    return 0;
}