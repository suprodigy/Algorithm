#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main()
{
    int T;
    cin >> T;

    while(T--)
    {
        int N;
        cin >> N;
        string str1, str2;
        cin >> str1 >> str2;

        int white=0, black=0;
        for(int i=0; i<N; i++) {
            if(str1[i] != str2[i]) {
                if(str1[i] == 'B') black++;
                else white++;
            }
        }

        cout << max(white, black) << '\n';
    }

    return 0;
}