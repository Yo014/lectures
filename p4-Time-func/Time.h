#ifndef TIME_H
#define TIME_H

class Time
{
  public:
    friend bool operator==(int, const Time&);
    Time(int=0, int=0, int=0);
    void setTime(int, int, int);
    void print() const;

    bool operator==(Time&) const;
    bool operator==(int) const;

  private:
    int hours;
    int minutes;
    int seconds;
    int convertToSecs() const;
};

#endif
