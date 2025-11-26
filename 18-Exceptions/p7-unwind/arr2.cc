#include <iostream>
#include <string>
using namespace std;

void initialize();
int  enterInt();


int main()
{
  try {
    initialize();
  }
  catch(string str) {
    cout<<endl<< "Error! "<< str <<endl<<endl;
  }
  return 0;
}

void initialize()
{
  int size;

  cout<<"Enter the number of elements: ";
  cin >> size;

  int* myArray = new int[size];

  cout<<"Enter the elements: " <<endl;
  
  try{
  for (int i=0; i<size; ++i)
      myArray[i] = enterInt();
  }catch(...){
    delete [] myArray;
    throw;
  }

  cout<<endl<<"You entered:"<<endl;

  for (int i=0; i<size; ++i)
    cout << myArray[i] << " ";
  cout<<endl;

  delete [] myArray;
}

int enterInt()
{
  int element;

  cin >> element;

  if (!cin.good())
    throw (string)"bad input!";

  return element;
}

