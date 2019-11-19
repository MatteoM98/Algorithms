#include "utils.h"
#define ntest 8

int main(int argc,char *argv[])
{
    
    if(argc!=3)
    {
        fprintf(stderr,"Uso: %s nomefile1 nomefile2\n",argv[0]);
        exit(-1);
    }
    
    //creazione primo file
    char *nomefile1 = strdup(argv[1]);
    strcat(nomefile1,".csv");
    FILE *f1 = fopen(nomefile1,"wt");
    fprintf(f1,"Numero Elementi, Time ABR, Time QuickSort\n");
    
    //creazione secondo file
    char *nomefile2 = strdup(argv[2]);
    strcat(nomefile2,".csv");
    FILE *f2 = fopen(nomefile2,"wt");
    fprintf(f2,"Numero Elementi, Hit ABR, Hit Binary\n");
    
    srand(time(NULL));
	int dimensioni[ntest] = { 10, 25, 50, 100, 200, 500, 1000, 2000 };
	int hittot_array = 0;
	int hittot_albero = 0;
	
	
	for(int i=0;i<ntest;i++)
	{
		//punto 1 e 2
		int dim = dimensioni[i];
		int *array = random_array(dim);
		Albero root = NULL;
		
	      	//========ORDINAMENTO=======   //punto 3
            clock_t T1 = clock();
            for(int i=0;i<dim;i++) root = insert_ABR(root,array[i]);
            clock_t T2 = clock();
            double timealbero = (double)(T2 - T1)/CLOCKS_PER_SEC;
            printf("Tempo impiegato per la creazione dell'albero: %lf\n",timealbero);
            
            T1 = clock();
            qsort(array,dim,sizeof(int),intcmp);
            T2 = clock();
            double timequick = (double)(T2 - T1)/CLOCKS_PER_SEC;
            printf("Tempo impiegato per l'ordinamento dell'array: %lf\n",timequick);
            
            //=======STAMPA===========    //punto 4-5
            printf("Stampa dell'array: \n");
            stampa_array(array,dim);
            printf("Stampa dell'albero: \n");
            visitaDFSRicorsiva_ABR(root);
            
            
            //======RICERCA==========  //punto 6
            int nrandom = rand()%MAX;
            
            //array
            int hit=0;
            T1 = clock();
            int trovato = binarySearch(array,0,dim,nrandom,&hit);
            T2 = clock();
            if(trovato==-1) printf("Elemento %d non trovato all'interno dell'array\n",nrandom);
            else  printf("Elemento %d trovato all'interno dell'array\n",nrandom);
            double timebinary = (double)(T2 - T1)/CLOCKS_PER_SEC;
            printf("Tempo impiegato per la ricerca nell'array: %lf\n",timebinary);
            printf("Numero di hit: %d\n",hit);
            
            //albero
            int hit2=0;
            T1 = clock();
            ricerca_albero(root,nrandom,&hit2);
            T2 = clock();
            double timetree = (double)(T2 - T1)/CLOCKS_PER_SEC;
            printf("Tempo impiegato per la ricerca nell'albero: %lf\n",timetree);
            printf("Numero di hit: %d\n",hit2);
        
        
        
        
            // stampe su file
            fprintf(f1,"%d,%lf,%lf\n",dim,(timealbero*1000000),(timequick*1000000));         // salva nel file il tempo in microsecondi
            fprintf(f2,"%d,%d,%d\n",dim,hit,hit2);
        
            //=====DEALLOCAZIONE====== //punto 7 
            free(array);
            destroy_albero(root);
            
            //incremento hit totali
            hittot_array+=hit;
            hittot_albero+=hit2;
         
		}
		
		//stampo la media degli hit
		printf("La media totale degli hit effettuati dall'array risulta essere pari a %d\n",hittot_array/ntest);
		printf("La media totale degli hit effettuati dall'albero risulta essere pari a %d\n",hittot_albero/ntest);
    
        //chiusura file
        fclose(f1);
        fclose(f2);
        free(nomefile1);
        free(nomefile2);
	
		
		return 0;           
            
	}
		

