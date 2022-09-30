#include <stdio.h>

int main(){

    char str[1000];
    int num[] = {0, 0, 0, 0, 0};
    printf("Enter a sentence:\n");
    gets(str);

    int i = 0;
    while(str[i] != '\0'){
        if(str[i] == 'A' || str[i] == 'a'){
            num[0]++;
        }
        else if(str[i] == 'E' || str[i] == 'e'){
            num[1]++;
        }
        else if(str[i] == 'I' || str[i] == 'i'){
            num[2]++;
        }
        else if(str[i] == 'O' || str[i] == 'o'){
            num[3]++;
        }
        else if(str[i] == 'U' || str[i] == 'u'){
            num[4]++;
        }
        i++;
    }


    printf("A/a: %d E/e: %d I/i: %d O/o: %d U/u: %d", num[0], num[1], num[2], num[3], num[4]);

    return 0;
}