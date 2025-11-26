#include <iostream>
#include <string>
using namespace std;

void initialize();
int  enterInt();

class IntArray
{
  public:
    IntArray(int s=10) : size(s) { ptr = new int[size]; }
    ~IntArray()                  { delete [] ptr;       }
    int& operator[](int sub)     { return ptr[sub];     }
  private:
    int  size;
    int* ptr;
};


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

  IntArray myArray(size);

  cout<<"Enter the elements: " <<endl;
  
  for (int i=0; i<size; ++i)
      myArray[i] = enterInt();
  

  cout<<endl<<"You entered:"<<endl;

  for (int i=0; i<size; ++i)
    cout << myArray[i] << " ";
  cout<<endl;


}

int enterInt()
{
  int element;

  cin >> element;

  if (!cin.good())
    throw((string)"bad input!");

  return element;
}

