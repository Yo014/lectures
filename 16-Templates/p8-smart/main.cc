#include <iostream>
using namespace std;
#include "Time.h"
#include "SPointer.h"




int main()
{

  SPointer<Time> today = new Time(9,9,9);

  today->print(); cout<<endl;
  (*today).print(); cout<<endl;


  SPointer<int> sp = new int(42);

  cout<<"The secret of life is "<<*sp<<endl;


  // SPointer deletes the new Time object when it goes out of scope
  return 0;
}
