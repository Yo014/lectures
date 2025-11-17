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

int Time::convertToSecs() const
{
  return (hours*3600 + minutes*60 + seconds);
}

ostream& operator<<(ostream& output, const Time& t)
{
  output<<setfill('0')<<setw(2)<<t.hours<<":"
      <<setfill('0')<<setw(2)<<t.minutes<<":"
      <<setfill('0')<<setw(2)<<t.seconds;

  return output;
}


