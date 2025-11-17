#include <iostream>
using namespace std;
#include <algorithm>
#include <vector>
#include "Student.h"

bool compare(Student* s1, Student* s2);

int main()
{
  Student matilda("100567899", "Matilda", "CS", 9.0f);
  Student joe("100234555", "Joe", "Physics", 8.3f);
  Student timmy("100234888", "Timmy", "CS", 11.5f);
  Student jimmy("100234889", "Jimmy", "Math", 10.5f);
  Student alice("100234890", "Alice", "English", 10.0f);

  // what if this was objects?
  vector<Student*> comp2404;

  

  // cout<<"Capacity: "<<comp2404.capacity()<<endl;
  // cout<<"Size    : "<<comp2404.size()<<endl;

  // comp2404.push_back(&matilda);
  // comp2404.push_back(&joe);
  // comp2404.push_back(&timmy);
  // comp2404.push_back(&jimmy);
  // comp2404.push_back(&alice);
  // cout << endl << "COMP 2404 students:" << endl;

  // for (int i=0; i<comp2404.size(); ++i)
  //   cout << *comp2404[i];
  
  // cout << comp2404 << endl;


  // comp2404.push_back(&matilda);
  // cout<<"Adding "<<matilda<<endl;
  // cout<<"Capacity: "<<comp2404.capacity()<<endl;

  // comp2404.push_back(&joe);
  // cout<<"Adding "<<joe<<endl;
  // cout<<"Capacity: "<<comp2404.capacity()<<endl;
  // comp2404.push_back(&timmy);
  // cout<<"Adding "<<timmy<<endl;
  // cout<<"Capacity: "<<comp2404.capacity()<<endl;
  // comp2404.push_back(&jimmy);
  // cout<<"Adding "<<jimmy<<endl;
  // cout<<"Capacity: "<<comp2404.capacity()<<endl;
  // comp2404.push_back(&alice);
  // cout<<"Adding "<<alice<<endl;
  // cout<<"Capacity: "<<comp2404.capacity()<<endl;


  // cout << endl << "COMP 2404 students:" << endl;

  // for (int i=0; i<comp2404.size(); ++i)
  //   cout << *comp2404[i];

  // vector<Student*> comp2406(comp2404);
  // cout << endl << "Are vectors equal? "<< ( comp2406 == comp2404 ? "yes" : "no" ) << endl;

  // cout<<"Capacity 2404: "<<comp2404.capacity()<<endl;
  // cout<<"Capacity 2406: "<<comp2406.capacity()<<endl;

  // comp2406.push_back(&jimmy);
  
  // comp2406.pop_back();
  // cout<<"Capacity 2404: "<<comp2404.capacity()<<endl;
  // cout<<"Capacity 2406: "<<comp2406.capacity()<<endl;

  // sort(comp2406.begin(), comp2406.end(), compare);

  // cout << endl << "Are vectors equal? "<< ( comp2406 == comp2404 ? "yes" : "no" ) << endl;


//   cout <<endl<< "Student at 1:  " << *(comp2404.at(1));
//   cout <<endl<< "Student at 3:  " << *(comp2404.at(3));
//   try{
//     cout <<endl<< "Student at 10: " << *(comp2404.at(10));
//   }catch(exception& e){
//     cout<<"Bad index"<<endl;
//   }
  
  


//   cout<<"Sorting students"<<endl;

//   sort(comp2404.begin(), comp2404.end(), compare);

//   cout << endl << "COMP 2404 students:" << endl;

//   for (int i=0; i<comp2404.size(); ++i)
//     cout << *comp2404[i];

//   cout << endl << "Are vectors equal? "<< ( comp2406 == comp2404 ? "yes" : "no" ) << endl;
  

//   vector<Student> vect2;
//   cout << endl << "Adding students to vect2..." << endl;
//   vect2.push_back(matilda);
//   vect2.push_back(joe);
//   vect2.push_back(timmy);

cout << endl << "So long and thanks for all the fish..." << endl;


return 0; 

} 


// bool compare(Student* s1, Student* s2){
//   return s1->gpa < s2->gpa;
// }



