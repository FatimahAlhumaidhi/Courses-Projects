#include <stdio.h>
#include <stdlib.h>
#include "string.h"

#define SIZE 250


struct symbol{
	char Name[SIZE];
	int Type;
};


void Insert(struct symbol*);
struct symbol* Lookup(char*);
unsigned long hash(unsigned char*);

