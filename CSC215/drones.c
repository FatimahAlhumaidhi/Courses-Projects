#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "drones.h"
#define MAX 150

DroneNode *list = NULL;

int main(){
    addNavigateDrone();
    printf("the available navigation drones:\n");
    showList(list);
    
    float f = 4.5;
    setNavigateDrone("Delivery", &f);
    f = 5.0;
    setNavigateDrone("Agriculture", &f);
    setNavigateDrone("GPS", &f);
    f = 4.9;
    setNavigateDrone("Agriculture", &f);

    writeDroneInWay("Drones.txt");
    void* headingSouth = findDirection('S');
    puts("--------------------------------------------------------------------------------------------------------------------------------------------");
    printf("drones heading south:\n");
    showList(headingSouth);

    puts("--------------------------------------------------------------------------------------------------------------------------------------------");
    printf("drones in way:\n");
    showList(list);
    return 0;
}

void addNavigateDrone(){
    DroneNode *current, *tail;
    FILE* file;
    if((file = fopen("Drones.txt", "r")) == NULL){
        exit(1);
    }
    char firstline[MAX];
    fgets(firstline, MAX, file);

    while(!feof(file)){
        if((current = (DroneNode*)malloc(sizeof(DroneNode))) == NULL){
            exit(1);
        }
        fscanf(file, "%d%s%s%s%s%f%f",  &current->id, current->pilot, current->class, current->location, current->rotor, &current->rate, &current->fees);
        current->mode = 'R';
        if(!list){
            tail = list = current;
        }
        else{
            tail->next = current;
            tail = tail->next;
        }
    }
    tail->next = NULL;
    fclose(file);
}

void setNavigateDrone(char* class, float* rate){
    DroneNode* current = list;
    while(current != NULL){
        if(!strcmp(current->class, class) && fabs(*rate - current->rate)<=0.01){
            current->mode = 'B';
        }
        current = current->next;
    }
}

void writeDroneInWay(char* fileName){

    FILE* file;
    if((file = fopen(fileName, "a")) == NULL){
        exit(1);
    }

    fputs("\n-------------------------------------------------------------------------------------------------------------------------------", file);
    fputs("\nthe navigation Drones\n", file);
    fprintf(file, "%-16s%-16s%-32s%-16s%-16s%s\n", "id", "pilot", "class", "location", "rate", "mode");
    DroneNode* current = list;
    while(current){
        if(current->mode == 'B'){
            fprintf(file, "%-16d%-16s%-32s%-16s%-16.1f%c\n",  current->id, current->pilot, current->class, current->location, current->rate, current->mode);
        }
        current = current->next;
    }
    fclose(file);
}

void showList(DroneNode* list){
    if(list == NULL){
        printf("empty list.");
        exit(1);
    }
    printf("%-16s%-16s%-32s%-16s%-24s%-16s%-16s%s\n", "id", "pilot", "class", "location", "rotor", "rate", "fees", "mode");
    DroneNode* current = list;
    while(current != NULL){
        printf("%-16d%-16s%-32s%-16s%-24s%-16.1f%-16.2f%c\n", current->id, current->pilot, current->class, current->location, current->rotor, current->rate, current->fees, current->mode);
        current = current->next;
    }
}

int endsWith(const char* str, char d){
    return (str && str[strlen(str) - 1] == d);
}// compares the last character in a string with character d

void copystruct(DroneNode* source, DroneNode* distenation){
    distenation->id = source->id;
    strcpy(distenation->pilot, source->pilot);
    strcpy(distenation->class, source->class);
    strcpy(distenation->location, source->location);
    strcpy(distenation->rotor, source->rotor);
    distenation->fees = source->fees;
    distenation->mode = source->mode;
    distenation->rate = source->rate;
    distenation->next = NULL;
}//copies drone information from node source to distenation

void insert(DroneNode* newnode, DroneNode** sortedList) {

    if (*sortedList == NULL || (*sortedList)->id > newnode->id) {
        newnode->next = *sortedList;
        *sortedList = newnode;
    }
    else {
        DroneNode* current = *sortedList;

        while (current->next != NULL && current->next->id <= newnode->id) {
            current = current->next;
        }
        newnode->next = current->next;
        current->next = newnode;
    }
} // inserts a node into sorted list in it's appropriate place according to drone id

void* findDirection(char d){
    
    DroneNode *current = list, *newNode = NULL, *sortedList = NULL;
    while (current) {
        if(endsWith(current->location, d)){
            if((newNode = (DroneNode*)malloc(sizeof(DroneNode))) == NULL) {
                exit(1);
            }
            copystruct(current, newNode);
            insert(newNode, &sortedList);
        }
        current = current->next;
    }

    return sortedList;
}
