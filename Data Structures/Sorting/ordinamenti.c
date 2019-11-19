#include "utils.h"

#define numeroRun 10
#define ntest 13



int main(int argc, char *argv[]){
	if(argc!=2)
	{
		printf("Uso: %s nomefile",argv[0]);
		exit(-1);
	}
	struct timeval T1, T2;
	char *nome = strcat(argv[1],".csv");
	FILE *f = fopen(nome,"wt");
	if(f==NULL) die("Apertura file fallita");

	int dimensioni [ntest] = {5,10,25,50,100,200,500,1000,2000,5000,10000,20000,50000};
	srand(time(NULL));
	fprintf(f,"%s,%s,%s,%s\n","Numero Run","timeBubble","timeQuick","timeHeap");
	for(int i=0;i<ntest;i++)
	{
		int n = dimensioni[i];
		long timeBubble = 0;
		long timeQuick = 0;
		long timeHeap = 0;
		long tempodiesecuzione=0;
        long seconds, useconds;
		int *testArray = creaArrayRandom(n);
		
			for(int k=1;k<numeroRun;k++)
			{
				 int *arraydaordinare = copyArray(testArray,n);
				 gettimeofday(&T1,NULL);
				 bubbleSort(arraydaordinare,n);
				 gettimeofday(&T2,NULL);
				 seconds = T2.tv_sec - T1.tv_sec;
                 useconds = T2.tv_usec - T1.tv_usec;
                 tempodiesecuzione = ((seconds) * 1000 + useconds/1000.0) + 0.5;
				 timeBubble+=tempodiesecuzione;
				 free(arraydaordinare);
				 
				 int *arraydaordinare1 = copyArray(testArray,n);
				 gettimeofday(&T1,NULL);
				 quickSort(arraydaordinare1,0,n-1);
				 gettimeofday(&T2,NULL);
				 seconds = T2.tv_sec - T1.tv_sec;
                 useconds = T2.tv_usec - T1.tv_usec;
                 tempodiesecuzione = ((seconds) * 1000 + useconds/1000.0) + 0.5;
				 timeQuick+=tempodiesecuzione;
				 free(arraydaordinare1);
				 
				 int *arraydaordinare2 = copyArray(testArray,n);
				 gettimeofday(&T1,NULL);
				 heapSort(arraydaordinare2,n);
				 gettimeofday(&T2,NULL);
				 seconds = T2.tv_sec - T1.tv_sec;
                 useconds = T2.tv_usec - T1.tv_usec;
                 tempodiesecuzione = ((seconds) * 1000 + useconds/1000.0) + 0.5;
				 timeHeap+=tempodiesecuzione;
				 free(arraydaordinare2); 
		 
			}
		printf("-------RISULTATI TEST N %d con n=%d-----------\n",i+1,dimensioni[i]);
		printf("Tempo medio BubbleSort: %ld millisecondi\n",(timeBubble/numeroRun));
		printf("Tempo medio QuickSort: %ld millisecondi\n",(timeQuick/numeroRun));
		printf("Tempo medio HeapSort: %ld millisecondi\n\n\n",(timeHeap/numeroRun));
		fprintf(f,"Dim [%d],%ld,%ld,%ld\n",dimensioni[i],(timeBubble/numeroRun),(timeQuick/numeroRun),(timeHeap/numeroRun));
		free(testArray);
	}
	fclose(f);
	return 0;
}
