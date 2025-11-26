#include <iostream>
using namespace std;
#include <string>
#include <cstdlib>

string enterName();


int main()
{
  string someName = "abracadabra";

  try {
    someName = enterName();
    cout <<"--looks like no trouble here!"<<endl;
  }

  catch(const char* error) {
    cout << error <<endl;
  }

  cout << "Name: "<<someName<<endl;
  
  return 0;
}

string enterName()
{
  string s;

  cout<<"Enter a name: ";
  cin >> s;

  if (s == "Timmy")
    throw "Help, Timmy's been kidnapped by a giant squid!";

  return s;
}
