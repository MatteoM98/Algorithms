#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>
#include <string.h>
#include <time.h>

typedef struct bucket {
	long id;
	char *nome;
	char *cognome;
} bucket;

char *strdup(const char *s);


// funzioni hash
int h1(int k, int m);
int h2(int k, int m);
int h(int k, int i, int m);
int hash_insert(bucket **T, bucket *elem, int m);
int hash_search(bucket **T, long key, int m, int *c);

long crea_matricola();
void stampa_studente(bucket *b);

void dealloca_studente(bucket *b);
void dealloca_tabella_studenti(bucket **tab, int n);
