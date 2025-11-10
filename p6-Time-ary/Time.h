#ifndef TIME_H
#define TIME_H

class Time
{
  friend ostream& operator<<(ostream&, Time&);

  public:
    Time(int=0, int=0, int=0);
    void setTime(int, int, int);
    Time& operator+=(int);
    Time operator+(int);
    Time& operator=(int);

  private:
    int   hours;
    int   minutes;
    int   seconds;
    int   convertToSecs() const;
    //set the time in seconds
    void  setTime(int);
};

#endif
