#include <iostream>
using namespace std;
#include <string>

#include <vector>
#include <iterator>
#include <algorithm>


int main()
{
  vector<string> words1;
  string str;

  cout<<"Enter words <ending with \"end\">: ";
  cin>>str;

  while (str != "end") {
    words1.push_back(str);
    cin>>str;
  }

  ostream_iterator<string> outItr(cout, " * ");

  cout<<"Your words are:  ";
  copy(words1.begin(), words1.end(), outItr);
  cout<<endl;

  cout<<"size:     "<<words1.size()<<endl;
  cout<<"capacity: "<<words1.capacity()<<endl<<endl;

  // cout<<"Your words reversed are:  ";
  // copy(words1.rbegin(), words1.rend(), outItr);
  // cout<<endl;

  // vector<string> words2 = words1;
  // cout << "Words after copy ctor: ";
  // copy(words2.begin(), words2.end(), outItr);
  // cout<<endl;
  // cout<<"size:     "<<words2.size()<<endl;
  // cout<<"capacity: "<<words2.capacity()<<endl<<endl;

  // words2.insert(words2.begin() + 2, "PANIC");
  // cout << "Words after insert : ";
  // copy(words2.begin(), words2.end(), outItr);
  // cout<<endl;
  // cout<<"size:     "<<words2.size()<<endl;
  // cout<<"capacity: "<<words2.capacity()<<endl<<endl;

  // words2.erase(words2.begin(), words2.end());
  // cout << "Words after erase : ";
  // copy(words2.begin(), words2.end(), outItr);
  // cout<<endl;
  // cout<<"size:     "<<words2.size()<<endl;
  // cout<<"capacity: "<<words2.capacity()<<endl<<endl;

  return 0;
}

