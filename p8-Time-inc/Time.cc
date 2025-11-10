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

void Time::setTime(int s)
{
  hours   = s / 3600;
  minutes = (s % 3600) / 60;
  seconds = (s % 3600) % 60;
}

Time& Time::operator=(const Time& t)
{
  hours   = t.hours;
  minutes = t.minutes;
  seconds = t.seconds;

  return *this;
}

Time& Time::operator++(){
   int t = convertToSecs();
   ++t;
   setTime(t);
   return *this;
}

Time Time::operator++(int){
  Time t2(*this);
  int t = convertToSecs();
   ++t;
   setTime(t);
   return t2;
}

Time& operator--(Time& t){
   int temp = t.convertToSecs();
   --temp;
   t.setTime(temp);
   return t;
}

Time operator--(Time& t, int){
  Time t2(t);  //copy constructor
  int temp = t.convertToSecs();
   --temp;
   t.setTime(temp);
   return t2;
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

