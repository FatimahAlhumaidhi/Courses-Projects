#include <stdio.h>

int main(){
    int num=0;
    printf("Enter a positive integer: ");
    scanf("%d", &num);

    for(int i=num; i>0; i--){
        for(int j=i; j>0; j--){
            printf("*");}
        printf("\n");
    }

    for(int i=1; i<num; i++){
        for(int j=0; j<=i; j++){
            printf("*");}
        printf("\n");
    }

    return 0;
}
