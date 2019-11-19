#include "utils.h"

long crea_matricola(){
	return (rand()%(1223622928-1065687581+1))+1065687581;
}

void stampa_studente(bucket *b){
	fprintf(stdout,"Id: %ld, Nome: %s, Cognome: %s\n",b->id,b->nome,b->cognome);
}



void dealloca_studente(bucket *b){
	free(b->nome);
	free(b->cognome);
	free(b);
}

void dealloca_tabella_studenti(bucket **tab, int n){
	for(int i=0;i<n;i++){
		if(tab[i]!=NULL) dealloca_studente(tab[i]);
	}
	free(tab);
}

// ---------------------------------------------------
// --------------  TABELLE HASH  ---------------------
// ---------------------------------------------------

int h1(int k, int m){
	return k%m;
}

int h2(int k, int m){
	return 1+(k%(m-1));
}

int h(int k, int i, int m){
	return ((h1(k,m) + i*h2(k,m))%m);
}

int hash_insert(bucket **T, bucket *elem, int m){
	int i = 0;
	int j;
	do{
		j = h(elem->id,i,m);
		if(T[j]==NULL){
			T[j] = elem;
			return j;
		}
		else i+=1;
	}while(i!=m);
	return -1;
}

int hash_search(bucket **T, long key, int m, int *c){
	int i=0;
	int cont = 0;
	int j = h(key,i,m);
	cont+=1; // ho chiamato una volta la funzione h quindi cont va incrementato e ora è uno
	assert(cont==1);
	while(T[j]!=NULL && i!=m){
		
		if(T[j]->id==key) {*c = cont; return j;}
		i+=1;
		j = h(key,i,m);
		cont+=1;
	}
	*c = cont;
	return -1;
}

