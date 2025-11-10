#include <iostream>
#include <sstream>
using namespace std;
#include "Time.h"


int main()
{
  Time lunch(12);


  cout<<endl<<"Lunch before:     "<< lunch <<endl;
  lunch += 80;
  cout<<      "Lunch after:      "<< lunch <<endl <<endl;

  cout<<      "Later lunch:      "<< (lunch += 20) <<endl;
  cout<<      "Later lunch:      "<< lunch <<endl <<endl;

  Time iGoHome(13,15);
  Time youGoHome;

  cout<<endl<<"I go home before:     "<< iGoHome   <<endl;
  cout<<      "You go home before:   "<< youGoHome <<endl;

  youGoHome = iGoHome + 3700;

  cout<<endl<<"I go home after:      "<< iGoHome   <<endl;
  cout<<      "You go home after:    "<< youGoHome <<endl;

  youGoHome = 60;
  cout<<      "You go home after midnight:    "<< (youGoHome = 80) <<endl;

  stringstream ss;
  return 0;
}
