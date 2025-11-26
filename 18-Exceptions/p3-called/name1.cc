#include <iostream>
using namespace std;
#include <string>
#include <cstdlib>

string enterName();


int main()
{
  string someName = "abracadabra";

  someName = enterName();
  cout << "Name: "<<someName<<endl;
  
  return 0;
}

string enterName()
{
  string s;
  cout<<"Enter a name: ";
  cin >> s;

  try{
    if (s=="Timmy"){
      throw "Timmy has been eaten by a giant squid!";
    }
  }catch(const char* error){
    cout<<error<<endl;
  }

  return s;
}
