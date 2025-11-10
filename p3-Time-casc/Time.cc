#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

#include "Time.h"

Time::Time(int h, int m, int s)
{
  setTime(h, m, s);
}

Time& Time::setTime(int h,int m,int s)
{
  setHours(h);
  setMinutes(m);
  setSeconds(s);

  return *this;

}

Time& Time::setHours(int h)
{
  hours = ( h >= 0 && h < 24) ? h : 0;
  return *this;
}

Time& Time::setMinutes(int m)
{
  minutes = ( m >= 0 && m < 60) ? m : 0;
  return *this;
}

Time& Time::setSeconds(int s)
{
  seconds = ( s >= 0 && s < 60) ? s : 0;
  return *this;
}

void Time::print() const
{
  cout<<setfill('0')<<setw(2)<<hours<<":"
      <<setfill('0')<<setw(2)<<minutes<<":"
      <<setfill('0')<<setw(2)<<seconds<<endl;
}

