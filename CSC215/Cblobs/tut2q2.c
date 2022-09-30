#include <stdio.h>

typedef struct Stack Stack;
Stack* new_stack();
void* pop(Stack* s);
void push(Stack* s, void* data);
typedef struct Queue Queue;
Stack* new_queue();
void* serve(Queue* q);
void enqueue(Queue* q, void* data);
//Stack S1 is empty, and Stack S2 is not. Write the statements required to copy elements of S2 to S1 in the same order.