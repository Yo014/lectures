#include <iostream>
using namespace std;
#include <iomanip>
#include <string>
#include "Book.h"

int Book::nextId = 1001;

Book::Book(string t, string a)
{
  id     = nextId++;
  title  = t;
  author = a;
}

Book::Book(Book& oldBook)
{
  id     = oldBook.id;
  title  = oldBook.title;
  author = oldBook.author;
}

int Book::getNextId()
{ 
  return nextId; 
}

Book& Book::operator=(const Book& b)
{
  id     = b.id;
  title  = b.title;
  author = b.author;

  return *this;
}

bool Book::operator!=(Book& b)
{
  return ( title != b.title || author != b.author );
}

ostream& operator<<(ostream& output, const Book& b)
{
  output<< b.id<< setw(35) << b.title <<" -by- "<<b.author << endl;
  return output;
}

string Book::getTitle()  const { return title; }

string Book::getAuthor() const { return author; }

