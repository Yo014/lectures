TARGETS	=	p3

all:	$(TARGETS)

p3:	main.o Book.o
	g++ -o p3 main.o Book.o

main.o:	main.cc Book.h List.h
	g++ -c main.cc

Book.o:	Book.cc Book.h
	g++ -c Book.cc

clean:
	rm -f *.o p3
