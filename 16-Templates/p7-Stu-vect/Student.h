#ifndef STUDENT_H
#define STUDENT_H


class Student
{
  // can add a compare global function
  friend bool compare(Student*, Student*);
  friend ostream& operator<<(ostream&, const Student&);
  public:
    Student(string="000000000", string="Hey you!", string="basket-weaving", float=0.0f);
    Student(const Student&);
    ~Student();
    bool operator==(const Student&) const;
    // can use a compare member function

  private:
    string  number;
    string  name;
    string  majorPgm;
    float   gpa;
};

#endif
