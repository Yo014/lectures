#include <iostream>
using namespace std;

#include <vector>
#include <list>
#include "Student.h"


int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe(    "100789111", "Joe", "Physics", 8.3f);
  Student stanley("100456123", "Stanley", "Geography", 5.6f);
  Student amy(    "100123444", "Amy", "Math", 10.8f);
  Student jimmy(  "100123555", "Jimmy", "Math", 10.8f);

  vector<Student> stuVect;

  cout<<endl<<"Pushing students on vector:"<<endl;
  stuVect.push_back(matilda);
  stuVect.push_back(joe);
  stuVect.push_back(stanley);
  stuVect.push_back(amy);

  cout<<endl<<"Vector students:"<<endl;
  for (int i=0; i<stuVect.size(); ++i)
    cout<< stuVect[i];
  cout<<endl;

  list<Student> stuList;  //doubly linked list

  stuList.push_back(matilda);
  stuList.push_back(joe);
  stuList.push_back(stanley);
  stuList.push_back(amy);

  for (int i=0; i<stuList.size(); ++i){
    cout<< stuList.front();
    stuList.pop_front();
  }

  return 0;
}

