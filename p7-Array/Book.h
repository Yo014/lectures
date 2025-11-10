#ifndef BOOK_H
#define BOOK_H

class Book
{
  friend ostream& operator<<(ostream&, const Book&);

  public:
    Book(string="unknown title", string="unknown author");
    Book(Book&);
    static int getNextId();
    string getTitle() const;
    string getAuthor() const;
    Book& operator=(const Book&);
    bool  operator!=(Book&);

  private:
    static int nextId;
    int id;
    string title;
    string author;
};

#endif
