all:	p1 p2 p5

p1:	p1-except.cc MyClass.o
	g++ -o p1 p1-except.cc MyClass.o
	
MyClass.o: MyClass.h MyClass.cc
	g++ -c MyClass.cc

p2:	p2-basic.cc
	g++ -o p2 p2-basic.cc

p5:	p5-mult.cc
	g++ -o p5 p5-mult.cc

clean:
	rm -f *.o p1 p2 p5
