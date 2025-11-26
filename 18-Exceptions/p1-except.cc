#include <iostream>
using namespace std;

#include <stdexcept>
#include "MyClass.h"

void func1();
void func2();
void func3();


int main()
{

 try{ 
  cout<<endl<< "calling func1 from main"<<endl;
  func1();
  cout << "back from func1 in main"<<endl;
 }catch(MyClass& error){
   cout<<endl<<"Exception: "<<endl;
   error.print();
 }
  cout<<"We're done"<<endl;
  
  return 0;
}

void func1()
{
  cout<<endl<< "calling func2 from func1"<<endl;
  func2();
  cout << "back from func2 in func1"<<endl;
}

void func2()
{
  cout<<endl<< "calling func3 from func2"<<endl;
  func3();
  cout << "back from func3 in func2"<<endl;
}

void func3()
{
  cout<<endl<< "in func3"<<endl;
  //throw runtime_error("runtime error in func3");
  throw MyClass("error");
  cout << "exiting func3"<<endl;
}

