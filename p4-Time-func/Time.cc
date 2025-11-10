#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

#include "Time.h"

Time::Time(int h, int m, int s)
{
  setTime(h, m, s);
}

bool Time::operator==(Time& t) const
{
  return ( (hours   == t.hours)   &&
           (minutes == t.minutes) &&
           (seconds == t.seconds) );
}

bool Time::operator==(int t) const{
    return convertToSecs() == t;
}

bool operator==(int t1, const Time& t2){
   return t2.convertToSecs() == t1;
  //return t2 == t1;
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

void Time::print() const
{
  cout<<setfill('0')<<setw(2)<<hours<<":"
      <<setfill('0')<<setw(2)<<minutes<<":"
      <<setfill('0')<<setw(2)<<seconds<<endl;
}

