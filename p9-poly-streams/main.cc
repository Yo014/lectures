#include <iostream>
using namespace std;

class Animal
{
  friend ostream& operator<<(ostream&, const Animal&);
  public:
    Animal(string n="") : name(n) { }
    

  protected:
    virtual void print(ostream&) const = 0;
    string name;
};

class Bird : public Animal
{
  public:
    Bird(string n="") : Animal(n) { }
  protected:
    virtual void print(ostream& os) const{ os<< "-- bird "<<name<<" says tweet tweet!"<<endl; }
};

class Chicken : public Bird
{
  public:
    Chicken(string n="") : Bird(n) { }
  protected:
    virtual void print(ostream& os) const { os<< "-- chicken "<<name<<" says cluck-cluck!"<<endl; }
};

ostream& operator<<(ostream& os, const Animal& a){
    //  os<< "-- animal "<<a.name<<" makes noise!"<<endl; 
    a.print(os);
    return os;
}



int main()
{
  Bird bird("Big Bird");
  Chicken chick("Kentucky Fried");

  cout<<endl<<"Outputing Bird with Bird handle:"<<endl<<bird<<endl<<endl;
  //bird.print();
  ///cout<<endl;
  cout<<"Outputing Chicken with Chicken handle:"<<endl<<chick<<endl<<endl;
  // chick.print();
  // cout<<endl;

  Animal* ap1 = &bird;
  Animal* ap2 = &chick;

  cout<<"Outputing Bird with Animal handle:"<<endl<<*ap1<<endl<<endl;
  // ap1->print();
  // cout<<endl;

  cout<<"Outputing Chicken with Animal handle:"<<endl<<*ap2<<endl<<endl;
  // ap2->print();
  // cout<<endl;

  return 0;
}
