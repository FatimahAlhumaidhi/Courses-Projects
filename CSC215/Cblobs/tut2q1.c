#include <stdio.h>

typedef struct Node Node;
Node* new_node(int);
typedef struct LinkedList LinkedList;
struct Node{
 void* integer;
 Node* next;
};
struct LinkedList {
Node* head;
};

void sort_ll(LinkedList*); //Sorts the nodes in the linked list so they follow an ascending order

void sort_ll(LinkedList* ll){

}