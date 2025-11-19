#include <iostream>
using namespace std;
#include <string>

#include <iterator>


int main()
{

  ostream_iterator<string> outItr(cout);

  *outItr ="Enter two words: ";

  istream_iterator<string> inItr(cin);

  string w1, w2;

  w1 = *inItr;

  ++inItr;  //read from the keyboard

  w2 = *inItr;


  *outItr = "Your words are: ";
  *outItr = w1 + " " + w2 +"\n";
  ++outItr;   //this does nothing
  *outItr ="Huh?";

  return 0;
}

