package unifi.AlberiMari;

import java.util.ArrayList;

public class Tree<T> {

	private final int arietà;
	private Nodo<T> radice;

	public Tree(int n) {
		if (n < 1) {
			System.out.println("Errore arietà errata");
		}
		this.arietà = n;
	}

	public void addRadice(T rad) {
		addRadice(new Nodo<>(arietà, rad), 0);
	}

	// Metodo che inserisce la nuova radice: se non c'è nessuna radice mette il
	// nodo passato come parametro. Se invece è presente un'altra radice la
	// scambia

	public void addRadice(Nodo<T> rad, int pos) {
		if (rad == null) {
			System.out.println("Errore, radice non valida.");
		}
		if (haveRoot()) {// controlla se c'è la radice-> true se non c'è
			this.radice = rad;
			this.radice.setPadre(null);
		} else {
			// scambio le radici
			rad.addNodo(radice, pos);
			radice = rad;
			rad.setPadre(null);
		}
	}

	// Metodo che controlla la presenza della radice
	public boolean haveRoot() {
		if (radice == null) {
			return true;
		} else {
			return false;
		}
	}

	public int getArieta() {
		return arietà;
	}

	public Nodo<T> getRadice() {
		return radice;
	}

	public ArrayList<T> visitaAnticipata() {
		if (haveRoot()) {
			return new ArrayList<>();
		}
		return radice.visitaAnticipata();

	}

	public ArrayList<T> visitaPosticipata() {
		if (haveRoot()) {
			return new ArrayList<>();
		}
		return radice.visitaPosticipata();

	}

	public ArrayList<T> visitaSimmetrica() {
		if (haveRoot()) {
			return new ArrayList<>();
		}
		return radice.visitaSimmetrica();

	}

	public int getNodi() {
		if (haveRoot()) {
			return 0;
		}
		return radice.getNumeroNodi() + 1;
	}

	// Controllo la validità dell'albero e del non a cui verrà collegato
	// l'albero inseriti come parametri.
	public void insertSottoAlbero(Tree<T> albero, Nodo<T> padre, int pos) {

		if (albero == null || albero.haveRoot()) {
			System.out.println("Errore nell'albero passato come parametro");
		}

		if (padre == null) {
			System.out.println("Nodo padre non valido");
		}
		padre.addNodo(albero.radice, pos);
	}

}
