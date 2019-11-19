#include "utils.h"



// ------- FUNZIONI DI UTILITA' ----------------

void die(const char *s){
	perror(s);
	exit(1);
}


// ----------  FUNZIONI SULLE LISTE  -----------

// crea una lista vuota
Lista *makeLista() {
	return NULL;
}

// controlla se la lista è vuota
int emptyL(Lista *l) {
	return (l == NULL);
}

// ritorna la prima carta della lista
carta *primac(Lista *l){
	if (l != NULL)
		return (l->inf);
	else return NULL;
}

// nel main chiamo crea_carta e lo passo a creaNodo
carta *crea_carta(int val, char s){
	carta *c = (carta *)malloc(sizeof(carta));
	if(c==NULL) die("Errore malloc crea_carta");
	c->valore = val;
	c->seme = s;
	return c;
}

// aggiunge un nodo alla lista
Lista *creaNodo (carta *el) {
	Lista *l;
	l = (Lista *) malloc(sizeof(Lista));
	if (l != NULL) {
		l->inf = el;
		l->next = NULL;
	}
	return l;
}



// inserimento in testa
Lista *inserisci(carta *el,Lista *l){
	Lista *l1;
	l1 = creaNodo (el);
	if (l1 != NULL) {
		l1->next = l;
		l= l1;
	}
	return l;
}

// elimina elemento in testa e restituisce la nuova lista
Lista *rimuovi(Lista *lptr){
	if (lptr != NULL) {
		Lista *l = lptr;
		lptr= lptr->next;
		free(l);
	}
	return lptr;
}


// ------------  FUNZIONI SULLE CODE  --------------

// crea una coda vuota
Coda *makeCoda(){
	Coda *c = malloc (sizeof(Coda *));
	c->primo= c->ultimo= NULL;
	return c;
}

// verifica se la coda è vuota
int emptyC(Coda *c){
	return emptyL(c->primo);
}

// ritorna la testa della coda
carta *first(Coda *c){
	return primac(c->primo);
}

// inserisce una carta in coda
void enqueue(carta *el, Coda *c){
	Lista *l;
	l = creaNodo(el);
	if (l != NULL) {
		if(emptyC(c)) {
			c->primo = l;
			c->ultimo = l;
		} else {
			c->ultimo->next = l;
			c->ultimo = l;
		}
	}
}

// elimina una carta dalla testa della coda
carta *dequeue(Coda *c){
	carta *el = NULL;
	if (!emptyC(c)) {
		el= first(c);
		c->primo= rimuovi(c->primo);
	}	
	return el;
	
}


// ------------  FUNZIONI SULLE PILE  ------------------

Pila *makePila(){
	return makeLista();
}


int emptyP(Pila *p){
	return emptyL(p);
}

Pila *push(carta *el,Pila *p){
	return inserisci(el, p);
}

carta *top(Pila *p){
	if (!emptyP(p))
		return primac(p);
	else return NULL;
}


carta *pop(Pila **p){
	carta *el = NULL;
	if (!emptyP(*p)) {
		el= primac(*p);
		*p= rimuovi(*p);
	}
	return el;
}

/********************************************************************************
 * -----------------    ORDINAMENTI   ----------------------------------------*
 * *****************************************************************************/

//• Generazione array random:
int *creaArrayRandom (int N) {
	int *arr= (int *)malloc (N * sizeof (int));
	for (int i= 0; i < N; i++) arr[i] = rand()%1000;
	return arr;
}

//• Copia di un array:
int * copyArray (int *inputArray, int N) {
	int *arr= (int *)malloc (N * sizeof (int));
	memcpy(arr, inputArray, N * sizeof(int));
	return arr;
}

// Stampa array
void stampa_array(int *a, int n){
	fprintf(stdout,"Array: [ ");
	for(int i=0;i<n;i++){
		fprintf(stdout,"%d ",a[i]);
	}
	puts("]");
}

// verifica che array a[0..n] sia ordinato 
bool check_sort(int *a, int n)
{
  for(int i=0; i < n-1; i++)
     if(a[i] > a[i+1]) return false;
  return true;
}

// ----- ALGORITMI DI ORDINAMENTO --------

void swap(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
}

// ---------- funzioni per il bubbleSort --------

void bubbleSort(int *array, int n){
	for(int i=0;i<n-1;i++){
		for(int j=1;j<(n-i);j++){
			if(array[j-1]>array[j]){
				swap(&array[j-1],&array[j]);
			}
		}
	}
}

// -------- funzioni per il quickSort ---------

int partition(int a[], int begin, int end)
{
  int pivot, l, r, Random;
	Random = begin + rand()%(end-begin);
  swap(&a[begin],&a[Random]);
  pivot = a[begin];
  l = begin + 1;
  r = end;
  while(l<=r){
		while(a[r]>pivot) r=r-1;
		while((a[l]<=pivot && (l<=r))) l=l+1;
		if(l<r){
			swap(&a[l],&a[r]);
			l++;
			r--;
		}
	}
	swap(&a[begin],&a[r]);
	return r;
}


void quickSort(int a[], int begin, int end) {
  int q;
  if(begin<end) {
    q = partition(a,begin,end);
    quickSort(a,begin,q-1);       // chiamata ricorsiva prima parte 
    quickSort(a,q+1,end);   // chiamata ricorsiva seconda parte
  }
}

// ------ funzioni per l'heapSort --------

// figlio sinistro
int left(int i) { return 2*i+1; }

// figlio destro
int right(int i) { return 2*i+2; }

// da figli a genitore
int parent (int i) { return (i-1)/2;} 

// funzione fixheap rende l'array come un heap (valore più grande in testa)
void fixHeap(int A[], int i, int HeapSize){
	int largest;
	int l = left(i);
	int r = right(i);
	if ((l < HeapSize) && (A[l] > A[i])){
		largest = l;
	}
	else{
		largest = i;
	}
	
	if ((r < HeapSize) && (A[r] > A[largest])){
		largest = r;
	}
	if (largest != i){
		swap(&A[i], &A[largest]);
		fixHeap (A, largest, HeapSize);
	}
}

// funzione heapify
void Heapify(int *a, int ArraySize){
	for(int i=ArraySize/2;i>=0;i--){
		fixHeap(a,i,ArraySize);
	}
}

// funzione heapSort
void heapSort(int *a, int ArraySize){
	int HeapSize = ArraySize;
	Heapify(a,ArraySize);
	for(int i=(ArraySize-1);i>=1;i--){
		swap(&a[0],&a[i]);
		HeapSize = HeapSize - 1;
		fixHeap(a,0,HeapSize);
	}
}
