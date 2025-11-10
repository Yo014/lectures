#ifndef TIME_H
#define TIME_H

class Time
{
  public:
    Time(int=0, int=0, int=0);
    void setTime(int, int, int);
    void print() const;

    //LHS argument is *this
    bool operator==(const Time&) const;
    bool operator<(const Time&) const;
    bool operator!=(const Time&) const;
    bool operator<=(const Time&) const;
    bool operator>(const Time&) const;
    bool operator>=(const Time&) const;

    

    Time& operator=(const Time&);



  private:
    int hours;
    int minutes;
    int seconds;
    
    int convertToSecs() const;
};

#endif
