package homework3;

import java.util.LinkedList;
//import homework3.MyNetwork.Arco;


public class TestMyNetwork {

	public static <T> void main(String[] args) throws NoSuchNodeException, NoSuchPathException {
		LinkedList<T> vertici = new LinkedList<T>();
		
		T nodo1 = (T) new Object();	
		T nodo2 = (T) new Object();
		T nodo3 = (T) new Object();	
		T nodo4 = (T) new Object();
		//T nodo5 = (T) new Object();
		//T nodo6 = (T) new Object();
		
		//int nodo1 = 1;	vertici.add(nodo1); 
		//int nodo2 = 2;	vertici.add(nodo2);
		//int nodo3 = 3;	vertici.add(nodo3);
		//int nodo4 = 4;	vertici.add(nodo4);
		//int nodo5 = 5;	vertici.add(nodo5);
		//int nodo6 = 6;	vertici.add(nodo6);
		
		MyNetwork<T> grafo = new MyNetwork<T>(vertici);
		
		grafo.addNode(nodo1); grafo.addNode(nodo2); grafo.addNode(nodo3); grafo.addNode(nodo4);
		
		grafo.addEdge(nodo1, nodo2); grafo.addEdge(nodo1, nodo3);
		grafo.addEdge(nodo2, nodo3); grafo.addEdge(nodo3, nodo4); 
		grafo.addEdge(nodo1, nodo3); //arco duplicato caso da considerare
		
		grafo.setSource(nodo1); grafo.setTarget(nodo4);
		
		System.out.println("Source = " + grafo.source());
		System.out.println("Nodo2 = " + nodo2);
		System.out.println("Nodo3 = " + nodo3);
		System.out.println("Target = " + grafo.target());
		
		//grafo.setTarget(nodo1);
		System.out.println(grafo.shortestPath().toString());	//OK
		
		
			
	}
}
