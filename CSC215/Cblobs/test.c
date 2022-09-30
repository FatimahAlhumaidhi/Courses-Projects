#include <stdio.h>
#include <stdlib.h>
#include "pub.h"

int main(){
    int num;
    PubList list;
    printf("enter the number of publications: ");
    scanf("%d", &num);
    list.count = num;
    list.publications = (Publication*)malloc(num*sizeof(Publication));
    for(int i=0; i<num; i++){
       Publication pub = readpub();
       if(findpub(pub, list)==-1){
           list.publications[i] = pub;
       }
       else{
           printf("Publication is already in the list\n");
           i--;
       }
    }

    PubList books = getallbooks(list);
    double sum=0;
    for(int i=0; i<books.count; i++){
        sum += books.publications[i].price;
    }
    printf("All books' prices: %.1lf", sum);
    
    return 0;
}