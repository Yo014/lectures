#include <iostream>
using namespace std;
#include <string>
#include <vector>


/* * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*          ANIMAL CLASS DEFINITIONS                   */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * */


class Animal
{
  public:
    Animal(string n="") : name(n) { }
    virtual ~Animal()      { }
    virtual void sing() = 0;

  protected:
    string name;
};

class Bird : public Animal
{
  public:
    Bird(string n="") : Animal(n) { }
    virtual ~Bird()      { }
    virtual void sing()  { cout<< "-- bird "<<name<<" says tweet-tweet!"<<endl; }
};

class Chicken : public Bird
{
  public:
    Chicken(string n="") : Bird(n) { }
    virtual ~Chicken()   { }
    virtual void sing()  { cout<< "-- chicken "<<name<<" says cluck-cluck!"<<endl; }
};

class Cat : public Animal
{
  public:
    Cat(string n="") : Animal(n) { }
    virtual ~Cat()       { }
    virtual void sing()  { cout<< "-- cat "<<name<<" says meow!"<<endl; }
};

class Pig : public Animal
{
  public:
    Pig(string n="") : Animal(n) { }
    virtual ~Pig()       { }
    virtual void sing()  { throw (string)"Pigs don't sing !!!"; }
};


/* * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*          MAIN PROGRAM                               */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * */

int main()
{
  /*  Object declarations  */
  Chicken*  redHen   = new Chicken("Little Red Hen");
  Cat*      lady     = new Cat("Lady");
  Pig*      wilbur   = new Pig("Wilbur");

  vector<Animal*> barnyard;

  barnyard.push_back(redHen);
  barnyard.push_back(wilbur);
  barnyard.push_back(lady);

  cout<<endl<<"Barnyard harmony:"<<endl;

  for (int i=0; i<barnyard.size(); ++i) {
     try{
      barnyard[i]->sing();
     }catch(string error){
       cout<<"Someone complained!"<<endl;
     }
  }

  for (int i=0; i<barnyard.size(); ++i)
    delete barnyard[i];


  cout<<endl;

  return 0;
}

