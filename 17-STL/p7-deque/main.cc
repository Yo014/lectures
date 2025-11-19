#include <iostream>
using namespace std;

#include <deque>
#include <vector>
#include <iterator>
#include <algorithm>
#include "Student.h"


int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe(    "100789111", "Joe", "Physics", 8.3f);
  Student stanley("100456123", "Stanley", "Geography", 5.6f);
  Student amy(    "100123444", "Amy", "Math", 10.8f);

  Student bob(    "100987555", "Bob", "Chemistry", 11.9f);
  Student alice(  "100342322", "Alice", "CS", 7.8f);
  Student ted(    "100765444", "Ted", "Math", 4.6f);

  ostream_iterator<Student> outItr(cout);

  deque<Student> stuDeque;

  

  stuDeque.push_back(matilda);
  stuDeque.push_back(amy);
  stuDeque.push_back(stanley);
  stuDeque.push_back(joe);

  cout << "Deque of students:" << endl;
  copy(stuDeque.begin(), stuDeque.end(), outItr);
  cout << endl;

  cout << "pushing on the front of the deque" << endl;
  stuDeque.push_front(bob);
  stuDeque.push_front(alice);
  stuDeque.push_front(ted);
  // stuDeque.pop_back();

  cout << "Deque of students:" << endl;
  copy(stuDeque.begin(), stuDeque.end(), outItr);

  deque<Student>::iterator it;

  it = stuDeque.begin();
  cout << endl;

  cout <<"Student at position 3: " << it[2] << endl;

  // vector<Student> stuVect;
  // stuVect.push_back(matilda);
  // stuVect.push_back(amy);
  // stuVect.push_back(stanley);
  // stuVect.push_back(joe);

  // cout << "Vector of students:" << endl;
  // copy(stuVect.begin(), stuVect.end(), outItr);
  // cout << endl;

  // cout << "pushing on the front of the  vector" << endl;
  // stuVect.insert(stuVect.begin(), bob);
  // cout<<"insert next"<<endl;
  // stuVect.insert(stuVect.begin(), alice);
  // cout<<"insert next"<<endl;
  // stuVect.insert(stuVect.begin(), ted);

  // cout << "Vector of students:" << endl;
  // copy(stuVect.begin(), stuVect.end(), outItr);
  // cout << endl;
  
  return 0;
}

