#include <stdio.h>
#include "mat.h"

void fill_matrix(int t[][size]){
    #ifdef size
    printf("Enter %d elements: \n", size*size);
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
           scanf("%d", &t[i][j]); 
        }
    }
    #endif
}

void transpose(int t[][size], int s[][size]){
    #ifdef size
    for(int i=0; i<size; i++){
        for(int j=i; j<size; j++){
            s[j][i] = t[i][j]; 
        }
    }
    #endif
}

void print_matrix(int t[][size]){
    #ifdef size
    printf("{");
    for(int i=0; i<size-1; i++){
        for(int j=0; j<size; j++){
            printf("%d, ", t[i][j]); 
        }
        printf("\n");
    }

    for(int j=0; j<size-1; j++)
        printf("%d, ", t[size-1][j]); 
    
    printf("%d} \n", t[size-1][size-1]);
    #endif
}
