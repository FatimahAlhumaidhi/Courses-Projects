#include <stdio.h>

int main(){
    const float pi = 3.14;
    double radius=0;
    char color[100];

    printf("Enter the circle's Color: ");
    scanf("%s", &color);
    printf("Enter the circle's Radius: ");
    scanf("%lf", &radius);
    double area = pi*radius*radius;
    printf("the %s circle's area: %.1lf", color, area);
    return 0;
}