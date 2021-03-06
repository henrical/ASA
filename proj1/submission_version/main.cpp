#include <iostream>
#include <stdio.h>
#include <limits>
#include <cstring>
#include <vector>

#define NIL -1
#define WHITE 0
#define GREY 1
#define BLACK 2

class Node {
 
  private:
    const int value;
    
    
  public:
    Node* adjecent_node;
  
    
    explicit Node(int val)
    :value(val)
    {
      adjecent_node = NULL;
    }
    
    explicit Node(Node*& node)
    :value(node->getNodeValue()),adjecent_node(node->adjecent_node)
    {}
    
    ~Node(){} 
  
  
    const int getNodeValue() const;
    
    void addAdjecent(int val);
    
    void printAdjecentNodes() const;
    
    std::vector<int> getAdjecentNodes() const;
  
  
};

typedef struct adjecency_list
{  
  Node **list;
  int graph_size;
  
} *list_t;




class QueueNode{

  public:
    int *queue;
    int front; //the first element
    int rear; //the last element
  
    
    //constructors
    explicit QueueNode(int size)
    {
        queue = new int[size];
        front = 0;
        rear = 0;
    }
    
    //destructor
    ~QueueNode(){} 
    
    void push(int val);
    int pop() ;
    
    void print() const;
    
    bool isEmpty() const
    {
     return front==rear;
    }
  
  
};

list_t list_init(int size)
{
    int i;
  
    list_t adj_list = new adjecency_list; 
    
    adj_list->graph_size= size;
    
    adj_list->list=new Node*[size];
    
    for(i=0; i<size; i++)
    {
      adj_list->list[i] = new Node(i+1);
    }
    
    return adj_list;
}

void list_insert_edge(list_t graph, int origin, int destination)
{ 
  if(origin>graph->graph_size || destination>graph->graph_size) {
    return;
  }
  
  graph->list[origin-1]->addAdjecent(destination);
}
  
  

void breadth_first_search(list_t graph, int orig)
{
  
  std::vector<int> adj;
  
  int size = graph->graph_size;
  QueueNode *queue = new QueueNode(size+1);
  
  size_t c;
  int i,u, vertex_index;
  int M = 0;
  
  int color[size];
  int predecessor[size];
  int distance[size];
  
  memset(color, WHITE, sizeof(color));
  memset(predecessor, NIL, sizeof(predecessor));
  
  for(i=0; i<size; i++)
  { 
    distance[i] = 0;
  }
  
  
  queue->push(orig);
  color[orig-1]=GREY;
  i=1;

  
  while(!queue->isEmpty())
  {
    i++;
    
    u = queue->pop();
    
    adj = graph->list[u-1]->getAdjecentNodes();
    
    for(c=0; c<adj.size(); c++)
    {
      vertex_index = adj[c]-1;
    
      if(color[vertex_index]==WHITE)
      {
	color[vertex_index]=GREY;
	distance[vertex_index] = distance[u-1] + 1;
	if(distance[vertex_index]>M)
	  M = distance[vertex_index];
	predecessor[vertex_index] = u;
	
	queue->push(adj[c]);
      }
      
    }  
  }
  
    
    std::cout << M << std::endl;
    
    int dist_count[M];
    memset(dist_count, 0, sizeof(dist_count));
    
    for(i=0; i<size; i++)
    {
      if(distance[i]==0)
	continue;
      else
	dist_count[distance[i]-1]++; 
    }
    
    for(i=0; i<M; i++)
    {
      std::cout << dist_count[i] << std::endl;
    }
    
}  
    
const int Node::getNodeValue() const
{
  return value;
}


void Node::addAdjecent(int val)
{ 
  Node* new_node = new Node(val);
  
  if(adjecent_node==NULL)
  {
     adjecent_node = new_node;
  }
  else
  {
    new_node->adjecent_node = adjecent_node;
    
    adjecent_node = new Node(new_node);
    
    delete new_node;
  }
}

std::vector<int> Node::getAdjecentNodes() const
{
  Node *node_iterator = adjecent_node;
  std::vector<int> result;
  
  while(node_iterator!=NULL)
    {
      result.push_back(node_iterator->getNodeValue());
      
      node_iterator = node_iterator->adjecent_node;
    }
    
  return result;
}



void QueueNode::push(int val)
{ 
    queue[rear] = val;
    rear++;
}

int QueueNode::pop()
{ 
    int result;

    result = queue[front];
    front++;
    return result;
}






int main()
{
  list_t graph;
  
  int i;
  
  int temp1;
  int temp2;
  
  int edge_num;
  int orig_node;
  
  if(scanf("%d %d", &temp1,&edge_num)){}
  
  graph = list_init(temp1);
  
  if(scanf("%d", &orig_node))
    
  for(i=0; i<edge_num; i++)
  {
    
      if(scanf("%d %d", &temp1, &temp2)){}
      
      list_insert_edge(graph, temp1, temp2);
      list_insert_edge(graph, temp2, temp1);
  }
  
  
  breadth_first_search(graph,orig_node);
}

