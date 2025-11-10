#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

#include "Time.h"

Time::Time(int h, int m, int s)
{
  cout<<" in ctor "<<endl;
  setTime(h, m, s);
}

// set this time object to given hours, minutes, seconds
void Time::setTime(int h,int m,int s)
{
  hours   = ( h >= 0 && h < 24) ? h : 0;
  minutes = ( m >= 0 && m < 60) ? m : 0;
  seconds = ( s >= 0 && s < 60) ? s : 0;
}

// set this time object to given seconds
void Time::setTime(int s)
{
  hours   = s / 3600;
  minutes = (s % 3600) / 60;
  seconds = (s % 3600) % 60;
}

int Time::convertToSecs() const
{
  return (hours*3600 + minutes*60 + seconds);
}

Time& Time::operator+=(int t){
     int time = convertToSecs()+t;
     setTime(time);
     return *this;
}

Time Time::operator+(int t){
   Time time;
   time.setTime(convertToSecs()+t);
   return time;
}

Time& Time::operator=(int t){
  setTime(t);
  return *this;
}




ostream& operator<<(ostream& output, Time& t)
{
  output<<setfill('0')<<setw(2)<<t.hours<<":"
      <<setfill('0')<<setw(2)<<t.minutes<<":"
      <<setfill('0')<<setw(2)<<t.seconds;

  return output;
}

