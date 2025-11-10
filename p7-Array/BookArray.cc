#include <iostream>
using namespace std;
#include <cstdlib>

#include "BookArray.h"

BookArray::BookArray(int c)
{
  if (c < 0) {
    cout << "ERROR:  invalid capacity" << endl;
    exit(1);
  }
 
  capacity = c;
  elements = new Book[capacity];
 
}

BookArray::~BookArray()
{
  delete [] elements;
}

bool BookArray::operator==(BookArray& arr)
{
    if (capacity != arr.capacity)return false;
    for (int i = 0; i < capacity; ++i){
      if (elements[i] != arr.elements[i]){
        return false;
      }
  }
  return true;

}

BookArray& BookArray::operator=(BookArray& arr)
{
  if (this == &arr){return *this;}
  
  if (capacity != arr.capacity){
    capacity = arr.capacity;
    delete [] elements;
    elements = new Book[capacity];
  }

  for (int i = 0; i < capacity; ++i){
    elements[i] = arr.elements[i];
  }

  return *this;

  
}

Book& BookArray::operator[](int index)
{
  if (index < 0 || index >=capacity){
    cout<< "ERROR: invalid index"<<endl;
    exit(1);
  }
  return elements[index];
}

const Book& BookArray::operator[](int index) const
{
  if (index < 0 || index >=capacity){
    cout<< "ERROR: invalid index"<<endl;
    exit(1);
  }
  return elements[index];
}

ostream& operator<<(ostream& output, const BookArray& arr)
{
  output << endl;

  for (int i=0; i<arr.capacity; ++i)
    output << arr[i];

  output << endl;
  return output;
  
}

