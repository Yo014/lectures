#include <iostream>
using namespace std;
#include "Time.h"


int main()
{
  Time wakeup(5,45);
  int  lunch     = 43200;	// noon
  int  breakfast = 20700;	// 5:45

    cout<<"wakeup vs. lunch:  equal?          "
        << ( (wakeup == lunch) ? "yes" : "no" ) <<endl;

  cout<<"wakeup vs. breakfast:  equal?      "
      << ( (wakeup == breakfast) ? "yes" : "no" ) <<endl;

  cout<<"lunch vs. wakeup:  equal?          "
      << ( (43200 == wakeup) ? "yes" : "no" ) <<endl;

  cout<<"breakfast vs. wakeup:  equal?      "
      << ( (breakfast == wakeup) ? "yes" : "no" ) <<endl;

  return 0;
}
