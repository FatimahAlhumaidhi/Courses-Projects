#include <stdio.h>

int findLowest(float scores[]){
    int min = 0;
    for(int i=1; i<4; i++){
        if(scores[i] < scores[min])
            min = i;
    }
    return min;
}

float Avg(float scores[]){
    int lowest = findLowest(scores);
    float sum = 0.0;
    for(int i=0; i<4; i++){
        if(i != lowest){
            sum += scores[i];
        }     
    }
    return (sum/3);
}

int main(){
    float scores[5];
    printf("Enter 4 scores:\n");
    for(int i=0; i<4; i++){
        scanf("%f", &scores[i]);
    }

    float average = Avg(scores);

    printf("%.1f", average);
    return 0;
}

