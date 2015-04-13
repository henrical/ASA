#ifndef _GRAPH_H
#define _GRAPH_H

#include "vertex.h"

struct graph{
	int size;
	vertex_t vertex[];
};

typedef struct graph *graph_t;

#endif