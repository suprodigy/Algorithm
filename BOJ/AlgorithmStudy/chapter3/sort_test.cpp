#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <sstream>
using namespace std;

// 테스트하고 싶은 정렬 함수
void mySort(vector<int>& array, int left, int right);
void merge(vector<int>&array, int left, int mid, int right);
// 주어진 배열을 문자열으로 바꾸는 함수
string toString(const vector<int>& array);

int main()
{
  while(true)
  {
    int n = rand() % 100 + 1;
    vector<int> input(n);
    for (int i=0; i<n; i++) input[i] = rand();

    vector<int> mySorted = input;
    mySort(mySorted, 0, n);
    vector<int> reference = input;
    sort(reference.begin(), reference.end());

    if (mySorted != reference) {
      cout << "Mismatch!" << endl;
      cout << "Input: " << toString(input) << endl;
      cout << "Expected: " << toString(reference) << endl;
      cout << "Got: " << toString(mySorted) << endl;
      break;
    }
  }
}

void mySort(vector<int>& array, int left, int right)
{
  if (left < right)
  {
    int mid = (left + right) / 2;
    mySort(array, left, mid);
    mySort(array, mid+1, right);
    merge(array, left, mid, right);
  }
}

void merge(vector<int>& array, int left, int mid, int right)
{
  vector<int> temp(right - left + 1, 0);
  int i=left, j=mid+1, k=0;
  while (i<=mid && j<=right)
  {
    if (array[i] < array[j]) temp[k++] = array[i++];
    else temp[k++] = array[j++];
  }

  while(i<=mid) temp[k++] = array[i++];
  while(j<=right) temp[k++] = array[j++];

  int size = right - left + 1;
  for (int l=0; l<size; l++) array[left + l] = temp[l];
}

string toString(const vector<int>& array)
{
  string ret = "";
  int len = array.size();

  for (int i=0; i<len; i++)
  {
    stringstream ss;
    ss << array[i];
    ret += ss.str() + " ";
  }
  return ret;
}
