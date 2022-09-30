#include <stdio.h>
#include <stdlib.h>

void extend(int*, int);

int main(){

    int* list; int num;
    printf("How many integers do you want?: \n");
    scanf("%d", &num);
    list = (int*)(malloc(num*(sizeof(int))));
    for(int i=0; i<num; i++){
        printf("Enter the value of integer number %d: ", i+1);
        scanf("%d", (list+i));
    }

    extend(list, num);

    num=0;
    while(*(list+num)>-1){
        num++;
    }

    printf("number of elements is: %d\nThe values in the list: ", num);
    for(int i=0; i<num; i++){
        printf("%d ", *(list+i));
    }
    printf("\nThe size of the dynamic list is: %d Bytes", num*(sizeof(int)));

    return 0;
}

void extend(int* list, int num){
    int i=num+1, input;
    int* exlist;

    printf("exteneded list: -enter negative number when you want to finish- \n");
    do{
        printf("Enter the value of integer number %d: ", i);
        scanf("%d", &input);
        exlist = (int*)(realloc(list,i*(sizeof(int))));
        if(exlist != NULL){
            list = exlist;
            *(list+i-1) = input;
            i++;
        }
        else{
            free(list);
            input = -1;
        }
    }while(input>-1);
}