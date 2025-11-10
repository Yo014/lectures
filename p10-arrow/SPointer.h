#ifndef SPOINTER_H
#define SPOINTER_H

#include "Time.h"

/*
A smart pointer for the Time class
*/
class SPointer{
    public:
        SPointer(Time* t=NULL):t(t){}
        ~SPointer(){
            delete t;
        }

        Time* operator->(){
            return t;
        }

        Time& operator*(){return *t;}

        // this is unnecessary
        SPointer* operator&(){
            return this;
        }


    private:
        Time* t;
};

#endif