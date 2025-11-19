#ifndef STUDENT_H
#define STUDENT_H


class Student
{
  
  friend ostream& operator<<(ostream&, const Student&);
  public:
    Student(string="000000000", string="Hey you!", string="basket-weaving", float=0.0f);
    Student(const Student&);
    ~Student();

    bool operator<(const Student&) const;
    bool operator==(const Student&) const;

    const string& getName()const {return name;}

  struct cmp{
			bool operator()(const Student& s1, const Student s2) const{
				return s1.name < s2.name;
			}
		};
    

  private:
    string  number;
    string  name;
    string  majorPgm;
    float   gpa;
};

#endif
