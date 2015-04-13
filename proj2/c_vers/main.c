#include <stdio.h>

#include "vertex.h"
#include "edge.h"
#include "graph.h"

int main()
{
	vertex_t v;
	edge_t e;

	set_vertex_value(v, 3);
	printf("%d\n",get_vertex_value(v));
	return 0;
}