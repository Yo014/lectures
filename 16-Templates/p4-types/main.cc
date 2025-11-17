#include <iostream>
using namespace std;

#include "MapItem.h"
#define MAX_SIZE  4

int main()
{
  MapItem<string, int> map[MAX_SIZE];

  cout<<endl;

  map[0].set("Bobby", 10);
  map[1].set("Mike", 22);
  map[2].set("Wayne", 99);
  map[3].set("Joe", 65);

  for (int i=0; i<MAX_SIZE; ++i)
    cout<<map[i]<<endl;

  map[0].dummy();

  cout<<endl;


  return 0;
}

