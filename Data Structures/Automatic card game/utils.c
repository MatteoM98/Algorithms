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

// stampa una carta
void stampa_carta(carta *c){
	assert(c!=NULL);
	printf("Il valore della carta è: %d con seme %c\n",c->valore,c->seme);
}

// stampa la lista di carte + null al fondo
void visualizza_lista(Lista *l) {
	Lista *p;
	p = l;
	printf("\n lista : \n");
	while(p != NULL) {
		stampa_carta(p->inf); /* visualizza l'informazione */
		p = p->next; /* scorre la lista di un elemento */
	}
	printf("NULL\n\n");
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

// ---------- FUNZIONI PER IL GIOCO  ----------

// riempie il mazzo con 10 carte con seme 'ch'
void riempi_mazzo(Coda *c, char ch){
	for(int i=1;i<=10;i++){
		carta *card = crea_carta(i,ch);
		enqueue(card,c);
		
	}
}

void inserisci_carte_nel_mazzo(Coda *c){
	riempi_mazzo(c,'C'); // da 1 a 10 di cuori
	riempi_mazzo(c,'Q'); // da 1 a 10 di quadri
	riempi_mazzo(c,'F'); // da 1 a 10 di fiori
	riempi_mazzo(c,'P'); // da 1 a 10 di picche
}

void mescola_mazzo(Lista *lis){
	int r1 = rand()%40;
	int r2 = rand()%40;
	Lista *temp = lis;
	for(int i=0;i<r1;i++){
		temp = temp->next;	
	}
	for(int i=0;i<r2;i++){
		lis = lis->next;	
	}
	carta *ca = temp->inf;
	temp->inf = lis->inf;
	lis->inf = ca;
	

	
	
}

void mischia_40_volte(Lista *lis){
	
	for(int i=0;i<40;i++){
		mescola_mazzo(lis);
	}
}


