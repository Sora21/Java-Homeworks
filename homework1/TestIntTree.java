/**
 * Created by pietrocenciarelli.
 */
/* Test sul corretto funzionamento del programma */
public class TestIntTree {

		public static void main(String[] args) throws NoSuchTreeException {
		// test MyIntTree
			MyIntTree t = new MyIntTree(7); // crea un albero con un solo nodo etichettato 7
			System.out.println(t.nodes()); // stampa 1
			System.out.println(t.height()); // stampa 0
			
			MyIntTree t1 = new MyIntTree(9);
			MyIntTree t2 = new MyIntTree(9);
			t1.addChild(new MyIntTree(5));
			t2.addChild(new MyIntTree(5));
			System.out.println(t1.equals(t2)); // stampa true
								
			t1.addChild(t2);
			t1.addChild(new MyIntTree(3));
			t1.visit(); // stampa 9 3 9 5 5
			
			int[] path = {2,1};
			t.addChild(t1.followPath(path));
			t.visit(); // stampa 7 5
			t.printIntTree();
			t.followPath(path); // lancia NoSuchTreeException*/				
		}
	
}
