main: main.o graph.o node.o
	g++ -o main main.o node.o graph.o

node.o: node.cpp node.h
	g++ -c node.cpp
	
graph.o: graph.cpp graph.h
	g++ -c graph.cpp
	
main.o: main.cpp 
	g++ -c main.cpp
		
