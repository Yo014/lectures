#include <iostream>
using namespace std;

#include <set>
#include <vector>
#include <iterator>
#include <algorithm>
#include "Student.h"
#include <functional>


int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe(    "100789111", "Joe", "Physics", 8.3f);
  Student stanley("100456123", "Stanley", "Geography", 5.6f);
  Student amy(    "100123444", "Amy", "Math", 10.8f);

  Student bob(    "100987555", "Bob", "Chemistry", 11.9f);
  Student alice(  "100342322", "Alice", "CS", 7.8f);
  Student ted(    "100765444", "Ted", "Math", 4.6f);

  multiset<Student, function<bool(Student&, Student&)>> 
  students([](Student& s1, Student& s2)->bool{return s1 < s2;});

  students.insert(matilda);
  students.insert(joe);
  students.insert(stanley);
  students.insert(amy);
  students.insert(bob);

  multiset<Student, Student::cmp>::iterator itr;

  for (itr = students.begin(); itr != students.end(); ++itr){
      cout <<*itr<<endl;
  }
  
  return 0;
}

