#include <iostream>
#include <string>
using namespace std;

class Error
{
  friend int main();
  public:
    Error(int *p, const string& m) : arr(p), msg(m) { }
  private:
    string msg;
    int* arr;
};

void initialize();

int main()
{
  try {
    initialize();
  }
  catch(Error& err) {
    cout<<endl<< "Error! "<< err.msg <<endl<<endl;
    delete [] err.arr;
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
  for (int i=0; i<size; ++i)
    cin >> myArray[i];

  if (!cin.good())
    throw Error(myArray, "bad input!");

  cout<<endl<<"You entered:"<<endl;

  for (int i=0; i<size; ++i)
    cout << myArray[i] << " ";
  cout<<endl;

  delete [] myArray;

}

