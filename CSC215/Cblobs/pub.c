#include <stdio.h>
#include <stdlib.h>
#include "pub.h"

Publication readpub(){
    Publication pub;

    printf("enter publication's information: \n");
    printf("publication's type{Book=0, Magazine=1}: ");
    scanf("%d", &(pub.pType));
    printf("publication's title: ");
    scanf("%s", &(pub.title));
    printf("publication's number of pages: ");
    scanf("%d", &(pub.nPages));
    printf("publication's cover type{Hardcover=0, Paperback=1}: ");
    scanf("%d", &(pub.cType));
    printf("publication's price: ");
    scanf("%lf", &(pub.price));
    if(pub.pType){
        printf("publication's ISSN: ");
        scanf("%d", &(pub.MID.ISSN));
        printf("publication's vol: ");
        scanf("%d", &(pub.MID.vol));
        printf("publication's issue: ");
        scanf("%d", &(pub.MID.issue));
    }
    else{
        printf("publication's ISBN ");
        scanf("%d", &(pub.BID.ISBN));
    }
    return pub;
}

int findpub(Publication pub, PubList list){
    int i=0;
    if(pub.pType==Book){
        while(i<list.count){
            if(pub.pType==(list.publications[i]).pType){
                if(((list.publications[i]).BID.ISBN==pub.BID.ISBN)){
                    return i;
                }
            }
            i++;
        }
    }
    else{
        while(i<list.count){
            if(pub.pType==(list.publications[i]).pType){
                if((list.publications[i]).MID.ISSN==pub.MID.ISSN && (list.publications[i]).MID.issue==pub.MID.issue && (list.publications[i]).MID.vol==pub.MID.vol){
                    return i;
                }
            }
            i++;
        }
    }

    return -1;
}

PubList getallbooks(PubList list){
    int i=0, count=0;
    PubList books;
    books.publications = (Publication*)malloc(sizeof(Publication));
    while(i<list.count){
        if((list.publications[i]).pType==Book){ 
            realloc(books.publications, (count+1)*sizeof(Publication));
            books.publications[count++] = (list.publications[i]);
        }
        i++;
    }
    books.count = count;
    return books;
}