#include <iostream>
using namespace std;

#include "BookArray.h"
#include "Book.h"


int main()
{
  BookArray arr1(7);
  BookArray arr2;

  Book b1("Ender's Game", "Orson Scott Card");
  Book b2("Dune", "Frank Herbert");
  Book b3("Foundation", "Isaac Asimov");
  Book b4("Hitch Hiker's Guide to the Galaxy", "Douglas Adams");
  Book b5("So Long and Thanks for All the Fish", "Douglas Adams");

  arr1[0] = b1;
  arr1[1] = b2;
  arr1[2] = b3;
  arr1[3] = b4;


  cout << endl << arr1[0] <<endl << arr1[1];

  cout <<"Array 1:"<<endl<< arr1;
  cout<<"Array 2:"<<endl<<arr2;
  arr2 = arr1;
  cout << endl << "Arrays equal? " << (arr1 == arr2 ? "yes" : "no") << endl;

  arr2[5] = b1;
  cout << endl << "Arrays equal? " << (arr1 == arr2 ? "yes" : "no") << endl;

  // cout <<"Array 1:"<<endl<< arr1;
  // cout<<"Array 2:"<<endl<<arr2;

  // cout << endl << "Arrays equal? " << (arr1 == arr2 ? "yes" : "no") << endl;

  // arr2[2] = b5;

  // cout << endl << "Arrays equal? " << (arr1 == arr2 ? "yes" : "no") << endl;

  // cout << endl << "ARR1:" << endl;
  // cout << arr1;
  // cout << endl << "ARR2:" << endl;
  // cout << arr2;


  return 0;
}

