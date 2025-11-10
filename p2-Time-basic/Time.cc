#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

#include "Time.h"

Time::Time(int h, int m, int s)
{
  setTime(h, m, s);
}



void Time::setTime(int h,int m,int s)
{
  hours   = ( h >= 0 && h < 24) ? h : 0;
  minutes = ( m >= 0 && m < 60) ? m : 0;
  seconds = ( s >= 0 && s < 60) ? s : 0;
}

//RHS is the parameter, LHS is *this
bool Time::operator==(const Time& t) const{
      return (hours == t.hours && minutes == t.minutes && seconds == t.seconds);
}

bool Time::operator<(const Time& t) const{
    return ((*this).convertToSecs() < t.convertToSecs());
}


bool Time::operator!=(const Time& t) const{
  return !(*this == t);   
}

bool Time::operator<=(const Time& t) const{
  return (*this < t || *this == t);   
}

bool Time::operator>(const Time& t) const{
  return !(*this <= t);   
}

bool Time::operator>=(const Time& t) const{
  return !(*this < t);   
}

Time& Time::operator=(const Time& t){
    seconds = t.seconds;
    minutes = t.minutes;
    hours = t.hours;
    return *this;
}

int Time::convertToSecs() const
{
  return (hours*3600 + minutes*60 + seconds);

}

void Time::print() const
{
  cout<<setfill('0')<<setw(2)<<hours<<":"
      <<setfill('0')<<setw(2)<<minutes<<":"
      <<setfill('0')<<setw(2)<<seconds<<endl;
}

