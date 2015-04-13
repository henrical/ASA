#ifndef _EDGE_H
#define _EDGE_H

#include "vertex.h"

typedef struct edge{
	struct vertex *vertex;
	int weight;
} *edge_t;



#endif 