#include <iostream>
using namespace std;

#include "MyMap.h"


int main()
{
  MyMap<string, string, 10> map1;

  map1.add("10", "Bobby");
  map1.add("22", "Mike");
  map1.add("99", "Wayne");
  map1.add("65", "Joe");


  cout<<endl<< "MAP 1: " << endl;
  cout<<map1;

  // cout<<"Capacity map1 is "<<map1.cap<<endl;


  MyMap<string, string, 4> map2;

  map2.add("home", "613-555-1010");
  map2.add("cell", "613-555-1110");

  cout<<endl<< "MAP 2: " << endl;
  cout<<map2;

  // cout<<"Capacity map2 is "<<map2.cap<<endl;

  cout<<endl;

  return 0;
}


