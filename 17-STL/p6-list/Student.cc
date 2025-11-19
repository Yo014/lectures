#include <iostream>
#include <iomanip>
using namespace std;
#include <string>

#include "Student.h"


Student::Student(string s1, string s2, string s3, float g)
    : number(s1), name(s2), majorPgm(s3), gpa(g) {}

Student::Student(const Student& stu)
{ 
  name     = stu.name;
  number   = stu.number;
  majorPgm = stu.majorPgm;
  gpa      = stu.gpa;
}

Student::~Student() {}

bool Student::operator<(const Student& stu)const 
{
  return majorPgm < stu.majorPgm;
}

string Student::getName(){
  return name;
}

bool Student::compGPA(const Student& s1, const Student& s2){
    return s1.gpa < s2.gpa;
}

ostream& operator<<(ostream& output, const Student& stu)
{
  output<<"Student:  " << stu.number << "  " << left << setw(10) << stu.name << " "
                       << setw(15) << stu.majorPgm << "   GPA: "
                       << fixed << setprecision(2) << setw(5) << right << stu.gpa << endl;
  return output;
}

