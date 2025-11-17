#ifndef LIST_H
#define LIST_H

#include <string>
#include <iostream>
#include "Book.h"

using namespace std;

template <class T>
class List
{
  template <class V>
  friend ostream& operator<<(ostream&, const List<V>& list);
  public:
    List();
    ~List();
    bool isEmpty();
    T operator[](int);
    void add(T);
    T remove(int);
    //void print() const;
    

  protected:
    class Node
      {
        public:
          T data;
          Node* next;
      };
    Node* head;

};

template <class T>
List<T>::List() : head(NULL){ }

template <class T>
List<T>::~List()
{
  Node* currNode;
  Node* nextNode;

  currNode = head;

  while (currNode != NULL) {
    nextNode = currNode->next;
    delete currNode;
    currNode = nextNode;
  }
}

template <class T>
bool List<T>::isEmpty(){
  return head == NULL;
}

template <class T>
T List<T>::operator[](int index){
  Node* currNode;
  Node* prevNode;

  currNode = head;
  prevNode = NULL;

  int currIndex = 0;

  while (currIndex < index && currNode!=NULL) {
      currNode = currNode->next;
      ++currIndex;
  }

  if (currNode == NULL){
    cerr<<"Invalid index"<<endl;
    exit(1);
  }else{
    return currNode->data;
  }
}

template <class T>
void List<T>::add(T t)
{
  Node* newNode;
  Node* currNode;
  Node* prevNode;

  newNode = new Node;
  newNode->data = t;
  newNode->next = NULL;

  currNode = head;
  prevNode = NULL;

  while (currNode != NULL) {
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL){
    head = newNode;
  }else{
    prevNode->next = newNode;
  }
  newNode->next = currNode;
}

template <class T>
T List<T>::remove(int index)
{
 
  Node* currNode;
  Node* prevNode;

  currNode = head;
  prevNode = NULL;

  int currIndex = 0;

  while (currIndex < index && currNode!=NULL) {
      prevNode = currNode;
      currNode = currNode->next;
      ++currIndex;
  }

  if (currNode == NULL){
    cerr<<"Invalid index"<<endl;
    exit(1);
  }
  
  T goner = currNode->data;
  if (prevNode != NULL){
    prevNode->next = currNode->next;
  }else{
    head = currNode->next;
  }
  delete currNode;
  return goner;

}

template <class T>
ostream& operator<<(ostream& ost, const List<T>& list){
    typename List<T>::Node* curr = list.head;
    while (curr != nullptr){
      ost<<curr->data<<endl;
      curr = curr->next;
    }
    return ost;

}
#endif

