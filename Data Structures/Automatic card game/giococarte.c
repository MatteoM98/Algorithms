#include "utils.h"





int main(int argc, char *argv[]){
	
	srand(time(NULL));
	Pila *Giocatore_1 = makePila();
	Pila *Giocatore_2 = makePila();
	Pila *Giocatore_3 = makePila();
	Pila *Giocatore_4 = makePila();
	Lista *Tavolo = makeLista();
	Coda *Mazzo = makeCoda();
	
	inserisci_carte_nel_mazzo(Mazzo);
	printf("------ RIEMPI MAZZO -----");
	visualizza_lista(Mazzo->primo);
	//visualizza_lista(Mazzo->ultimo);
	mischia_40_volte(Mazzo->primo);
	printf("-------- MISCHIA CARTE ---------");
	visualizza_lista(Mazzo->primo);
	//visualizza_lista(Mazzo->ultimo);
	
	//  -----------  PRIMA MANO  ----------------
	carta *card = NULL;
	for(int i=0;i<3;i++){
		card = dequeue(Mazzo);
		Giocatore_1 = push(card,Giocatore_1);
		card = dequeue(Mazzo);
		Giocatore_2 = push(card,Giocatore_2);
		card = dequeue(Mazzo);
		Giocatore_3 = push(card,Giocatore_3);
		card = dequeue(Mazzo);
		Giocatore_4 = push(card,Giocatore_4);
	}
	for(int i=0;i<4;i++){
		card = dequeue(Mazzo);
		Tavolo = inserisci(card,Tavolo);
	}
	puts("------------ PRIMA MANO -------------");
	printf("Giocatore 1");
	visualizza_lista(Giocatore_1);
	printf("Giocatore 2");
	visualizza_lista(Giocatore_2);
	puts("Giocatore 3");
	visualizza_lista(Giocatore_3);
	puts("Giocatore 4");
	visualizza_lista(Giocatore_4);
	printf("Tavolo");
	visualizza_lista(Tavolo);
	
	// -------------  INIZIO GIOCO  ---------------
	puts("------------- START GAME -----------");
	for(int i=0;i<3;i++){
		printf("Tavolo inizio turno %d",i+1);
		visualizza_lista(Tavolo);
		card = pop(&Giocatore_1);
		printf("Giocatore 1\n");
		printf("Carta giocata da Giocatore 1\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_1);
		Tavolo = inserisci(card,Tavolo);
		card = pop(&Giocatore_2);
		printf("Giocatore 2\n");
		printf("Carta giocata da Giocatore 2\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_2);
		Tavolo = inserisci(card,Tavolo);
		card = pop(&Giocatore_3);
		printf("Giocatore 3\n");
		printf("Carta giocata da Giocatore 3\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_3);
		Tavolo = inserisci(card,Tavolo);
		card = pop(&Giocatore_4);
		printf("Giocatore 4\n");
		printf("Carta giocata da Giocatore 4\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_4);
		Tavolo = inserisci(card,Tavolo);
		printf("Tavolo fine turno %d",i+1);
		visualizza_lista(Tavolo);
	}
	while(true){
	if(Mazzo!=NULL){
	for(int i=0;i<3;i++){
		card = dequeue(Mazzo);
		Giocatore_1 = push(card,Giocatore_1);
		card = dequeue(Mazzo);
		Giocatore_2 = push(card,Giocatore_2);
		card = dequeue(Mazzo);
		Giocatore_3 = push(card,Giocatore_3);
		card = dequeue(Mazzo);
		Giocatore_4 = push(card,Giocatore_4);
	}
	}
		puts("------------- START GAME -----------");
		if(Giocatore_1->inf==NULL) break;
	for(int i=0;i<3;i++){
		printf("Tavolo inizio turno %d",i+1);
		visualizza_lista(Tavolo);
		card = pop(&Giocatore_1);
		printf("Giocatore 1\n");
		printf("Carta giocata da Giocatore 1\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_1);
		Tavolo = inserisci(card,Tavolo);
		card = pop(&Giocatore_2);
		printf("Giocatore 2\n");
		printf("Carta giocata da Giocatore 2\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_2);
		Tavolo = inserisci(card,Tavolo);
		card = pop(&Giocatore_3);
		printf("Giocatore 3\n");
		printf("Carta giocata da Giocatore 3\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_3);
		Tavolo = inserisci(card,Tavolo);
		card = pop(&Giocatore_4);
		printf("Giocatore 4\n");
		printf("Carta giocata da Giocatore 4\n");
		stampa_carta(card);
		visualizza_lista(Giocatore_4);
		Tavolo = inserisci(card,Tavolo);
		printf("Tavolo fine turno %d",i+1);
		visualizza_lista(Tavolo);
	}
}

	return 0;
}
