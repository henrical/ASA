all:
	(rm -f main *.o)
	(gcc -c vertex.c)
	(gcc -c edge.c)
	(gcc -c graph.c)
	(gcc -c main.c)
	(gcc -O3 -ansi -Wall -o main main.o vertex.o edge.o graph.o -lm)
	
	
clean: 	
	(rm main *.o)
