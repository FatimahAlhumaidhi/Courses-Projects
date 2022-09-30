#include <stdio.h>
#define MAX 80

void NumOfCharacter (FILE*, FILE*, char, char *);

int main () {
    FILE *fpi, *fpo;
    char c, line[MAX];
    fpo = fopen("output.txt" , "w");
    fpi = fopen("inupt.txt" , "r");

    if(fpi == NULL || fpo == NULL)
        return 1;

    printf("enter a character: ");
    scanf("%c" , &c);
    NumOfCharacter(fpi, fpo, c, line);
    
    fclose(fpi);
    fclose(fpo);
    return 0;
}

void NumOfCharacter (FILE* fpi , FILE* fpo, char c , char *line){
    int totalfreq, occurances, lineNumber, i;
    while(fgets(line, MAX, fpi)){
        lineNumber++; 
        while(*(line+i) != '\0'){ 
            if(*(line+i) == c)
                occurances++;
            i++;
        }
        totalfreq += occurances;
        fprintf(fpo, "Line#: %d, Frequency of %c = %d ", lineNumber, c, occurances);
        i=0;
        }
        if(!totalfreq)
            fprintf(fpo, "character not found");
        else
            fprintf(fpo, "Total frequency of %c = %d", c, totalfreq);
}



