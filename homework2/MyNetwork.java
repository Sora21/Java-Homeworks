package homework3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class MyNetwork<T> implements Network<T>  {
	
	private LinkedList<T> vertici = new LinkedList<T>();
	private LinkedList<Arco> archi = new LinkedList<Arco>();
	private T source = null; 
	private T target = null; 
	
	
	/**Un oggetto @code MyNetwork rappresenta una rete G(V, E, s, t) 
	 * @param V e' una lista contenente i nodi generici di tipo <T> 
	 * @param E e' una lista contenente gli archi (rappresentati come liste a loro volta, vd. @code Arco)
	 * @param s e' il nodo iniziale (source) 	
	 * @param t e' il nodo destinazione	(target)	*/
	public MyNetwork(LinkedList<T> V,  LinkedList<Arco> E, T s, T t ) {		 
		this.vertici = V;	
		this.archi  = E;
		this.source = s;
		this.target = t;
	}
	
	/** Costruttore di @code MyNetwork dove source e target non sono stati ancora settati
	 * @param V e' una lista contenente i nodi generici di tipo <T>
	 * @param E e' una lista contenente gli archi (rappresentati come liste a loro volta, vd. @code Arco)	 */
	public MyNetwork(LinkedList<T> V,  LinkedList<Arco> E) {		//dove source e target non settati
		this(V, E, null, null);
	}
	
	/** Costruttore di @code MyNetwork composta di solo nodi
	 * @param V e' una lista contenente i nodi generici di tipo <T>	 */
	public MyNetwork(LinkedList<T> V) {		//dove ho solo nodi senza archi, source e target non settati
		this(V, new LinkedList<Arco>(), null, null);
	}
	
	
	/** La sotto-classe @code Arco viene usata per creare oggeti di tipo <Arco>   
	 * Ciascun <Arco> e' una ArrayList di 2 elementi, dove il primo e' 
	 * il nodo s, mentre il secondo e' il nodo t
	 * (la scelta di usare una ArrayList invece di LinkedList e' data dal minor costo 
	 * per le operazioni di get) 	 */
	public class Arco {
		private ArrayList<T> arco = new ArrayList<T>();
		
		/** Un oggetto @code Arco rappresenta un arco diretto e(p, a) 
		 * @param p il nodo di partenza
		 * @param a il nodo di arrivo	*/
		public Arco(T p, T a) {		
			this.arco.add(p);
			this.arco.add(a);
		}				

		public T getNodoPartenza() {
			return arco.get(0);
		}
		
		public T getNodoArrivo() {
			return arco.get(1);
		}
		
	}
	
	
	/* (non-Javadoc)
	 * restituisce il nodo sorgente di this (null se la sorgente non e’ settata)  */
	public T source() {
		if (source == null)
			return null;
		
		return source;
	}

	
	/* (non-Javadoc)
	 * restituisce il nodo destinarione di this (null se la sorgente non e’ settata)  */
	public T target() {
		if (target == null)
			return null;
		
		return target;
	}

	
	/* (non-Javadoc)
	 * setta la sorgente della rete a newsource
	 * @throws NoSuchNodeException se newsource non e’ un nodo della rete	 */
	public void setSource(T newsource) throws NoSuchNodeException {
		if (!vertici.contains(newsource))			// se newsource non è contenuto in vertici
			throw new NoSuchNodeException();
		
		this.source = newsource;
	}

	
	/* (non-Javadoc)
	 * setta la destinazione della rete a newsource
	 * @throws NoSuchNodeException se newtarget non e’ un nodo della rete	 */
	public void setTarget(T newtarget) throws NoSuchNodeException {
		if (!vertici.contains(newtarget))			// se newtarget non è contenuto in vertici
			throw new NoSuchNodeException();
		
		this.target = newtarget;
	}

	
	/* (non-Javadoc)
	 * aggiunge a this un nuovo nodo v
	 * non fa nulla se v e’ gia’ un nodo della rete	 */
	public void addNode(T v) {
		if (!vertici.contains(v))			// se v non è già contenuto in vertici
			this.vertici.add(v);
	}

	
	/* (non-Javadoc)
	 * aggiunge a this un nuovo arco, con partenza p e arrivo a
	 * @throws NoSuchNodeException se p oppure a non appartengono alla rete	 */
	public void addEdge(T p, T a) throws NoSuchNodeException {
		if (!vertici.contains(p) || !vertici.contains(a))			// se p oppure a non sono contenuti in vertici
			throw new NoSuchNodeException();
				
		Arco nuovo = new Arco(p, a);
		if (!archi.contains(nuovo))						// lo aggiungo solo se l'arco non è duplicato
			this.archi.add(nuovo);
	}

	
	/* (non-Javadoc)
	 * Servendosi della BFS, visita la rete trovando il cammino più breve tra source e target
	 * per ogni nodo in percorso, esiste uno o piu' archi che li collegano
	 * @return percorso, una lista minimale <t1, ..., tn> di nodi di tipo <T> ottenuta traite BFS
	 * @throws NoSuchPathException se source o target non sono settati
	 * @throws NoSuchPathException se non c’e’ path che collega i nodi source e target */
	public List<T> shortestPath() throws NoSuchPathException {		
		if (source == null || target == null)			
			throw new NoSuchPathException();
		
		List<T> percorso = new ArrayList<T>();
		
		if (source() == target()) {		//caso source = target (sono lo stesso nodo)
			percorso.add(source);
			return percorso;
		}
		
		Map<T,T> padriMap = this.BFS(source(), target());		//Memorizzo i risultati della BFS in una
																//Map(Key=Figlio, Value=Padre)
		
		T tempNodo = target();
		percorso.add(tempNodo);
				
		
		while (padriMap.get(tempNodo) != source) {				//salgo nella rete, dal nodo target fino a source
			
			if (padriMap.get(tempNodo) != null) {				//se il nodo corrente ha un padre (source escluso)
				percorso.add(padriMap.get(tempNodo));			//aggiungo il padre al percorso
				tempNodo = padriMap.get(tempNodo);
			}
			else 												//se il nodo corrente non ha un padre lancio l'eccezione
				throw new NoSuchPathException();
		}
		
		percorso.add(source());
		Collections.reverse(percorso);							//ribalto l'ordine degli elementi del percorso
		return percorso;
	}

	
	/** Implementazione Breadth First Search(source, target)
	 * @param s e' il nodo iniziale (source) 	
	 * @param t e' il nodo destinazione	(target)
	 * @return padri, una mappa del tipo Map(Key=Figlio, Value=Padre)	 */
	public Map<T, T> BFS(T s, T t)	{
		
		List<T> nodiEsaminati = new ArrayList<T>();	//creo una lista vuota
		Queue<T> queue = new LinkedList<T>();		//creo una queue vuota
	    Map<T, T> padri=new HashMap<T, T>();
	    
	    nodiEsaminati.add(this.source());			//aggiungo source alla lista
	    queue.add(this.source());					//q.enqueue(source)
	    
	    while(!queue.isEmpty()) {
	        T current = queue.remove();				//q.dequeue(source)
	        
	        for (Arco arco : archi) {
	        	if (arco.getNodoPartenza() == current) {  	//per ogni nodo n, (cioe' arco.getNodoArrivo()), adiacente a current:
	        		
	        		if (!nodiEsaminati.contains(arco.getNodoArrivo())){	//se n non e' gia' nella lista:
	        			nodiEsaminati.add(arco.getNodoArrivo());		//aggiungo n alla lista
	        			padri.put(arco.getNodoArrivo(), current);		//Map(Key=Figlio, Value=Padre)
	        			queue.add(arco.getNodoArrivo());			////q.enqueue(n)
	        		}
	        	}	        		
	        }  
	    }
		return padri;
	}

}
