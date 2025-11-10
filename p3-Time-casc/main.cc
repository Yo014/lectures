#include <iostream>
using namespace std;
#include "Time.h"


int main()
{
  Time wakeup, lunch;
  wakeup.setTime(5,45,0);
  lunch.setTime(12,0,0);
  
  wakeup.print();

  lunch.print();

  lunch.setSeconds(12).setHours(12);

  
  cout<<"reseting lunch"<<endl;
  lunch.print();

  cout<<"reseting wakeup"<<endl;
  wakeup.setTime(11,11,11).print();

  return 0;
}
