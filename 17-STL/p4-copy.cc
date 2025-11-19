#include <iostream>
using namespace std;
#include <string>

#include <vector>
#include <iterator>
#include <algorithm>


int main()
{
  vector<string> words;
  string str;

  cout<<"Enter words <ending with \"end\">: ";
  cin>>str;

  while (str != "end") {
    words.push_back(str);
    cin>>str;
  }

  ostream_iterator<string> outItr(cout);

  cout<<"Your words are: "<<endl;

  copy(words.begin(), words.end(), outItr);

  cout<<endl;

  ostream_iterator<string> outItr2(cout, " * ");

  copy(words.begin(), words.end(), outItr2);

  cout<<endl;

  return 0;
}

