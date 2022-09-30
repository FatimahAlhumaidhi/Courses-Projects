#include <stdio.h>
#define tab '\t'

int main(){
    const char csc[] = "CSC";
    const int num = 215;
    #ifdef tab
        printf("%s%c%d", csc, tab, num);
    #endif
    return 0;
}