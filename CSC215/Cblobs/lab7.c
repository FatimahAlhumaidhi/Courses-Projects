#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    int ID;
    char name[100];
    float GPA;
}student;

int main(){
    int numofstudents, index=0;
    float highestGPA=0;
    char highestGPAName[100];
    student *students;
    printf("Enter the number of student: ");
    scanf("%d", &numofstudents);
    students = (student*)malloc(numofstudents*sizeof(student));

    for(int i=0; i<numofstudents; i++){
        printf("Enter ID of student %d:", i+1);
        scanf("%d", &(students[i].ID));
        printf("Enter name of student %d:", i+1);
        scanf("%s", students[i].name);
        printf("Enter GPA of student %d:", i+1);
        scanf("%f", &(students[i].GPA));
        if(students[i].GPA>highestGPA){
            highestGPA=students[i].GPA;
            strcpy(highestGPAName, students[i].name);
            index = i;
        }
    }
    printf("%s\n", students[index].name);
    for(int i=0; i<numofstudents; i++){
        if(i!= index){
            printf("%s\n", students[i].name);
        }
    }

    return 0;
}