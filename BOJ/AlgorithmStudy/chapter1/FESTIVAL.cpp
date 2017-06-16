#include <cstdio>
#include <vector>
#include <limits>
using namespace std;

int main()
{
    // freopen("input.txt", "r", stdin);

    int C;
    scanf("%d", &C);

    while(C--)
    {
      int N, L;
      scanf("%d%d", &N, &L);

      vector<int> psum(N+1, 0);
      for(int i=1; i<=N; i++) {
        int num;
        scanf("%d", &num);
        psum[i] = psum[i-1] + num;
      }

      double ans = numeric_limits<double>::max();
      for(int i=L; i<=N; i++) {
        int min_sum = numeric_limits<int>::max();
        for (int j=1; j<=N-i+1; j++) {
          int temp = psum[j+i-1] - psum[j-1];
          min_sum = min(min_sum, temp);
        }
        ans = min(ans, min_sum/(double)i);
      }
      printf("%.10f\n", ans);
    }

    return 0;
}
