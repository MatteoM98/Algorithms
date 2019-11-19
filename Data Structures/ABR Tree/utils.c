#include "utils.h"

//funzioni generali
void die(const char *s)
{
	perror(s);
	exit(1);
}

int max(int a,int b)
{
	if(a>b) return a;
	return b;
}

int min(int sx, int dx){
	
	if(sx>=dx) return dx;
	assert(sx<dx);
	return sx;
}

int *random_array(int n)
{
  assert(n>0);
  int *a = malloc(n* sizeof(int));
  assert(a!=NULL);
  for(int i=0; i < n ;i++)
    a[i] = rand()%MAX;
  return a;
}

bool check_sort(int *a, int n) //funzione che verifica il corretto ordinamento dell'array
{
  for(int i=0; i < n-1; i++)
     if(a[i] > a[i+1]) return false;
  return true;
}

void stampa_array(int *a,int n)
{
	for(int i=0;i<n;i++) printf("A[%d]: %d\n",i,a[i]);
}

int intcmp(const void *a,const void *b)
{
	return *((int *)a) - *((int *)b);
}

int binarySearch(int arr[], int l, int r, int x,int *hit) 
{ 
   if (r >= l) 
   { 
        int mid = l + (r - l)/2; 
  
        if (arr[mid] == x)   
        {
			*hit+=1;
            return mid; 
		}
  
        if (arr[mid] > x)  
        {
			*hit+=1;
            return binarySearch(arr, l, mid-1, x,hit); 
		}
  
    
        return binarySearch(arr, mid+1, r, x,hit); 
   } 
   
   return -1; 
} 


//funzioni delle liste.

Lista *makeLista() {  //crea una lista vuota
return NULL;
}

int emptyL(Lista *l) { //verifica se la lista è vuota
return (l == NULL);
}

Albero primac(Lista *l){  //ritorna la prima carta nella lista
if (l != NULL)
 return (l->inf);
return NULL;
}

//passo come parametro crea carta

Lista *creaNodo (Albero el) {  //creazione di un nodo con carta 
Lista *l;
l = (Lista *) malloc(sizeof(Lista));
if (l != NULL) {
l->inf = el;
l->next = NULL;
}
return l;
}

void visualizza_lista(Lista *l) {  //stampa la lista di carte
Lista *p;
p= l;
printf("\n lista : \n");
while(p != NULL) {
stampa_nodo(p->inf); //stampa il nodo
p = p->next;		 /* scorre la lista di un elemento*/
}
printf("NULL\n\n");
}

void stampa_nodo(Albero c) //stampa una singola carta
{
	assert(c!=NULL);
	printf("Il valore del nodo e': %d\n",c->inforadice);
}

Lista *inserisci(Albero el,Lista *l){ //inserisce una carta in testa alla lista e ritorna l'elemento che ha inserito(la testa della lista)
Lista *l1;
l1 = creaNodo(el);
if (l1 != NULL) {
 l1->next = l;
 l= l1;
}
return l;
}


Lista *rimuovi(Lista *lptr){  //elimina l'elemento in testa alla lista e restituisce la nuova lista
if (lptr != NULL) {
	Lista *l = lptr;
	lptr= lptr->next;
	free(l);
}
return lptr;
}

//funzioni delle code

Coda *makeCoda(){          //inizializza una coda
Coda *c = (Coda *) malloc (sizeof(Coda));
c->primo = c->ultimo= NULL;
return c;
}

int emptyC(Coda *c){       //verifica se la coda è vuota
return emptyL(c->primo);
}

Albero first(Coda *c){   //ritorna la carta in testa
return primac(c->primo);
}


void enqueue(Albero el, Coda *c){  //inserisce una carta in coda 
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


Albero dequeue(Coda *c){  //elimina una carta dalla testa e la restituisce
Albero el = NULL;
if (!emptyC(c)) {
	el = first(c);
	c->primo = rimuovi(c->primo);
				}
	return el;
}

//funzioni pile 

Pila *push(Albero el,Pila *p){
return inserisci(el, p);
}

Albero top(Pila *p){
if (!emptyP(p))
	return primac(p);
return NULL;
}

Albero pop(Pila **p){
Albero el = NULL;
if (!emptyP(*p)) {
		el= primac(*p);
		*p= rimuovi(*p);
         }
return el;
}

Pila *makePila(){
return makeLista();
}

int emptyP(Pila *p){
return emptyL(p);
}

/* *************************************************************************************** */
/* Implementazione funzioni degli alberi                                                  */
/* *************************************************************************************** */


Albero makeNodo (int el){
    Albero al;

    al = (Albero) malloc(sizeof(Nodo));
    if (al != NULL) {
	al -> destro = al -> sinistro = NULL;
   	al -> inforadice = el;}
    return al;
}

Albero makeTree (int n)
{
	if(n>0)
	{
		int numFiglioSx = (n-1)/2;
		int numFiglioDx = (n-1)-numFiglioSx;
		
		Albero nuovoNodo = makeNodo(rand()%K);
		nuovoNodo->sinistro = makeTree(numFiglioSx);
		nuovoNodo->destro = makeTree(numFiglioDx);
		
		return nuovoNodo;
	}
	
	return NULL;
}

void visitaDFSRicorsiva(Albero al) //visita in profondità
{
	if(al==NULL) return;
	stampa_nodo(al);
	visitaDFSRicorsiva(al->sinistro);
	visitaDFSRicorsiva(al->destro);
}

void visitaBFS(Albero al)
{
	Coda *c = makeCoda();
	enqueue(al,c);
	while(!emptyC(c))
	{
		Albero u = dequeue(c);
		if(u!=NULL)
		{
			stampa_nodo(u);
			enqueue(u->sinistro,c);
			enqueue(u->destro,c);
		}
	}
}

bool check_foglia(Albero al)
{
	assert(al!=NULL);
	if(al->sinistro==NULL && al->destro==NULL) return true;
	return false;
}

int altezza_albero (Albero al)
{
	if(al==NULL) return -1;
	if(check_foglia(al)) return 0;
	
	int sx = altezza_albero(al->sinistro);
	int dx = altezza_albero(al->destro);
	
	return max(sx,dx)+1;
	
}

int foglie_albero(Albero al)
{
	if(al==NULL) return 0;
	if(check_foglia(al)) return 1;
	
	int sx = foglie_albero(al->sinistro);
	int dx = foglie_albero(al->destro);
	
	return sx+dx;
}

int numero_nodi(Albero al)
{
	if(al==NULL) return 0;
	if(check_foglia(al)) return 1;
	
	int sx = numero_nodi(al->sinistro);
	int dx = numero_nodi(al->destro);
	
	return sx+dx+1;
	
}

bool cerca_elemento(Albero al,int x)
{
	if(al==NULL) return false;
	if(al->inforadice==x) return true;
	if(cerca_elemento(al->sinistro,x)) return true;
	if(cerca_elemento(al->destro,x)) return true;
	return false; 
	
}

int somma_chiavi(Albero al)
{
	if(al==NULL) return 0;
	int sx = somma_chiavi(al->sinistro);
	int dx = somma_chiavi(al->destro);
	return sx+dx+al->inforadice;
}

int somma_chiavi_iterativo(Albero al)
{
	Pila *p = makePila();
	Albero n = NULL;
	int somma=0;
	
	if(al!=NULL)
	{
		p = push(al,p);
		while(!(emptyP(p)))
		{
			n = pop(&p);
			if(n!=NULL){
				somma+=(n->inforadice);
				p = push(n->destro,p);
				p = push(n->sinistro,p);
			}
		}
	}
	return somma;
}

//Crea un albero partendo da un array, per alberi BILANCIATI
Albero creaAlbero(int *a, int i, int n){
	
	if(i<n){
		
		/*if(i==0){
			Albero nuovoNodo = makeNodo(a[i]);
			nuovoNodo->sinistro = creaAlbero(a, i+1, n);
			nuovoNodo->destro = creaAlbero(a,i+2, n);
			return nuovoNodo;
		}*/
		
		Albero nuovoNodo = makeNodo(a[i]);
		nuovoNodo->sinistro = creaAlbero(a, 2*i+1, n);
		nuovoNodo->destro = creaAlbero(a,2*i+2, n);
		return nuovoNodo;
	}
	
 return NULL;
	
}

int max_value(Albero al)
{
	if(al==NULL) return 0;
	if(check_foglia(al)) return al->inforadice;
	
	int sx = max_value(al->sinistro);
	int dx = max_value(al->destro);
	
	return max(al->inforadice,max(sx,dx));
}


bool pari(Albero al){
	
	//if(al==NULL) return false;
	return al->inforadice%2==0;
}

bool dispari(Albero al){
	
	//if(al==NULL) return false;
	return (al->inforadice)%2!=0;
}

//Controlla che i figlio sinistra di un nodo sia dispari
//mentre figlio destro di stesso nodo sia pari
bool check_pari_dis(Albero al){
	
	if(al==NULL) return true;
	
	if(al->sinistro!=NULL){
	
		if(!dispari(al->sinistro)) return false;
	}
	
	if(al->destro!=NULL){
		if(!pari(al->destro)) return false;
	}
	
	if(!check_pari_dis(al->sinistro)) return false;
	if(!check_pari_dis(al->destro)) return false;
	
	return true;
}

//Controlla se un albero e' completo
bool complete_tree(Albero al){
	
	int tot_foglie = foglie_albero(al);
	int altezza = altezza_albero(al);
	return tot_foglie == pow(2,altezza);
	
}

int altezza_minima(Albero al)
{
	if(al==NULL) return -1;
	if(check_foglia(al)) return 0;
	
	int sx = altezza_albero(al->sinistro);
	int dx = altezza_albero(al->destro);
	
	return min(sx,dx)+1;
}

void stampa_foglie_livello_pari(Albero al,int liv)
{
	if(al==NULL) return;
	if(check_foglia(al) && liv%2==0) {
		stampa_nodo(al);
		return;
	}
	
	stampa_foglie_livello_pari(al->sinistro,liv+1);
	stampa_foglie_livello_pari(al->destro,liv+1);
}

void stampaFogliePari(Albero al, int liv){
	
	if(al == NULL) return;
	
	if(check_foglia(al) && liv%2==0)
	printf("Foglia di livello pari: %d\n", al->inforadice);
	
	stampaFogliePari(al->sinistro, liv+1);
	stampaFogliePari(al->destro, liv+1);
}

int conta(Albero al,int v)
{
	if(al==NULL) return 0;
	
	int sx = conta(al->sinistro,v);
	int dx = conta(al->destro,v);
	
	if(al->inforadice <= v) return sx+dx+1;
	return sx+dx;
}

//====================ABR==================

Albero insert_ABR(Albero root,int val)
{
	if(root==NULL)
	{
		root = makeNodo(val);
		return root;
		
	}else if(root->inforadice > val)
	{
		
		root->sinistro = insert_ABR(root->sinistro,val);
	}else
	{
		root->destro = insert_ABR(root->destro,val);
	}
	
	
	return root;
}

void visitaBFS_ABR(Albero al)
{
	Coda *c = makeCoda();
	enqueue(al,c);
	while(!emptyC(c))
	{
		Albero u = dequeue(c);
		if(u!=NULL)
		{
			stampa_nodo(u);
			enqueue(u->sinistro,c);
			enqueue(u->destro,c);
		}else
		{
			printf("Figlio vuoto\n");
		}
	}
}


void visitaDFSRicorsiva_ABR(Albero al) //visita in profondità
{
	if(al==NULL) return;
	visitaDFSRicorsiva(al->sinistro);
	stampa_nodo(al);
	visitaDFSRicorsiva(al->destro);
}

void makeABR_from_array(Albero al,int *a,int n)
{
	assert(n>0);
	for(int i=0;i<n;i++) al = insert_ABR(al,a[i]);
	
}


void ricerca_albero(Albero al,int chiave,int *i)
{
	(*i)++;
	if(al==NULL) return;
	if(al->inforadice == chiave) return;
	else
	{
		if(chiave < al->inforadice)
		{
			
			ricerca_albero(al->sinistro,chiave,i);
			
		}else if(chiave > al->inforadice)
		{
			
			ricerca_albero(al->destro,chiave,i);
		}
		
	}
	
}

void destroy_albero(Albero al)
{
	if(al==NULL) return;
	Albero oldsx = al->sinistro;
	Albero olddx = al->destro;
	
	free(al);
	
	destroy_albero(oldsx);
	destroy_albero(olddx);
}






