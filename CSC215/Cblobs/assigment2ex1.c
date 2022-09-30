#include <stdio.h>

int main(){
    char str[1000];
    int n = ('a'-'A');
    printf("Enter a sentence: ");
    gets(str);

    int i = 0;
    if(str[i] >= 'a' && str[i] <= 'z'){
        str[i] -= n;
        i++;
    }

    while(str[i] != '\0'){
        if(str[i] == ' '){
            i++;
            if(str[i] >= 'a' && str[i] <= 'z')
                str[i] -= n;
        }
        else{
            if(str[i] >= 'A' && str[i] <= 'Z')
                str[i] += n;
        }

        i++;
    }
    printf("the sentence is: ");
    puts(str);
    return 0;
}
