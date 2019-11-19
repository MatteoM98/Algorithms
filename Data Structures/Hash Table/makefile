CFLAGS = -g -O -Wall -std=c99

EXECS = tabHash

all: $(EXECS)

tabHash: tabHash.o utils.o
	gcc $(CFLAGS) -o $@ $^ -lm
	
%.o: %.c utils.h
	gcc $(CFLAGS) -o $@ -c $<

clean:
	rm *.o $(EXECS) *.csv
