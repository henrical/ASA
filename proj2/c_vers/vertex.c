#include "vertex.h"

int get_vertex_value(vertex_t v)
{
 	return v->value;
}

void set_vertex_value(vertex_t v, int val)
{
	v->value = val;
}

//void add_edge(vertex_t v, int weight, int destination){}
