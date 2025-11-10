#ifndef BOOKARRAY_H
#define BOOKARRAY_H

#include "Book.h"


class BookArray
{
  friend ostream& operator<<(ostream&, const BookArray&);
  public:
    BookArray(int=10);
    ~BookArray();

    Book& operator[](int);
    const Book& operator[](int) const;
    BookArray& operator=(BookArray&);
    bool operator==(BookArray&);

  private:
    int   capacity;
    Book* elements;
};

#endif

