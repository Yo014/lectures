#include <iostream>
using namespace std;

template <typename T>
T max (T, T, T);


int main()
{
  cout<<endl;
  cout<<"max int:   "<<max(33, 22)<<endl;
  cout<<"max float: "<<max(3.14f, 9.99f, 3.45f)<<endl;
  cout<<"max char:  "<<max('Z', 'z', 'c') <<endl;
  cout << endl;

  // cout << "max two ints: " << max(44, 55) <<endl;
  // cout << "max double:   " << max(44.4, 55.5, 77.8) <<endl;

  return 0;
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

