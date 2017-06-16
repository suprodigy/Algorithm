#include <iostream>
#include <cmath>
using namespace std;

// 절대 오차와 상대 오차를 모두 이용해서 두 수가 같은지 판정한다.
bool doubleEqual(double a, double b)
{
  double diff = fabs(a - b);

  // 절대 오차가 허용 범위 안일 경우 무조건 true를 반환한다.
  if (diff < 1e-10) return true;

  // 이 외의 경우에는 상대 오차를 이용한다.
  return diff <= 1e-8 * max(fabs(a), fabs(b));
}

// [1, n] 범위의 자연수 x에 대해 (x * 1.0 / x == 1)인 x의 수를 센다.
int countObvious(int n)
{
  int same = 0;
  for (int x = 1; x <=n; ++x)
  {
    double y = 1.0 / x;
    if (doubleEqual(x * y,1.0))
      same++;
  }

  return same;
}

int main()
{
  cout << countObvious(50) << endl;
}
