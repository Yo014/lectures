#ifndef BOOK_H
#define BOOK_H

class Book
{
  friend ostream& operator<<(ostream&, const Book&);

  public:
    Book(const string& ="", const string& ="");
    Book(const Book&);
    static int getNextId();
    string getTitle() const;
    string getAuthor() const;

  private:
    static int nextId;
    int id;
    string title;
    string author;
};

#endif
