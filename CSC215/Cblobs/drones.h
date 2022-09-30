typedef struct Drone{
    int id;
    char pilot[20];
    char class[20];
    char location[8];
    char rotor[20];
    float rate;
    float fees;
    char mode;
    struct Drone* next;
}DroneNode; //project description specified not creating structures other than Drone

void addNavigateDrone();
void setNavigateDrone(char*, float*);
void writeDroneInWay(char*);
void showList(DroneNode*);
void* findDirection(char);