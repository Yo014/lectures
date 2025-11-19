#ifndef STUDENT_H
#define STUDENT_H


class Student
{
  friend bool pass(Student&);
  friend void printGPA(Student&);
  friend ostream& operator<<(ostream&, const Student&);
  friend bool operator==(const Student&, string);
  public:
    Student(string="000000000", string="Hey you!", string="basket-weaving", float=0.0f);
    Student(const Student&);
    ~Student();
    bool operator<(const Student&) const;
    bool operator==(const Student&) const;
    Student& operator=(const string&);

    static bool comp(Student& s1, Student& s2){
      return s1.gpa > s2.gpa;
    }
    

  private:
    string  number;
    string  name;
    string  majorPgm;
    float   gpa;
};

#endif
