#include <iostream>
using namespace std;
#include "Time.h"


int main()
{
  int ten = 10;

  Time now;
  now.setTime(12,28,0);

  cout<<endl<<"Original int:           "<< ten <<endl;

  // cout<<endl<<"int with prefix inc:    "<< ++ten <<endl;
  // cout<<      "int after prefix inc:   "<< ten <<endl;

  // cout<<endl<<"int with prefix dec:    "<< --ten <<endl;
  // cout<<      "int after prefix dec:   "<< ten <<endl;

  // cout<<endl<<"int with postfix inc:   "<< ten++<<endl;
  // cout<<      "int after postfix inc:  "<< ten <<endl;

  cout<<endl<<"int with postfix dec:   "<< ten-- <<endl;
  cout<<      "int after postfix dec:  "<< ten <<endl<<endl;

  // Time now(16, 14);

  cout<<endl<<"Original time:           "<< now <<endl;

  // cout<<endl<<"int with prefix inc:    "<< ++now <<endl;
  // cout<<      "int after prefix inc:   "<< now <<endl;

  // cout<<endl<<"int with prefix dec:    "<< --now <<endl;
  // cout<<      "int after prefix dec:   "<< now <<endl;

  // cout<<endl<<"int with postfix inc:   "<< now++<<endl;
  // cout<<      "int after postfix inc:  "<< now <<endl;

  cout<<endl<<"int with postfix dec:   "<< now-- <<endl;
  cout<<      "int after postfix dec:  "<< now <<endl<<endl;


  return 0;
}


