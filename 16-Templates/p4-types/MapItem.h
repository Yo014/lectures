#include <iostream>
using namespace std;

// for hockey players
template <class K, class V>
class MapItem{
  template <class A, class B>
  friend ostream& operator<<(ostream&, MapItem<A,B>&);
  public:
    void set(K, V);
    void dummy();

  private:
    K key;
    V value;
};

template <class K, class V>
void MapItem<K,V>::dummy() 
{ 
  int x;
  K k = x;
}

template <class K, class V>
void MapItem<K,V>::set(K k, V v) 
{ 
  key   = k;
  value = v;
}

template <class K, class V>
ostream& operator<<(ostream& output, MapItem<K,V>& m)
{ 
  output << "Key: " << m.key << ", Value: " << m.value << endl;

  return output;
}