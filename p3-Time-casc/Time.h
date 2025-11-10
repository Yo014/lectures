#ifndef TIME_H
#define TIME_H

class Time
{
  public:
    Time(int=0, int=0, int=0);
    void print() const;

    Time& setTime(int, int, int);
    Time& setHours(int);
    Time& setMinutes(int);
    Time& setSeconds(int);
    //Time& operator=(const Time&);

  private:
    int hours;
    int minutes;
    int seconds;
};

#endif
