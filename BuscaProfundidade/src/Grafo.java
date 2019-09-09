import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Grafo {
	Map<String, LinkedList<String>> vetor;
	public int tempo = 0;

	public Grafo(int n) {
		vetor = new HashMap<String, LinkedList<String>>(n);
	}

	public void addAresta(String indice, String destino) {
		LinkedList<String> list = new LinkedList<String>();
		if (vetor.containsKey(indice)) {
			list = vetor.get(indice);
		}
		list.push(destino);
		vetor.put(indice, list);
	}

	public void showSucessores() {
		for (String vertice : vetor.keySet()) {
			System.out.print("Sucessores do vértice " + vertice + ": ");
			for (String adjacentes : vetor.get(vertice)) {
				System.out.print(adjacentes + " ");
			}
			System.out.println();
		}
	}

	public boolean arcoExiste(String a, String b) {
		return vetor.get(a).contains(b) || vetor.get(b).contains(a);

	}

	public Map<String, LinkedList<String>> getVetor() {
		return vetor;
	}

	public void buscaProfundidadeStart(Grafo g) {
		// creating a hash table
		Map<String, String> cor = new HashMap<String, String>();
		Map<String, Integer> i = new HashMap<String, Integer>();
		Map<String, Integer> f = new HashMap<String, Integer>();
		Map<String, String> ante = new HashMap<String, String>();

		for (String v : g.vetor.keySet()) {
			cor.put(v, "branco");
			i.put(v, -1);
			f.put(v, -1);
			ante.put(v, null);
		}
		this.tempo = 1;
		for (String v : g.vetor.keySet()) {
			if (cor.get(v).compareTo("branco") == 0) {
				buscaProfundidadeVisit(g, v, cor, i, f, ante);
			}
		}

	}

	private void buscaProfundidadeVisit(Grafo g, String vertice, Map<String, String> cor, Map<String, Integer> i,
			Map<String, Integer> f, Map<String, String> ante) {

		cor.put(vertice, "cinza");
		i.put(vertice, this.tempo++);
		for (String v : g.vetor.get(vertice)) {
			if (cor.get(v).equals("branco")) {
				ante.put(v, vertice);
				buscaProfundidadeVisit(g, v, cor, i, f, ante);
			}
		}
		cor.put(vertice, "preto");
		f.put(vertice, this.tempo++);
		;

	}

}
