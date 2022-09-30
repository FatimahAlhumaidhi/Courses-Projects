#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define n 5
#define swap(x, y) int t; t=x; x=y; y=t;

void BruteForce(int[]);
int* merge(int[], int[]);
void Epicfail(int[]);


int main(){
    int arr1[n] = {0, 1, 6, 7, 9};
    int arr2[n] = {2, 3, 4, 5, 8};

    int* arr3 = merge(arr1, arr2);
    double mid = (arr3[((2*n)/2)-1]+arr3[((2*n)/2)])/2.0;
    printf("%.1f", mid);
    
}


int* merge(int arr1[], int arr2[]){
    int i=0, j=0, k=0;
    int* arr = (int*)malloc((2*n)*sizeof(int));

    while(i<n && j<n){
        if(arr1[i] <= arr2[j]){
            arr[k++] = arr1[i++];
        }
        else{
            arr[k++] = arr2[j++];
        }
    } 

    if(i<n){
        while(i<n){
            arr[k++] = arr1[i++];
        }
    } 
    else{
        while(j<(2*n)){
            arr[k++] = arr2[j++];
        }
    }
    return arr;
}//time complixity: theta(n) space complixity: theta(n)

/* def merge(arr1[], arr2[], n){
    i=0, j=0, k=0
    arr = [0 for i int range(0, n)]

    while(i<n && j<n)
        if(arr1[i] <= arr2[j])
            arr[k++] = arr1[i++]
        
        else
            arr[k++] = arr2[j++]
        
    }

    if(i<n)
        while(i<n)
            arr[k++] = arr1[i++]
        
    
    else
        while(j<(2*n))
            arr[k++] = arr2[j++]
        
    
    return arr;
}//time complixity: theta(n) space complixity: theta(n)*/




void Epicfail(int arr[2*n]){

    int i=0, j=n;
    while(i<n && j<(2*n)){
        if(arr[i]<=arr[j]){
            if(j==n){
                i++;
            }
            else{
                swap(arr[i], arr[j-1]);
                j=n;
            }
        }
        else{ 
            if(i==(n-1) && j==((2*n)-1)){
                swap(arr[i], arr[j]);
                j=n;
            }
            else{
                j++;
            }
        }
    }
}//time complixity: O(n^3)! and space complixity: theta(1)

void BruteForce(int arr[2*n]){

    for(int i=0; i<(2*n)-2; i++){
        int min = i;
        for(int j=i+1; j<(2*n)-1; j++){
            if(arr[j]<arr[min]){
                min = j;
            }
        }
        swap(arr[i], arr[min]);
    }

}// time complixity: theta(n^2) and space complixity: theta(1)