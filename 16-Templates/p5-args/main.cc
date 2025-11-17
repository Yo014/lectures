#include <iostream>
using namespace std;

#include "MyMap.h"


int main()
{
  MyMap<int, string, 4> map;

  map.add(10, "Bobby");
  map.add(22, "Mike");
  map.add(99, "Wayne");
  map.add(65, "Joe");
  //map.add(67, "Sue");

  cout<<"Printing map: "<<endl<<map<<endl;

  MyMap<int, string, 5> map2;

  cout<<"Static variable map: "<<map.test<<endl;
  cout<<"Static variable map: "<<map2.test<<endl;

  map.test = 11;

  cout<<"Static variable map: "<<map.test<<endl;
  cout<<"Static variable map: "<<map2.test<<endl;

  map2.add(42, "Hello");

  // cout<<endl;
  // cout<<map2;
  // cout<<endl;

  return 0;
}


