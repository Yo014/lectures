#include <iostream>
using namespace std;

#include "Time.h"
int max(int, int, int);

int max(int, int);

template<typename T>
T max(T, T, T);

int main()
{
  cout<<endl;
  cout<<"max(33, 22, 44) int:   "<<max(33, 22, 44)<<endl;
  cout<<"max(33.0, 22.3, 44.4) int:   "<<max(33.0f, 22.3f, 44.4f)<<endl;
  cout<<"max(a, b, c) int:   "<<max('c', 'b', 'a')<<endl;

  Time t1(23), t2(22), t3(21);
  cout<<"max time:   "<<max(t1, t2, t3)<<endl;
  return 0;
}

int max(int v1, int v2, int v3){
  cout<<"In non-templated function"<<endl;
  int maxValue = v1;

  if (maxValue < v2)
    maxValue = v2;

  if (maxValue < v3)
    maxValue = v3;

  return maxValue;
}

template <class T>
T max(T v1, T v2, T v3){
  T maxValue = v1;

  if (maxValue < v2)
    maxValue = v2;

  if (maxValue < v3)
    maxValue = v3;

  return maxValue;
}





