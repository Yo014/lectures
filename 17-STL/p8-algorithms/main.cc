#include <iostream>
using namespace std;

#include <vector>
#include <algorithm>
#include <list>
#include "Student.h"
#include <iterator>


bool pass(Student&);

void printGPA(Student&);

int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe(    "100789111", "Joe", "Physics", 8.3f);
  Student stanley("100456123", "Stanley", "Geography", 5.6f);
  Student amy(    "100123444", "Amy", "Math", 10.8f);

  vector<Student> stuVect;

  cout<<endl<<"Pushing students on vector:"<<endl;
  stuVect.push_back(matilda);
  stuVect.push_back(joe);
  stuVect.push_back(stanley);
  stuVect.push_back(amy);


  
  ostream_iterator<Student> outItr(cout);

  sort(stuVect.begin(), stuVect.end());
  //sort(stuVect.begin(), stuVect.end(), Student::comp);

  cout<<endl<<"Vector students sorted using <:"<<endl;
  copy(stuVect.begin(), stuVect.end(), outItr);
  

  cout<<endl<<"Check if all students are passing:"<<endl;
//   if (all_of(stuVect.begin(), stuVect.end(), pass)){
//     cout<<"Everyone is passing"<<endl;
//   }else{
//     cout<<"Not everyone is passing"<<endl;
//   }
     cout<<endl<<"Check if ANY students are passing:"<<endl;
//   if (any_of(stuVect.begin(), stuVect.end(), pass)){
//     cout<<"Someone is passing"<<endl;
//   }else{
//     cout<<"No one is passing"<<endl;
//   }

//   cout<<"Print all Student's GPA's:"<<endl;
//   for_each(stuVect.begin(), stuVect.end(), printGPA);


//   cout<<"Finding Matilda: "<<endl;
//   vector<Student>::iterator found;

//   found = find(stuVect.begin(), stuVect.end(), "Matilda");

//   if(found!=stuVect.end()){
//     cout<<"Found: "<<endl<<*found<<endl;
//   }else{
//     cout<<"Not found"<<endl;
//   }

  cout<<"Find all passing students:"<<endl;
  vector<Student> stuVect2(stuVect.size());

  copy_if(stuVect.begin(), stuVect.end(), back_inserter(stuVect2), pass);

  cout<<endl<<"Vector passing students:"<<endl;
  for (int i=0; i<stuVect2.size(); ++i)
    cout<< stuVect2[i];
  cout<<endl;

//   cout<<"Fill with default student: "<<endl;
//   fill(stuVect2.begin(), stuVect2.end(), "Dude");

//   for (int i=0; i<stuVect2.size(); ++i)
//     cout<< stuVect2[i];
//   cout<<endl;

//   cout<<"Find minimum student: "<<endl;
//   cout<<*min_element(stuVect.begin(),stuVect.end())<<endl;

//   list<Student> stuList;
//   cout<<endl<<"Pushing students on list:"<<endl;
//   stuList.push_back(matilda);
//   stuList.push_back(joe);
//   stuList.push_back(stanley);
//   stuList.push_back(amy);

//   sort(stuList.begin(), stuList.end());

//  cout<<endl<<"List students:"<<endl;
//  copy(stuList.begin(), stuList.end(), outItr);
//   cout << endl;

  return 0;
}

void printGPA(Student& stu){
  cout<<"GPA: "<<stu.gpa<<endl;
}

bool pass(Student& stu){
  return stu.gpa >6.0f;
}