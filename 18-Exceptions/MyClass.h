#ifndef MYCLASS_H
#define MYCLASS_H

#include <iostream>
#include <string>
using namespace std;

class MyClass {
public:
	MyClass(string message);
	//MyClass(const MyClass&& mc);
	~MyClass();

	void print();
private:
	string message;
};

#endif




