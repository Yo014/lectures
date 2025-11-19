#ifndef STUDENT_H
#define STUDENT_H


class Student
{
  friend ostream& operator<<(ostream&, const Student&);
  public:
    Student(string="000000000", string="Hey you!", string="basket-weaving", float=0.0f);
    Student(const Student&);
    ~Student();

    string getName();

    bool operator<(const Student&) const;

    static bool compGPA(const Student&, const Student&);

  private:
    string  number;
    string  name;
    string  majorPgm;
    float   gpa;
};

#endif
