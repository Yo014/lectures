#include <iostream>
using namespace std;
#include "Time.h"


int main()
{
  Time wakeup, lunch;

  Time supper(lunch);

  wakeup.setTime(5,45,0);
  lunch.setTime(12,0,0);
  supper.setTime(6,0,0);

  cout<<"Print wakeup"<<endl;
  wakeup.print();
  cout<<"Print lunch"<<endl;
  lunch.print();
  cout<<"Wakeup at lunch time"<<endl;
  wakeup = lunch = supper;
  wakeup.print();

  cout<<"wakeup vs. lunch:  equal?      "
      << ( (wakeup.operator==(lunch)) ? "yes" : "no" ) <<endl;
  cout<<"wakeup vs. lunch:  not equal?  "
      << ( (wakeup != lunch) ? "yes" : "no" ) <<endl<<endl;

  cout<<"wakeup vs. lunch:  gt?  "
      << ( (wakeup >  lunch) ? "yes" : "no" ) <<endl;
  cout<<"wakeup vs. lunch:  ge?  "
      << ( (wakeup >= lunch) ? "yes" : "no" ) <<endl;
   cout<<"wakeup vs. lunch:  lt?  "
  << ( (wakeup <  lunch) ? "yes" : "no" ) <<endl;
  cout<<"wakeup vs. lunch:  le?  "
      << ( (wakeup <= lunch) ? "yes" : "no" ) <<endl<<endl;

  cout<<"wakeup vs. wakeup:  gt?  "
      << ( (wakeup >  wakeup) ? "yes" : "no" ) <<endl;
  cout<<"wakeup vs. wakeup:  ge?  "
      << ( (wakeup >= wakeup) ? "yes" : "no" ) <<endl;
  cout<<"wakeup vs. wakeup:  lt?  "
      << ( (wakeup <  wakeup) ? "yes" : "no" ) <<endl;
  cout<<"wakeup vs. wakeup:  le?  "
      << ( (wakeup <= wakeup) ? "yes" : "no" ) <<endl<<endl;

  return 0;
}
