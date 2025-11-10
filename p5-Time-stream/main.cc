#include <iostream>
using namespace std;
#include "Time.h"



int main()
{
  Time theTime(16, 40, 56);

  cout << endl << "The current time is: " << theTime << endl << endl;

  cout << "Please enter a time: ";
  cin >> theTime;
  cout << "New time is: " << theTime << endl;
  return 0;
  
}
