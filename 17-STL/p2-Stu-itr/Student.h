#ifndef STUDENT_H
#define STUDENT_H


class Student
{
  friend ostream& operator<<(ostream&, const Student&);
  public:
    Student(string="000000000", string="Hey you!", string="basket-weaving", float=0.0f);
    Student(const Student&);
    void setName(const string);
    ~Student();

  private:
    string  number;
    string  name;
    string  majorPgm;
    float   gpa;
};

#endif
