#include <stdio.h>
#include <math.h>

int main(){
    float radius=0;
    char color[100];

    printf("Enter the circle's Color: ");
    scanf("%s", &color);
    printf("Enter the circle's Radius: ");
    scanf("%f", &radius);
    float area = M_PI*radius*radius;
    printf("the %s circle's area: %.1f", color, area);
    return 0;
}