#ifndef _VERTEX_H
#define _VERTEX_H

#include "edge.h"

struct edge;

typedef struct vertex{
	int value;
	struct edge *edges;
} *vertex_t;

void init_vertex(vertex_t v, int val, int edges);
int get_vertex_value(vertex_t v);
void set_vertex_value(vertex_t v, int val);

void add_edge(vertex_t v, int weight, int destination);
struct edge** get_adj_edges(vertex_t v);

#endif 