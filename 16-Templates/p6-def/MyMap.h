#include <iostream>
using namespace std;
#include <cstdlib>
#include <string>

#include "MapItem.h"

template <class T, class V, int capacity>
class MyMap
{
template <class A, class B, int c>
  friend ostream& operator<<(ostream&, MyMap<A,B,c>&);
  public:
    MyMap();
    void add(T, V);
    // static int cap;

  private:
    int size;
    MapItem<T,V>* arr;
};


template <class T, class V, int capacity>
MyMap<T,V,capacity>::MyMap()
{ 
  size = 0;
  arr  = new MapItem<T,V>[capacity];
}

template <class T, class V, int capacity>
void MyMap<T,V,capacity>::add(T k, V v) 
{ 
  if (size >= capacity) {
    cout << "ERROR:  map is full" << endl;
    exit(1);
  }

  arr[size].set(k, v);
  ++size;
}

template <class T, class V, int capacity>
ostream& operator<<(ostream& output, MyMap<T,V,capacity>& m)
{ 
  for (int i=0; i<m.size; ++i)
    output << m.arr[i];

  return output;
}

