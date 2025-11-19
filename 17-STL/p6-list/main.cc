#include <iostream>
using namespace std;

#include <list>
#include <iterator>
#include <algorithm>
#include "Student.h"


bool compare(Student&, Student&);

int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe(    "100789111", "Joe", "Physics", 8.3f);
  Student stanley("100456123", "Stanley", "Geography", 5.6f);
  Student amy(    "100123444", "Amy", "Math", 10.8f);
  Student bob(    "100987555", "Bob", "Chemistry", 11.9f);

  ostream_iterator<Student> outItr(cout);

  list<Student> comp2404;

  comp2404.push_back(matilda);
  comp2404.push_back(amy);
  comp2404.push_back(stanley);

  comp2404.push_back(joe);

  comp2404.sort();

  cout<<"Students sorts according to < operator:"<<endl;
  copy(comp2404.begin(), comp2404.end(), outItr);
  cout << endl;

  cout<<"Students sorts according to custom global function:"<<endl;
  comp2404.sort(compare);

  copy(comp2404.begin(), comp2404.end(), outItr);
  cout << endl;

  cout<<"Students sorts according to custom (static) member function:"<<endl;
  comp2404.sort(Student::compGPA);

  copy(comp2404.begin(), comp2404.end(), outItr);
  cout << endl;

  list<Student>::iterator it = comp2404.begin();
  ++it;
  ++it;


  comp2404.insert(it, bob);
  copy(comp2404.begin(), comp2404.end(), outItr);
  cout << endl;

  list<Student>::iterator it2 = comp2404.begin();

  comp2404.erase(it2, it);
  copy(comp2404.begin(), comp2404.end(), outItr);
  cout << endl;
  return 0;
}

bool compare(Student& s1, Student& s2){
  return s1.getName()<s2.getName();
}

