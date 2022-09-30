#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void add();
void look();
void show();
void removeA();

typedef struct{
    char name[100];
    char email[100];
    char phoneNum[100];
}Address;

int main(){
    int choice=0;
    printf("#### Welcome to the Diary Book ####\n");
    printf("==========================================================\n");
    printf("[Main Menu]\n");
    printf("1) Add a new Address\n");
    printf("2) Show All Addresses\n");
    printf("3) Look for an Address\n");
    printf("4) Remove an Address\n");
    printf("5) Exit\n");
    printf("==========================================================\n");

    while(choice!=5){
        printf("Please Enter your preferred choice: ");
        scanf("%d", &choice);
        switch(choice){
            case 1: add(); break;
            case 2: show(); break;
            case 3: look(); break;
            case 4: removeA(); break;
            case 5: printf(". . .Exiting the Program. . ."); break;
            default: printf("incorrect option\n"); choice=0;
        }
    }
    return 0;
}

void add(){
    FILE *file;
    Address address;
    int choice=1;
    file = fopen("Adresses.txt","a+");
    if(!file){
        return;
    }

    while(choice){
        printf("Enter the name: ");
        scanf("%s", address.name);
        printf("Enter Email: ");
        scanf("%s", address.email);
        printf("Enter phone number: ");
        scanf("%s", address.phoneNum);
        
        fwrite(&address, sizeof(address), 1, file);
        printf("Address added successfully\n");
        printf("want to continue? ");
        scanf("%d", &choice);
    }
    fclose(file);
}

void show(){
    FILE *file;
    Address address;
    file = fopen("Adresses.txt","r");
    if(!file){
        return;
    }
    printf("name            email         phoneNum\n");
    fseek(file, 0, SEEK_SET);
    

    while(fread(&address, sizeof(address), 1, file)){
        printf("%s       %s       %s\n", address.name, address.email, address.phoneNum);
    }

    fclose(file);
}

void look(){
    FILE *file;
    Address address;
    int choice=1;
    char name[100];
    file = fopen("Adresses.txt","r");
    if(!file){
        return;
    }
    
    while(choice){
        printf("Enter the name: ");
        scanf("%s", name);
        fseek(file, 0, SEEK_SET);

        while(fread(&address, sizeof(address), 1, file)){
            if(!strcmp(address.name, name)){
                printf("An Address Found . .\n");
                printf("name: %s\nEmail: %s\nphone number: %s\n", address.name, address.email, address.phoneNum);
                break;
            }
        }

        printf("want to continue? ");
        scanf("%d", &choice);
    }

    fclose(file);
}

void removeA(){
    FILE *file;
    Address address, *addresses;
    int choice=1, numOfAddresses, i;
    char name[100];

    while(choice){
        printf("Enter the name: ");
        scanf("%s", name);
        file = fopen("Adresses.txt","r");
        numOfAddresses=0; i=0;
        addresses = (Address*)malloc(sizeof(Address));
        if(!file){
            return;
        }
        
        while(fread(&address, sizeof(address), 1, file)){
            if(strcmp(address.name, name)){
                numOfAddresses++;
                addresses = (Address*)realloc(addresses, numOfAddresses*sizeof(Address));
                addresses[i] = address;
                i++;
            }
        }

        file = fopen("Adresses.txt","w");
        if(!file){
            return;
        }

        fwrite(addresses, sizeof(address), numOfAddresses, file);
        printf("Address deleted successfully\n");
        
        printf("want to continue? ");
        scanf("%d", &choice);
    }

    fclose(file);
}
