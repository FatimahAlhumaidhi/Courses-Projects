#include <stdlib.h>
#include "mystr.h"

/* calculate the length of a string */
int slen(const char* str){
    if(str == NULL)
        return -1;
    
    int i=0;
    while(*(str+i) != '\0')
        i++;
    return i;
}

/* concatenate two strings into a
new string */
char* scat(const char* str1, char* str2){
    int len1 = slen(str1), len2 = slen(str2);
    if((len1+len2)<=0)
        return "";
    
    char* str3 = sdup(str1);
    str3 = (char*)realloc(str3, (len1+len2)*sizeof(char));
    while(*str2 != '\0'){
        *(str3+len1) = *str2++;
        len1++;
    }
    *(str3+len1) = '\0';

    return str3;
}

/* compares two strings s1 and s2, returns an integer less than,
 equal to, or greater than zero if s1 is found, respectively,
 to be less than, to match, or be greater than s2 */
int scmp(const char* str1, const char* str2){
    if(*str1 == '\0'){
        if (*str2 == '\0')
            return 0;
        return -1;
    }
    if(*str2=='\0')
        return +1;

    if(*str1 == *str2)
        scmp((str1+1), (str2+1));
    
    else{
        if(*str1 > *str2)
            return +1;
        return -1;
    }
}

/* returns a new duplicate of the parameter */
char* sdup(const char* str){
    int len = slen(str);
    if(len <= 0)
        return "";
    
    char* str2 = (char*)malloc(len*sizeof(char));
    int i=0;
    while(*(str+i) != '\0'){
        *(str2+i) = *(str+i);
        i++;
    }
    /* 
    int i = len;
    while(i--)
        *str2++ = *str++;
    *(str2+len) = '\0';
    */
    *(str2+i) = '\0';
    return str2;
}

/* returns a new string that is the reverse of the parameter */
char* srev(const char* str){
    int len = slen(str);
    if(len <= 0)
        return "";
    
    char* str2 = (char*)malloc(len*sizeof(char));
    int i=len, j=0;
    while(i--){//while((str+i)!=str)
        *(str2+j) = *(str+i);
        j++;
    }
    //*(str2+j) = *(str);
    *(str2+len) = '\0';
    
    return str2;
}
