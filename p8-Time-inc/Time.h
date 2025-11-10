#ifndef TIME_H
#define TIME_H

class Time
{
  friend ostream& operator<<(ostream&, const Time&);
  friend Time& operator--(Time&);
  friend Time operator--(Time&, int);

  public:
    Time(int=0, int=0, int=0);
    void setTime(int, int, int);
    Time& operator=(const Time&);

    Time& operator++();
    Time operator++(int);


  private:
    int   hours;
    int   minutes;
    int   seconds;
    int   convertToSecs() const;
    void  setTime(int);
};

#endif
