#include <stdio.h>
#include <string.h>
#include <stdlib.h>


typedef enum{
    P1=1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15
}projects;

int indexOf(int e, int arr[]){
    for(int i=0; i<15; i++){
        if(arr[i]==e)
            return i;
    }
}
/*
    P1:  وئام 
    P2:  هبة
    P3:  مزنة
    P4:  أريج
    P5:  حفيظة1
    P6:  نورة
    P7:  صيتة
    P8:  سراب
    P9:  هاجر
    P10: رشا
    P11: نجلاء
    P12: لبنى
    P13: منيرة
    P14: حفيظة2
    P15: مي

    P1     P2     P3     P4     P5     P6     P7     P8     P9     P10    P11    P12    P13    P14    P15  
    2.2    11.6   4.4    9.6    6.8    12.6   12.0   6.8    7.6    14.2   1.0    3.8    12.8   7.6    7.0
    
    P11    P1     P12    P3     P5     P8     P15    P14    P9     P4     P2     P7     P6     P13    P10
    1.0    2.2    3.8    4.4    6.8    6.8    7.0    7.6    7.6    9.6    11.6   12.0   12.6   12.8   14.2
    1      2      3      4      5      6      7      8      9      10     11     12     13     14     15

*/
int main(int argc, char *argv[]){
    
    int me[15] = {P11, P14, P1, P12, P15, P5, P8, P3, P2, P9, P4, P13, P10, P7, P6},
    halah[15]  = {P11, P1, P12, P3, P5, P14, P15, P8, P9, P4, P7, P2, P6, P10, P13},
    lama[15]   = {P11, P1, P3, P12, P15, P8, P5, P4, P9, P14, P7, P2, P6, P10, P13},
    joud[15]   = {P11, P1, P3, P9, P12, P8, P5, P6, P4, P13, P7, P14, P15, P2, P10}, 
    seba[15]   = {P11, P1, P12, P3, P15, P9, P8, P14, P5, P4, P2, P13, P7, P6, P10};
    double finally[16];

    for(int i=1; i<16; i++){
        printf("%s%-5d ", "P", i);
        finally[i-1] = ((indexOf(i, me)+indexOf(i, halah)+indexOf(i, lama)+indexOf(i, joud)+indexOf(i, seba))/5.0)+1;
    }

    printf("\n");
    for(int i=0; i<15; i++){
        printf("%-6.1f ", finally[i]);
    }

    
    return 0;
}
