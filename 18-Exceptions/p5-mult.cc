#include <iostream>
using namespace std;
#include <string>
#include <vector>


/* * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*          ANIMAL CLASS DEFINITIONS                   */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * */


class Animal { };

class Bird : public Animal { 
 
};

class Chicken : public Bird { };

class Cat : public Animal { };

class Pig : public Animal { };


/* * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*          MAIN PROGRAM                               */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * */

int main()
{
  /*  Object declarations  */
  Chicken  redHen;
  Cat      lady;
  Pig      wilbur;

  string choice;

  cout<<"Select your activity: ";
  cin >> choice;

  try {
    if (choice == "Bird") {
      throw Bird();
    }
    else if (choice == "Chicken") {
      throw redHen;
    }
    else if (choice == "Cat") {
      throw lady;
    }
    else if (choice == "Pig") {
      throw wilbur;
    }
    else
      throw "whatever";
  }
  
  catch(Bird& a) {
    cout<<"Bird alert!"<<endl;
  }

  catch(Chicken& a) {
    cout<<"Chicken alert!"<<endl;
  }
  
  catch(Cat& a) {
    cout<<"Cat alert!"<<endl;
  }
  catch(Pig& a) {
    cout<<"Pig alert!"<<endl;
  }


  catch(...){
    cout<<"Caught something"<<endl;
  }
  


  return 0;
}

