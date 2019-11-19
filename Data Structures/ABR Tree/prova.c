#include "utils.h"

int main(int argc,char *argv[])
{
	Albero al = makeTree(10);
	visitaBFS(al);
	Albero p = NULL;
	get_parent(al,5,NULL,p);
	stampa_nodo(p);
}
