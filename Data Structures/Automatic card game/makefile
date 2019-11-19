# definizione variabili
CFLAGS=-Wall -O -g -std=c99
CC=gcc

# elenco eseguibili
EXECS=giococarte

all: $(EXECS)

giococarte: giococarte.o utils.o
	$(CC) $(CFLAGS) -o $@ $^



# regola generale per i file oggetto
%.o: %.c utils.h
	$(CC) $(CFLAGS) -o $@ -c $<

# utile per il docente per creare un archvio da mettere su DIR 
tarfile:
	tar -zcf lezione.tgz makefile *.c *.h

# per quando si vuole ricominciare da zero 
clean:
	rm -f *.o $(EXECS)
