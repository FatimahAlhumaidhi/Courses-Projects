#include <stdio.h>

int main(){
    int a=1, b=10, c=0;

    printf("a=%d b=%d c=%d\n", a, b, c);
    printf("a++ + %d = %d\n", b, (a++ + b));
    printf("++a + %d = %d\n", b, (++a + b));
    printf("%d && %d = %d\n", a, c, (a&&c));
    printf("%d || %d = %d\n", a, c, (a||c));
    printf("%d & 2 = %d\n", a, (a&2));
    printf("%d | 0 = %d\n", a, (a|0));
    printf("%d << 2 = %d\n", a, (a<<2));
    printf("%d >> 1 = %d\n", a, (a>>1));
    return 0;
}