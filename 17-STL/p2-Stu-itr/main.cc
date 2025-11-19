#include <iostream>
using namespace std;

#include <list>
#include <vector>
#include "Student.h"


int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe(    "100789111", "Joe", "Physics", 8.3f);
  Student stanley("100456123", "Stanley", "Geography", 5.6f);
  Student amy(    "100123444", "Amy", "Math", 10.8f);
  Student jimmy(  "100123555", "Jimmy", "Math", 10.8f);


  // list<Student> stuList;
  // stuList.push_back(matilda);
  // stuList.push_back(joe);
  // stuList.push_back(stanley);
  // stuList.push_back(amy);


  // list<Student>::iterator itr;
  // list<Student>::iterator enditr;

  // enditr = stuList.end();

  // for (itr = stuList.begin(); itr != enditr; ++itr ){
  //     cout<< *itr <<endl;
  //     //itr->setName("Bob");
  // }

  // // list<Student>::const_iterator citr;

  // // for (citr = stuList.begin(); citr != stuList.end(); ++citr ){
  // //     cout<< *citr <<endl;
  // //     //citr->setName("Steve");
  // // }

  // list<Student>::reverse_iterator ritr;

  // cout<<endl<<"in reverse"<<endl;
  // for (ritr = stuList.rbegin(); ritr != stuList.rend(); ++ritr ){
  //     cout<< *ritr <<endl;
  //     //itr->setName("Bob");
  // }

  vector<Student> stuV;

  stuV.push_back(matilda);
  stuV.push_back(joe);
  stuV.push_back(stanley);
  stuV.push_back(amy);

  vector<Student>::iterator itr;

  for (itr = stuV.begin(); itr != stuV.end()-2; ++itr ){
      cout<< *itr <<endl;
  }

  cout<<"Who is left?"<<endl;

  cout<<itr[0]<<endl<<itr[1]<<endl;

  ++itr;


  
  return 0;
}

