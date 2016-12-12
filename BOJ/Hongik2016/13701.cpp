#include <cstdio>
const int MAX = 1111111;
int check[MAX];
int main(){
  int num;
  while(scanf("%d", &num) != EOF){
    int i = num/32;
    int j = num%32;
    if((check[i] & (1<<j)) == 0){
      printf("%d ", num);
      check[i] |= (1<<j);
    }
  }
  puts("");
  return 0;
}