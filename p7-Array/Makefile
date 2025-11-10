TARGETS	=	p7

all:	$(TARGETS)

p7:	main.o Book.o BookArray.o
	g++ -o p7 main.o Book.o BookArray.o

main.o:	main.cc Book.h BookArray.h
	g++ -c main.cc

Book.o:	Book.cc Book.h
	g++ -c Book.cc

BookArray.o:	BookArray.cc BookArray.h Book.h
	g++ -c BookArray.cc

clean:
	rm -f *.o p7
