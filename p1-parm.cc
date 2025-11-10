#include <iostream>
using namespace std;

bool checkNum(int);
void doubleNum(int, int&);
void doubleNum(int, int*);

int main()
{ 
  bool inputOk = false;
  int  num1, num2, result1, result2;

  while (!inputOk) {
    cout << "Please enter a number between 0 and 100:  ";
    cin >> num1;
    inputOk = checkNum(num1);
    if (inputOk)
      break;
  }

  num2 = num1;

  doubleNum(num1, result1);
  doubleNum(num2, &result2);

  cout<<"Result 1:  " << result1 << endl;
  cout<<"Result 2:  " << result2 << endl;

  return 0;
}

void doubleNum(int n, int& res)
{
  cout << "pass-by-reference by reference" << endl;
  res = n * 2;
}

void doubleNum(int n, int* res)
{
  cout << "pass-by-reference by pointer" << endl;
  *res = n * 2;
}

bool checkNum(int n)
{
  return ( n>=0 && n<=100);
}

