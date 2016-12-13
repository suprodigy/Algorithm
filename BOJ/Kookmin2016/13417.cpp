#include <iostream>
#include <string>
using namespace std;

int main()
{
    int T;
    cin >> T;

    while(T--)
    {
        int N;
        cin >> N;

        string ans = "";
        for(int i=0; i<N; i++) {
            char now;
            cin >> now;
            if(ans.empty()) ans += now;
            else {
                if(ans[0] < now) ans += now;
                else ans = now + ans;
            }
        }

        cout << ans << '\n';
    }

    return 0;
}