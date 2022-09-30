#include <stdio.h>

void reverse_string(char *str);

int main(){

    char str[1000];
    printf("enter a sentence: ");
    gets(str);
    reverse_string(str);
    puts(str);
    return 0;
}

void reverse_string(char *str){
    int len = 0;
    for(int i=0; str[i] != '\0'; i++){
        len++;
    }
    for (int i = 0, j = len-1; i < j; i++, j--) {
         int temp = str[i];
         str[i] = str[j];
         str[j] = temp;
    }
}