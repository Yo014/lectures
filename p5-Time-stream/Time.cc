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

ostream& operator<<(ostream& ost, const Time& t){
  ost<<setfill('0')<<setw(2)<< t.hours<<":"
      <<setfill('0')<<setw(2)<< t.minutes<<":"
      <<setfill('0')<<setw(5)<< t.seconds;
    return ost;
}

//hh:mm:ss
istream& operator>>(istream& is, Time& t){
    int h,m,s;
    
    is>>setw(2)>>h;
    is.ignore();  //throws away :
    is>>setw(2)>>m;
    is.ignore();
    is>>setw(2)>>s;

    t.setTime(h,m,s);
    
    return is;
}

int Time::convertToSecs() const
{
  return (hours*3600 + minutes*60 + seconds);

}

/*void Time::print() const{
  cout<<setfill('0')<<setw(2)<< hours<<":"
      <<setfill('0')<<setw(2)<< minutes<<":"
      <<setfill('0')<<setw(2)<< seconds;
}*/




