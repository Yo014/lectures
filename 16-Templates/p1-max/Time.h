#ifndef TIME_H
#define TIME_H

class Time
{
  friend ostream& operator<<(ostream&, const Time&);

  public:
    Time(int=0, int=0, int=0);
    void setTime(int=0, int=0, int=0);

    bool operator<(const Time& t){
      return convertToSecs()<t.convertToSecs();
    }
    

  private:
    int hours;
    int minutes;
    int seconds;
    int convertToSecs() const;
};

#endif
