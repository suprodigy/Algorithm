#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

map<char, int> m;
char dna[] = {'A', 'C', 'G', 'T'};
bool c[] = {false, false, false, false};

int LCS(string str1, string str2)
{
    vector<vector<int> > d(2);
    for(int i=0; i<2; i++)
        d[i] = vector<int>(str2.size()+1);

    for(int i=0; i<2; i++) d[i][0] = 0;
    for(int j=0; j<str2.size()+1; j++) d[0][j] = 0;

    for(int i=1; i<=str1.size(); i++){
        for(int j=1; j<=str2.size(); j++){
            if(str1[i-1] == str2[j-1]) d[i%2][j] = d[(i-1)%2][j-1]+1;
            else d[i%2][j] = max(d[(i-1)%2][j], d[i%2][j-1]);
        }
    }

    return d[str1.size()%2][str2.size()];
}

int main()
{
    int N;
    cin >> N;
    string str;
    cin >> str;
    m['A'] = 0; m['C']=1; m['G']=2; m['T']=3;

    for(int i=0; i<N; i++) {
        c[m[str[i]]] = true;
    }

    char ch = 'a';
    for(int i=0; i<4; i++) {
        if(c[i] == false) {
            ch = dna[i];
            break;
        }
    }

    if(ch != 'a') {
        cout << 0 << '\n';
        for(int i=0; i<N; i++) cout << ch;
        cout << '\n';
    } else {
        string ans = str;
        reverse(ans.begin(), ans.end());
        int lcs = LCS(str, ans);
        cout << lcs << '\n';
        cout << ans << '\n';
    }

    return 0;
}