#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <math.h>
#define K 20
#define MAX 10000

//strutture


/* Definizione del tipo Albero: puntatore ad una struttura Nodo che contiene informazione e
  riferimenti al sottoalbero sinistro e destro. La funzione crea_nodo costruisce un nodo 
  contenente l'informazione passata come parametro e sottoalbero destro e sinistro vuoto. */

typedef struct Nodo {
    int  inforadice;
    struct Nodo *sinistro;
    struct Nodo *destro;
} Nodo;

typedef Nodo* Albero;

typedef struct Nodo_lista {
Albero inf;
struct Nodo_lista *next;
} Lista;

typedef struct coda {
Lista *primo;
Lista *ultimo;
} Coda; 

typedef Lista Pila;


//prototipi funzioni generali
void die(const char *s);
int max(int a,int b);
int *random_array(int n);
bool check_sort(int *a, int n);
void stampa_array(int *a,int n);
int intcmp(const void *a,const void *b);
int binarySearch(int arr[], int l, int r, int x,int *hit);


// prototipi funzioni liste

Lista *makeLista();
int emptyL(Lista *l);
Albero primac(Lista *l);
Lista *creaNodo (Albero el);
void visualizza_lista(Lista *l);
void stampa_nodo(Albero al);
Lista *inserisci(Albero el,Lista *l);
Lista *rimuovi(Lista *lptr);


//prototipi funzioni code

Coda *makeCoda();
int emptyC(Coda *c);
Albero first(Coda *c);
void enqueue(Albero el, Coda *c);
Albero dequeue(Coda *c);


//prototipi funzioni pila
Pila *push(Albero el,Pila *p);
Albero top(Pila *p);
Albero pop(Pila **p);
Pila *makePila();
int emptyP(Pila *p);


//prototipi funzioni alberi
Albero makeNodo(int el);
Albero makeTree (int n);
void visitaDFSRicorsiva(Albero al);
void visitaBFS(Albero al);
bool check_foglia(Albero al);
int altezza_albero (Albero al);
int foglie_albero(Albero al);
int numero_nodi(Albero al);
bool cerca_elemento(Albero al,int x);
int somma_chiavi(Albero al);
int somma_chiavi_iterativo(Albero al);
Albero creaAlbero(int *a, int i, int n);
int max_value(Albero al);
bool pari(Albero al);
bool dispari(Albero al);
bool check_pari_dis(Albero al);
bool complete_tree(Albero al);
int altezza_minima(Albero al);
void stampa_foglie_livello_pari(Albero al,int liv);
void stampaFogliePari(Albero al, int liv);
int conta(Albero al,int v);
void destroy_albero(Albero al);

//prototipi funzioni abr
Albero insert_ABR(Albero root,int val);
void visitaBFS_ABR(Albero al);
void visitaDFSRicorsiva_ABR(Albero al);
void makeABR_from_array(Albero al,int *a,int n);
void ricerca_albero(Albero al,int chiave,int *i);




