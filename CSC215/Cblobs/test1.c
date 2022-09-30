#include <stdio.h>
#include <stdbool.h>
#include "assigment4.h"
#define size 100

int main(){
    char str[size];
    printf("Enter your full name and student ID, seperate the two by a comma: ");
    gets(str);

    printf("%s\n", (countInteger(str)? "true":"false"));
    printf("%s\n", lastWord(str));
    printf("%s\n", uniEmail(str));
    reform(str);
    printf("%s", str);

}