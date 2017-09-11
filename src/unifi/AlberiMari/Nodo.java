package unifi.AlberiMari;

import java.util.ArrayList;

public class Nodo<T> {

	private Nodo<T>[] figli; // i figli del nodo
	private T info; // il valore del nodo
	private Nodo<T> padre;

	public Nodo(int ariet‡) {
		if (ariet‡ < 1) {
			System.out.println("Errore ariet‡ errata");
		} else {
			figli = new Nodo[ariet‡];
		}
	}

	public Nodo(int ariet‡, T info) {
		if (ariet‡ < 1 || info == null) {
			System.out.println("Errore nei parametri inseriti");
		} else {
			figli = new Nodo[ariet‡];
			this.info = info;
		}
	}

	public Nodo<T> getFiglio(int pos) {
		return figli[pos];
	}

	public int getArieta() {
		return figli.length;
	}

	/**
	 * ritorna il numero di figli non vuoti del nodo
	 *
	 * @return
	 */
	public int getNumeroFigli() {
		int n = 0;
		for (Nodo<T> nodo : figli) {
			if (nodo != null) {
				n++;
			}
		}
		return n;
	}

	/**
	 * restituisce il numero di nodi a partire da questo nodo (questo escluso):
	 * ex se qusto nodo ha altri 3 discendenti (non necessariamente figli di
	 * questo nodo, ma anche figli dei suoi figli) , (e qundi in totale 4) ver√†
	 * ritornato 3,altrimenti se non ha discendenti verr√† ritornato 0, il
	 * numero totale di nodi sar√† ottenuto richiamando questo mettodo +1
	 * 
	 * @return
	 */
	public int getNumeroNodi() {
		int n = this.getNumeroFigli();
		for (Nodo<T> nodo : figli) {
			if (nodo != null) {
				n += nodo.getNumeroNodi();
			}
		}
		return n;
	}

	/**
	 * restituisce una lista di figli non null contenuti in questo nodo
	 * 
	 * @return
	 */
	public ArrayList<Nodo<T>> getFigliNonVuoti() {
		ArrayList<Nodo<T>> list = new ArrayList<>();
		for (Nodo<T> nodo : figli) {
			if (nodo != null) {
				list.add(nodo);
			}
		}
		return list;
	}

	
	public void addNodo(Nodo<T> figlio, int i) {
		if (figlio != null) {
			figlio.setPadre(this);
			figli[i] = figlio;
		} else {
			System.out.println("Errore, informazione non valida");
		}
	}


	public Nodo<T> getPadre() {
		return padre;
	}

	public Nodo<T>[] getFigli() {
		return figli;
	}

	public void setFigli(Nodo<T>[] figli) {
		this.figli = figli;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public void setPadre(Nodo<T> padre) {
		this.padre = padre;
	}

	/**
	 * fa la visita anticipata a partire da questo nodo e ritorna una lista cone
	 * le informazioni contenute al loro interno
	 * 
	 * @return
	 */
	public ArrayList<T> visitaAnticipata() {
		ArrayList<T> lista = new ArrayList<>();
		lista.add(info);
		for (Nodo<T> nodo : figli) {
			if (nodo != null) {
				lista.addAll(nodo.visitaAnticipata());
			}
		}
		return lista;
	}

	/**
	 * fa la visita posticipata a partire da questo nodo e ritorna una lista
	 * cone le informazioni contenute al loro interno
	 * 
	 * @return
	 */
	public ArrayList<T> visitaPosticipata() {
		ArrayList<T> lista = new ArrayList<>();
		for (Nodo<T> nodo : figli) {
			if (nodo != null) {
				lista.addAll(nodo.visitaPosticipata());
			}
		}
		lista.add(info);
		return lista;
	}

	/**
	 * fa la visita simmetrica (da 0 a n/2 poi questo nodo ed in seguito da n/2
	 * in poi) a partire da questo nodo e ritorna una lista cone le informazioni
	 * contenute al loro interno
	 * 
	 * @return
	 */
	public ArrayList<T> visitaSimmetrica() {
		ArrayList<T> lista = new ArrayList<>();
		int c = 0;
		for (; c < figli.length / 2; c++) {
			if (figli[c] != null) {
				lista.addAll(figli[c].visitaSimmetrica());
			}
		}
		lista.add(info);
		for (; c < figli.length; c++) {
			if (figli[c] != null) {
				lista.addAll(figli[c].visitaSimmetrica());
			}
		}

		return lista;
	}

	/**
	 * ritorna una lista con le informazioni contenuta nei figli di questo nodo
	 * 
	 * @return
	 */
	public ArrayList<T> getInfoList() {
		ArrayList<T> lista = new ArrayList<>();
		for (int c = 0; c < figli.length; c++) {
			if (figli[c] != null) {
				lista.add((T) figli[c].getInfo());
			}
		}
		return lista;
	}
}
