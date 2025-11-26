#include "MyClass.h"

MyClass::MyClass(string message):message(message){cout<<"in ctor..."<<endl;}

// MyClass::MyClass(const MyClass&& mc){
// 	cout<<"In copy constructor..."<<endl;
// 	message = mc.message;
// }

MyClass::~MyClass(){}



void MyClass::print(){
	cout<<message<<endl;
}
