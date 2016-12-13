#include <iostream>
#include <vector>
#include <string>
#include <set>
using namespace std;

int main()
{
    int K, L;
    cin >> K >> L;

    vector<string> a(L), b;
    set<string> s;

    for(int i=0; i<L; i++)
        cin >> a[i];
    for(int i=a.size()-1; i>=0; i--) {
        if(s.count(a[i]) == 0){
            b.push_back(a[i]);
            s.insert(a[i]);
        }
    }

    int len = b.size();
    for(int j=1; j<=K && len-j>=0; j++){
        cout << b[len-j] << '\n';
    }
    return 0;
}