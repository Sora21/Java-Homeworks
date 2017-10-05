import java.util.ArrayList;


public class MyIntTree implements IntTree{
		
	private ArrayList<MyIntTree> tree = new ArrayList<MyIntTree>();
	private int valore;			// il valore del nodo corrente
	
	
	private int contato = 0;		// usato nella funzione di conteggio nodes(), indica se il nodo e' stato gia' contato o meno
	private boolean visitato = false;	// usato nella funzione di conteggio nodes(), indica se il nodo e' stato gia' visitato in precedenza 
	
	private int height = 0;			// usato nella funzione height(), indica la lunghezza del cammino piu' lungo corrente dalla radice a una foglia
	private int maxHeight = 0;		// usato nella funzione height(), indica la lunghezza del cammino piu' lungo dalla radice a una foglia
	
	
	/**Un oggetto @code MyIntTree rappresenta un albero n-ario di interi
	 * @param valore e' il valore del nodo corrente	 */
	public MyIntTree(int valore) {			
		this.tree = new ArrayList<MyIntTree>();	
		this.valore = valore;
		this.visitato = false;
	}
	
	
	/**Getter dell'arraylist di figli di t
	 * @param t albero di interi
	 * @return l'arraylist contenente i figli di t	 */
	public ArrayList<MyIntTree> getTree(MyIntTree t) {	
		return t.tree;
	}
		
	
	
	/* (non-Javadoc)
	 * restituisce il valore associato alla radice	 */
	public int getValue() {
		return valore;
	}

	/* (non-Javadoc)
	 * restituisce il numero di figli di this, 0 se e’ una foglia	 */
	public int childrenNumber() {
		return this.tree.size();	
	}

	
	/* (non-Javadoc)
	 * restituisce il numero di nodi di this, 1 se this e' una foglia	 */
	public int nodes() {					
		for (int i = 0; i < getTree(this).size(); i++) {			// in questo for ripristino le variabili da eventuali esecuzioni precedenti 
			getTree(this).get(i).visitato = false;					 
			getTree(this).get(i).contato = 0;						
		}
		return countDFS();											// svolgo il conteggio tramite una visita DFS pre-order ricorsiva
	}

	
	/* (non-Javadoc)
	 * restituisce la lunghezza del cammino piu' lungo dalla radice a una foglia	 */
	public int height() {					
		
		 if(getTree(this).size() == 0) 											// se arrivo ad una foglia ritorno 0
			 return 0;

		for(int i = 0; i < getTree(this).size(); i++){
			int altezza = this.height + getTree(this).get(i).height() + 1; 			// svolgo una chiamata ricorsiva su height() che quando 
																					// ritorna perchè trova una foglia somma 1 ad altezza
			
			if (altezza > maxHeight) 												// al termine della ricorsivita' su un nodo controllo se
				maxHeight = altezza;												// l'altezza corrente e' la piu' alta
		}
		
		return maxHeight;
	}

	
	/* (non-Javadoc)
	 * t1.equals(t2) e’ true se t1 e t2 sono isomorfi, ovvero indistinguibili ad un osservatore esterno	 */
	public boolean equals(IntTree t) {
		if (this.childrenNumber() != t.childrenNumber()) 			// controllo sul numero dei figli dei 2 nodi
			return false;		
		
		if (this.valore != t.getValue()) 							// controllo sul valore dei 2 nodi
			return false;
			
		for(int i = 0; i < this.getTree(this).size(); i++){ 
			if (!getTree(this).get(i).equals(getTree((MyIntTree) t).get(i))); 		// ricorsione di equals sul nodo figlio in posizione "i" di entrambi
				return getTree(this).get(i).equals(getTree((MyIntTree) t).get(i));
		} 
		return true;
	}

	
	/* (non-Javadoc)
	 * aggiunge child come primo figlio di this	 */
	public void addChild(IntTree child) {	
		tree.add(0, (MyIntTree) child);		
	}

	
	/* (non-Javadoc)
	 * restituisce il sottoalbero individuato da path
	 * @throws NoSuchTreeException se un tale sottoalbero non esiste */
	public IntTree followPath(int[] path) throws NoSuchTreeException {
		MyIntTree follow = this;								// usato dentro il for per spostarsi tra i nodi richiesti da path 
		
		for(int i = 0; i < path.length; i++) {
			if (follow.childrenNumber() < path[i]) 				// se il sottoalbero richiesto da path non esiste 
				throw new NoSuchTreeException();
			
			
			follow = getTree(follow).get(path[i] - 1);
		}
		
		return follow;
	}

	
	/* (non-Javadoc)
	 * stampa la sequenza di valori associati ai nodi dell’albero corrispondente
	 * ad una visita in profondita’ (depth-first) pre-order	 */
	public void visit() {
		printDFS();												// visita DFS pre-order ricorsiva
		System.out.println();									// newline a fine visita
		
	}

	
	/* (non-Javadoc)
	 * pretty prints this usando le parentesi, dove
	 * per esempio  (1 (2 3 (5) 4 ) rappresenta 
	 * l'albero di radice 1, di foglie 2, 3 e 4, dove 3 ha come figlio 5	 */
	public void printIntTree() {					
		System.out.print("(" + this.valore + " ( ");				//stampo la radice
		
		recursivePrettyPrint();										//DFS ricorsiva pre-order, sui figli della radice
		System.out.println(")");
	}	
	
	
	/** DFS ricorsiva pre-order che stampa valore del nodo corrente 
	 *  in ordine da sinistra verso destra	 */
	public void printDFS() {				
		System.out.print(this.valore + " ");
		
		for(int i = 0; i < getTree(this).size(); i++)
			getTree(this).get(i).printDFS(); 
	}
	
	
	/** DFS ricorsiva pre-order che svolge il conto dei nodi
	 * @return il numero totale dei nodi nell'albero this, alla funzione nodes()	 */
	public int countDFS() {
		
		if (!visitato) {										// il nodo viene contato e segnato come visitato se non lo era gia'
			contato++;
			visitato = true;
		 }
		
		for(int i = 0; i < getTree(this).size(); i++) 
			contato += getTree(this).get(i).countDFS(); 		// ad ogni ricorsione su un nuovo nodo viene aumentato il valore di contato
		
		return contato;
	}
	

	
	/** DFS ricorsiva pre-order, sui figli della radice
	 * stampa il valore del nodo e all'occorrenza la parentesi ")" quando si risale l'albero	 */
	public void recursivePrettyPrint() {					
				
		for(int i = 0; i < getTree(this).size(); i++) {
			System.out.print(getTree(this).get(i).valore + " ");
			
			if (getTree(this).get(i).height() > 0) 				// trovo un nodo con figli, stampo la parentesi "("
				System.out.print("( ");
			
			getTree(this).get(i).recursivePrettyPrint();		// chiamata ricorsiva sui vari figli
		}
		
		if (this.height() == 1) 								// comincio a risalire l'albero, stampo la parentesi ")"
			System.out.print(") ");								
	}
	
}
