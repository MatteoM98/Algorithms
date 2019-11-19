#include "utils.h"


int main(int argc, char **argv){
	
	if(argc!=3) {
		fprintf(stderr,"Usage: %s [nome_file_input] [nome_file_output]\n",argv[0]);
		exit(1);
	}
	
	srand(time(NULL));
	int n = 80; //numero studenti da inserire in tabella 
	
	int dim[5] = {131,113,101,89,83}; // primi da testare come m per ottenere fattore di carico rispettivamente di (circa) 60% 70% 80% 90% 100%
	
	long id; char *name; char *surname; //parametri studente
	int ind; 
	int cont;  //contatore studenti
	int media_hit; 
	FILE *f = fopen(argv[1],"rt"); //apro file da leggere
	
	//apertura file output
	char *file_out = strcat(argv[2],".csv");
	FILE *f2 = fopen(file_out,"wt");
	fprintf(f2,"Fattore di carico,Media Hit\n");
	
	for(int j=0;j<5;j++){
	
			int m = dim[j]; //inizializzo m
			int fattore_di_carico = (((double)n)/m)*100; //calcolo il fattore di carico in percentuale
			
			printf("\n--------  INIZIO INSERIMENTO STUDENTI  con Fattore di Carico   %d%c  -----------\n\n",fattore_di_carico,'%');
			bucket **tabHash = (bucket **)malloc(m*sizeof(bucket *)); //inizializzo tabella di hash
			
			// ciclo che inizializza a NULL tutti gli elementi della tabella
			for(int i=0;i<m;i++) tabHash[i]=NULL;
			cont=0;
			
			// ciclo per la lettura degli studenti da file e l'inserimento nella tabella hash
				for(int l=0;l<n;l++){
					int e = fscanf(f,"%ld%ms%ms",&id,&name,&surname);
					if(e==-1) break;
					bucket *b = (bucket *)malloc(sizeof(bucket)); 
					b->id = id;
					b->nome = name;
					b->cognome = surname;
					ind = hash_insert(tabHash,b,m); //inserimento dello studente in tabella 
					if(ind==-1) printf("Inserimento non riuscito\n");
					cont++;
				}
				
			printf("\nSono stati inseriti %d studenti\n",cont);
			media_hit=0;
			
			// ricerca studente
				for(int k=0;k<n;k++) {
					long matricola = crea_matricola(); //si genera una matricola casuale fra la minima presente nel file e la massima per testare la ricerca
					assert(matricola>=1065687581 && matricola<=1223622928); //si controlla che la matricola generata sia compresa fra la minima presente nel file e la massima presente nel file
					int hit; // contatore per le visite degli elementi della tabella hash
					printf("\nRisultato della ricerca dello studente con matricola %ld\n",matricola);
					int result = hash_search(tabHash,matricola,m,&hit); //ricerca dello studente
					if(result!=-1) stampa_studente(tabHash[result]);
					else printf("Nessuno studente trovato con la matricola %ld\n",matricola);
					printf("Sono stati visitati %d elementi per cercare lo studente con matricola %ld\n\n",hit,matricola); //numero degli hit per ogni studente
					media_hit+=hit; //aggiorno gli hit totali
				}
				
			media_hit = (media_hit/n); //calcolo la media
			printf("\n--------  FINE RICERCA STUDENTI  -----------\n\n");
			fprintf(f2,"%d,%d\n",fattore_di_carico,media_hit);
			dealloca_tabella_studenti(tabHash,m);
			rewind(f);
			
			
	}
	fclose(f);
	fclose(f2);
	return 0;
}

