p6:	main.o Time.o
	g++ -o p6 main.o Time.o

main.o:	main.cc Time.h
	g++ -c main.cc

Time.o:	Time.cc Time.h
	g++ -c Time.cc

clean:
	rm -f *.o p6
