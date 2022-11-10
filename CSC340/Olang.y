%{
#include "symbolTable.h"

extern int yylineno;
extern FILE* yyin;
extern int yyerror (char* msg);
extern char * yytext;
extern int yylex();

void relError(char msg[]);
void condStmtError(char msg[]);
void assignError(char dataType1[], char dataType2[]);
void varDupError(char variable[]);
void undecVarError(char variable[]);

struct symbol* symbolTable[SIZE];
%}

%token START END IF THEN ELSE IFEND READ WRITE REPEAT UNTIL
%token INT DOUBLE BOOLEAN
%token INT_LITERAL STRING_LITERAL DBL_LITERAL ID
%token ASSIGN PLUS MINUS MULT DIV EQ LT LE GT GE NE LPAREN RPAREN

%union{
	struct symbol sym;
}

%type <sym> type factor term simple_exp exp
%type <sym> ID

%%

Program :START stmt_sequence END;

stmt_sequence :statment ';' stmt_sequence
              | statment ';' ;

statment : dec_stmt
         | if_stmt 
         | repeat_stmt
         | assign_stmt
         | read_stmt
         | write_stmt;

dec_stmt :type ID {
			if(!Lookup($2.Name)){
				struct symbol *newdec = (struct symbol*)malloc(sizeof(struct symbol));
				newdec->Type = $1.Type;
				strcpy(newdec->Name, $2.Name);
				Insert(newdec);
			} else {
				varDupError($2.Name);
			}
		 };

type : INT { $$.Type = INT; }
     | DOUBLE { $$.Type = DOUBLE; };

if_stmt :IF exp {
				if($2.Type != BOOLEAN){
					condStmtError("if condition should be of type boolean");
				}
		} THEN stmt_sequence if_stmt2
		;
if_stmt2 : ELSE stmt_sequence IFEND
		| IFEND
		;

repeat_stmt :REPEAT stmt_sequence UNTIL exp {
				if($4.Type != BOOLEAN){
					condStmtError("repeat condition should be of type boolean");
				}
			}
			;

assign_stmt :ID ASSIGN exp {
				struct symbol *sym = Lookup($1.Name);
				if (!sym){
					undecVarError(sym->Name);
				}
				if(sym->Type != $3.Type){
					relError("Type mismatch");
				}
			};

read_stmt	: READ ID  {
				if(!Lookup($2.Name)){
					undecVarError($2.Name);
				}
			};

write_stmt : WRITE LPAREN exp RPAREN
           | WRITE LPAREN STRING_LITERAL RPAREN ;

exp : simple_exp comparison_op simple_exp { 
		if($1.Type == $3.Type){
			$$.Type = BOOLEAN;
		} else{
			relError("Type mismatch");
		}
	}
    |simple_exp { $$.Type = $1.Type; }
	;

comparison_op :EQ
              |LT 
              |LE 
              |GT 
              |GE  
              |NE;

simple_exp  : simple_exp addopt term {
				if($1.Type != $3.Type){
					$$.Type = DOUBLE ;
				} else {
					$$.Type = $1.Type;
				}
			}
            | term { $$.Type = $1.Type; }
			;
			
addopt : PLUS 
       | MINUS;

term : term multop factor {
			if($1.Type != $3.Type) {
				$$.Type = DOUBLE;
			} else {
				$$.Type = $1.Type;
			}
		}
     | factor { $$.Type = $1.Type; } 
	 ;

multop: MULT 
       |DIV;
	   
factor : LPAREN exp RPAREN {
			if($2.Type != BOOLEAN){
				$$.Type = $2.Type;
			} else {
				relError("Type mismatch");
			}
		}
       | INT_LITERAL { $$.Type = INT; }
       | DBL_LITERAL { $$.Type = DOUBLE; }
       | ID {
			struct symbol *sym = Lookup($1.Name);
			if(!sym){
				undecVarError($1.Name);
			} else {
				$$.Type = sym->Type;
			}
	   };

%%
int main (int argc, char *argv[]) {

	yyin=fopen(argv[1],"r");
        
	if(!yyparse())
	printf("\nParsing complete\n");	
	else
	printf("\nParsing failed\n");
	
	fclose(yyin);
	return 0;
}

extern int yyerror(char* msg) {
	printf("\n %s in line: %d %s \n", msg, yylineno, yytext);
	return 0;
}


void relError(char msg[]) {
	printf("\nRelational Operation Error : line %d %s\n", yylineno, msg);
}

void condStmtError(char msg[]) {
	printf("\nConditional Statement Error : line %d %s\n", yylineno, msg);
}

void assignError(char dataType1[], char dataType2[]) {
    printf("\nInvalid Assignment Error: line %d, Data types '%s' and '%s' are not compatable\n", yylineno, dataType1, dataType2);
}

void varDupError(char variable[]) {
    printf("\nDuplicate Variable Error: line %d, Duplicate variable '%s' found\n", yylineno, variable);
}

void undecVarError(char variable[]) {
	printf("\nUndeclared Variable Error : line %d, variable '%s' is not declared or is out of scope\n", yylineno, variable);
}



unsigned long hash(unsigned char *str) { /*djb2 by dan bernstein*/

    unsigned long hash = 5381;
    int c;

    while (c = *str++)
        hash = ((hash << 5) + hash) + c; 

    return hash % SIZE;
}

struct symbol* Lookup(char *Name) {
   unsigned long idx = hash(Name);

   while(symbolTable[idx]) {
      if(!strcmp(symbolTable[idx]->Name, Name)){
		  return symbolTable[idx];
		}
      idx++;
      idx %= SIZE;
   }

   return NULL;
}

void Insert(struct symbol *Symbol) {

   unsigned long idx = hash(Symbol->Name);

   while(symbolTable[idx]) {
      idx++;
      idx %= SIZE;
   }

   symbolTable[idx] = (struct symbol*)malloc(sizeof(struct symbol));
   strcpy(symbolTable[idx]->Name,Symbol->Name);
   symbolTable[idx]->Type = Symbol->Type;
}
