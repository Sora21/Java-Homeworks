# Java-Homeworks



### Homework 1 
Definire una classe Java MyIntTree che implementi il tipo dato astratto degli alberi di interi. 
Ciascun nodo di un albero deve avere un valore intero associato (accessibile tramite il metodo getValue),
e puo' avere un numero arbitrario (ma finito), anche zero, di figli, ciascuno dei quali e' a sua volta un albero.
MyIntTree deve implementare l'interfaccia IntTree; i metodi sono spiegati nei commenti.



### Homework 2
**Definizioni**. Un grafo orientato *G(V, E)* e' costituito da un insieme *V* di vertici, o nodi, ed un insieme *E* di archi.
Ogni arco *e* ha associati un nodo *p(e) ∈ V* , il suo nodo di partenza, ed uno, *a(e) ∈ V* , di arrivo.
Un cammino orientato e' una sequenza *< e1, . . . , en >* di archi, dove *a(ei) = p(ei+1)* per i = 1, . . . , n − 1.
Se la sequenza e' non vuota, i nodi *p(e1)* e *a(en)* sono chiamati rispettivamente inizio e fine del cammino, e *n* e' la sua lunghezza.
Una rete *N (V, E, s, d)* e' un grafo orientato *GN (V, E)* con due nodi speciali *s ∈ V* e *t ∈ V*, chiamati rispettivamente source (sorgente) e target (destinazione) della rete. 
Un path e' un cammino orientato (eventualmete ciclico) da source a target.

**Obiettivo**. Implementare in Java il tipo di dato astratto delle reti di T, dove T
denota il tipo generico dei nodi della rete. Scrivere un programma che calcola il (un) path di lunghezza minima in una rete.

Scrivere una classe MyNetwork <T> che implementi l'interfaccia Network
