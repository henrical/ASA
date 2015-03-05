all:
	(rm -f main *.o)
	(g++ -c node.cpp)
	(g++ -c graph.cpp)
	(g++ -c main.cpp)
	(g++ -o main main.o node.o graph.o)
	
clean: 	
	(rm main *.o)
