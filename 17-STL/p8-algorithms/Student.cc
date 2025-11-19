#include <iostream>
#include <iomanip>
using namespace std;
#include <string>

#include "Student.h"


Student::Student(string s1, string s2, string s3, float g)
    : number(s1), name(s2), majorPgm(s3), gpa(g) 
{ 
  cout<<"-- Student ctor:  "<< name<<endl;
}

Student::Student(const Student& stu)
{ 
  cout<<"-- Student copy ctor:  "<< stu.name<<endl;
  name     = stu.name;
  number   = stu.number;
  majorPgm = stu.majorPgm;
  gpa      = stu.gpa;
}



Student::~Student() 
{ 
  cout<<"-- Student dtor:  "<< name<<endl;
}

bool Student::operator<(const Student& stu) const{
    return name < stu.name;
}

bool Student::operator==(const Student& stu) const{
    return (name == stu.name &&
            number == stu.number &&
            majorPgm == stu.majorPgm &&
            gpa == stu.gpa);
}

Student& Student::operator=(const string& name){
  this->name = name;
  return *this;
}


ostream& operator<<(ostream& output, const Student& stu)
{
  output<<"Student:  " << stu.number << "  " << left << setw(10) << stu.name << " "
                       << setw(15) << stu.majorPgm << "   GPA: "
                       << fixed << setprecision(2) << setw(5) << right << stu.gpa << endl;
  return output;
}

bool operator==(const Student& stu, string name){
    return stu.name == name;
}


