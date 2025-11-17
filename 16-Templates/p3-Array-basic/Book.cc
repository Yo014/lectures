#include <iostream>
using namespace std;
#include <string>
#include "Book.h"

int Book::nextId = 1001;

Book::Book(const string& t, const string& a)
{
  id     = nextId++;
  title  = t;
  author = a;
}

Book::Book(const Book& oldBook)
{
  id     = oldBook.id;
  title  = oldBook.title;
  author = oldBook.author;
}

int Book::getNextId() { return nextId; }

ostream& operator<<(ostream& output, const Book& b)
{
  output<<"** "<< b.title <<" by "<<b.author;
  return output;
}

string Book::getTitle()  const { return title; }
string Book::getAuthor() const { return author; }

