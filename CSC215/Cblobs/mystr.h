#if !defined MYSTR
#define MYSTR
/* calculate the length of a string */
int slen(const char*);
/* concatenate two strings into a
new string */
char* scat(const char*, char*);
/* compares two strings s1 and s2, returns an integer less than,
 equal to, or greater than zero if s1 is found, respectively,
 to be less than, to match, or be greater than s2 */
int scmp(const char*,const char*);
/* returns a new duplicate of the parameter */
char* sdup(const char*);
/* returns a new string that is the reverse of the parameter */
char* srev(const char*);
#endif