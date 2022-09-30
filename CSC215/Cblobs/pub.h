typedef enum { 
    Book, Magazine
}PubType;

typedef enum {
    Hardcover, Paperback
}CoverType;

typedef struct {
    int ISBN;
}BookID;

typedef struct {
    int ISSN;
    int vol;
    int issue;
}MagazineID;

typedef struct{
    PubType pType;
    char title[100];
    int nPages;
    CoverType cType;
    double price;
    union{
        BookID BID;
        MagazineID MID;
    };
}Publication;

typedef struct{
    int count;
    Publication* publications;
}PubList;


Publication readpub();
int findpub(Publication, PubList);
PubList getallbooks(PubList);