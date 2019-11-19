#define _GNU_SOURCE
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <assert.h>

// DEFINIZIONE STRUTTURE

typedef struct carta {
	int valore;
	char seme; // (C = Cuori; Q = Quadri; F = Fiori; P = Picche)
} carta;

typedef struct Nodo_lista {
	carta *inf;
	struct Nodo_lista *next;
} Lista;

typedef Lista Pila;	// Pila come Lista

typedef struct Coda{	// struttura coda = 2 liste (primo e ultimo)
	Lista *primo;
	Lista *ultimo;
} Coda;

// prototipi vari
void die(const char *s);

// prototipi funzioni Lista
carta *crea_carta(int val, char s);
Lista *makeLista();
int emptyL(Lista *l); 
carta *primac(Lista *l);
Lista *creaNodo (carta *el);
void stampa_carta(carta *c);
void visualizza_lista(Lista *l);
Lista *inserisci(carta *el,Lista *l);
Lista *rimuovi(Lista *lptr);

// prototipi funzioni Coda
Coda *makeCoda();
int emptyC(Coda *c);
carta *first(Coda *c);
void enqueue(carta *el, Coda *c);
carta *dequeue(Coda *c);

// prototipi funzioni Pila
Pila *makePila();
int emptyP(Pila *p);
Pila *push(carta *el,Pila *p);
carta *top(Pila *p);
carta *pop(Pila **p);

// prototipi funzioni gioco
void riempi_mazzo(Coda *c, char ch);
void inserisci_carte_nel_mazzo(Coda *c);
void mescola_mazzo(Lista *lis);
void mischia_40_volte(Lista *lis);
