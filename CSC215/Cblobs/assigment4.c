#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include "assigment4.h"


bool countInteger(char* str){
    int num = 0;

    for(char* i=str; (*i!='\0'); i++){
        if(*i>='0' && *i<='9'){
            num++;
            for(char* j=i+1; (*j>='0' && *j<='9'); j++){
                num++;
            }
            if(num==9){
                return true;
            }
            num=0;
        }
    }
    return false;
}

char* uniEmail(char* str){
    while(!countInteger(str)){
        printf("Please enter a valid full name and student ID, seperate the two by a comma: ");
        gets(str);
    }
    if(countInteger(str)){
        char* index = strrchr(str, ',');
        char* str1 = str;
        char str2[100];
        if(*(index+2)>='0' && *(index+2)<='9'){
            str1 = (index+2);
            strcat(str1, "@STUDENT.KSU.EDU.SA");
        }
        else{
            int I = (int)(index-str);
            strncpy(str1, str, I); //here
            *(str1+I) = '\0';
            strcat(str1, "@STUDENT.KSU.EDU.SA");
        }
        return str1;
    }
    return NULL;
}

char* lastWord(char* str){
    char* index = strrchr(str, ' '); 
    char* str1 = (index+1);
    return str1;
}

void reform(char* str){
    char* index = strrchr(str, ',');
    char* str1 = str;
    int I = (int)(index-str);
    if(*(index+2)>='0' && *(index+2)<='9'){
        strncpy(str1, str, I);
        *(str1+I) = '\0';
    }
    else{
        str1 = (index+2);
    }
    str = lastWord(str1);
    strcat(str, ", ");
    index = strchr(str1, ' ');
    strncpy(index, (index+1), 1);
    *(index+1) = '\0';
    strcat(str, index);
    strcat(str, ". ");
    index = strchr(str, ' ');
    I = (int)(index-str);
    strncat(str, str1, I);
}

