#include <iostream>
using namespace std;
#include <cstdlib>
#include "MyClass.h"

float divByZero(float, float);

int main()
{
  float num1, num2;
  char  c;

  cout << "Enter two numbers:  ";
  cin >> num1 >> num2;

  if (!cin.good()) {
    cout<<"Input error"<<endl;
    exit(1);
  }

  cout << "Result: "<< divByZero(num1, num2) << endl;
  
  return 0;
}

float divByZero(float num, float den)
{
  float result = 0;

  
   try{
      if (den == 0)
        throw 30;
      result = num / den;
    }catch(const char* error){
        cout<<error<<endl;
    }catch(int error){
        cout<<"int: "<< error<<endl;
    }
  
  

  return result;
}
