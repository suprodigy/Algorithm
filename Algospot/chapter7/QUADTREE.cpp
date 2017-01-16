#include <iostream>
#include <string>
using namespace std;

string flip(const string& str, int i)
{
    if(str[i] != 'x') {
        string ret;
        ret.push_back(str[i]);
        return ret;
    }

    int idx = i+1;
    string upLeft = flip(str, idx); idx += upLeft.size();
    string upRight = flip(str, idx); idx += upRight.size();
    string downLeft = flip(str, idx); idx += downLeft.size();
    string downRight = flip(str, idx); idx += downRight.size();

    return 'x' + downLeft + downRight + upLeft + upRight;
}

int main()
{
    int C;
    cin >> C;

    while(C--)
    {
        string str;
        cin >> str;
        cout << flip(str, 0) << '\n';
    }

    return 0;
}