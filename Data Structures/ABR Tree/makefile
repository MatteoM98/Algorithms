# definizione variabili
CFLAGS=-Wall -O -g -std=c99
LDFLAGS=-lm
CC=gcc

# elenco eseguibili
EXECS=alberi prova

all: $(EXECS)

alberi: alberi.o utils.o
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)
	
prova: prova.o utils.o
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)


# regola generale per i file oggetto
%.o: %.c utils.h
	$(CC) $(CFLAGS) -o $@ -c $<

# utile per il docente per creare un archvio da mettere su DIR 
tarfile:
	tar -zcf lezione.tgz makefile *.c *.h *.py

# per quando si vuole ricominciare da zero 
clean:
	rm -f *.o $(EXECS) *.csv
