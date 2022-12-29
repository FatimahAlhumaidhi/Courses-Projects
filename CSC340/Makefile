OUTPUT_FILE = Olang.tab.c

$(OUTPUT_FILE): Olang.y
        bison -d Olang.y

LEXER_OUTPUT_FILE = Olang.yy.c

$(LEXER_OUTPUT_FILE): Olang.l
        flex Olang.l

COMPILER = compiler

$(COMPILER): Olang.tab.c Olang.yy.c
        gcc Olang.tab.c Olang.yy.c -ly -L -ll -o $(COMPILER)
