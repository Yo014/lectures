#include <iostream>
using namespace std;
#include <cstdlib>


#include "MapItem.h"

// We want to be able to specify a default size
// A Map of MapItems - template within a template
template <class T = int, class V = string, int capacity = 5>
class MyMap
{
  template <class A, class B, int c>
  friend ostream &operator<<(ostream &, const MyMap<A, B, c> &);

public:
  MyMap();
  void add(T, V);
  static int test;

private:
  int size;
  MapItem<T,V>* items;

};

template <class T, class V, int capacity>
int MyMap<T,V,capacity>::test = 42;

template <class T, class V, int capacity>
MyMap<T, V, capacity>::MyMap()
{
  items = new MapItem<T,V>[capacity];
  size = 0;
}

template <class T, class V, int c>
void MyMap<T, V, c>::add(T k, V v)
{
  if (size >= c)
  {
    cout << "ERROR:  map is full" << endl;
    exit(1);
  }

  items[size++].set(k,v);
 
}

template <class T, class V, int c>
ostream &operator<<(ostream &output, const MyMap<T, V, c> &m)
{
  for (int i = 0; i < m.size; ++i){
    cout<<m.items[i]<<endl;
  }
  return output;
}
