#include <stdio.h>
#include <stdlib.h>

int M(int);

int main(){
    int f[] = {M(0), M(1), M(2), M(3), M(4), M(5), M(6), M(7), M(8), M(9), M(10)};
    for(int i=0; i<=10; i++){
        printf("M(%d) = %d \n", i, f[i]);
    }
    return 0;
}

int M(int n){
    int d[3] = {1, 3, 4};
    int* f = (int*)malloc((n+1)*(sizeof(int)));
    for(int i=0; i<=n; i++){
        f[i]=0;
    }
    f[0]=1; f[1]=1;

    for(int i=2; i<=n; i++){
        int j=0;
        while(i>=d[j]){
            f[i] = f[i] + f[i-d[j]];
            j++;
        }
    }
    return f[n];
}