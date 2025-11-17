#include <iostream>
using namespace std;

#include "List.h"
#include "Book.h"


int main()
{

  Book b1("Ender's Game", "Orson Scott Card");
  Book b2("Dune", "Frank Herbert");
  Book b3("Foundation", "Isaac Asimov");
  Book b4("Hitch Hiker's Guide to the Galaxy", "Douglas Adams");

  List<Book*> listBooks;
  listBooks.add(&b1);
  listBooks.add(&b2);
  listBooks.add(&b3);
  listBooks.add(&b4);
  
  cout<<"List of Books:"<<endl<<endl;

  for (int i =0; i < 4; ++i){
    cout<<*listBooks[i]<<endl;
  }
  

  cout<<"Remove the third book: "<<endl<<*listBooks[2]<<endl;

  listBooks.remove(2);

  cout<<"Printing list with removed book: "<<endl<<listBooks<<endl;

  // List<int> lints;

  // for (int i = 0; i < 5; ++i){
  //   lints.add(i);
  // }

  // cout<<"Printing lints: "<<endl;
  

  // for(int i = 0; i <5; ++i){
  //   cout<<lints[i]<<endl;
  // }

  // // cout<<"List of ints:"<<endl<<endl;
  // cout<<lints<<endl;
  cout<<"end"<<endl;
  return 0;
}

