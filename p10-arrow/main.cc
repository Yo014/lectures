#include <iostream>
using namespace std;
#include "Time.h"
#include "SPointer.h"




int main()
{
  Time now;
  now.setTime(10,10,10);
  now.print();

  SPointer today = new Time(9,9,9);

  today->print();
  (*today).print();

  // SPointer deletes the new Time object when it goes out of scope
  return 0;
}
