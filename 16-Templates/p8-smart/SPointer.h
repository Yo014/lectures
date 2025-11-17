#ifndef SPOINTER_H
#define SPOINTER_H

#include "Time.h"

/*
A smart pointer for the Time class.
*/
template <class T>
class SPointer{
    public:
        SPointer(T* t=nullptr):t(t){}
        ~SPointer(){
            delete t;
        }

        T* operator->(){
            return t;
        }

        T& operator*(){return *t;}

    private:
        T* t;
};

#endif