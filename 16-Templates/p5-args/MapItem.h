#include <iostream>
using namespace std;

template <class T, class V>
class MapItem
{
template <class A, class B>
  friend ostream& operator<<(ostream&, const MapItem<A,B>&);
  public:
    void set(T, V);

  private:
    T key;
    V value;
};

template <class T, class V>
void MapItem<T,V>::set(T k, V v) 
{ 
  key   = k;
  value = v;
}

template <class T, class V>
ostream& operator<<(ostream& output, const MapItem<T,V>& m)
{ 
  output << "Key: " << m.key << ", Value: " << m.value << endl;

  return output;
}

