all:
	(rm -f main *.o)
	(g++ -c queue.cpp)
	(g++ -c node.cpp)
	(g++ -c graph.cpp)
	(g++ -c main.cpp)
	(g++ -O3 -ansi -Wall -o main main.o node.o graph.o queue.o -lm)
	
	
clean: 	
	(rm main *.o)
