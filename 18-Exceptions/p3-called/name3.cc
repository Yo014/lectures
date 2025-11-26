#include <iostream>
using namespace std;
#include <string>
#include <cstdlib>

string enterName();
void   checkName(string);


int main()
{
  string someName = "abracadabra";

  try {
    someName = enterName();
    cout <<"-- looks like no trouble in main!"<<endl;
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

  checkName(s);

  cout <<"-- looks like no trouble in enterName!"<<endl;

  return s;
}

void checkName(string name)
{
  if (name == "Timmy")
    throw "Help, Timmy's been kidnapped by a giant squid!";
}

