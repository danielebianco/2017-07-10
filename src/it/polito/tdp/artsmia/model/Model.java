package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
		
	private ArtsmiaDAO dao;
	private List<ArtObject> opere;
	private SimpleWeightedGraph<ArtObject,DefaultWeightedEdge> grafo;
	private ArtObjectIdMap artObjectIdMap;
	
	public Model() {
		this.artObjectIdMap = new ArtObjectIdMap();
		this.dao = new ArtsmiaDAO();
		this.opere = dao.listObjects(artObjectIdMap);
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, opere);
		for(ExhibitionResult ex : dao.getResult(artObjectIdMap)) {
			Graphs.addEdge(grafo, artObjectIdMap.get(ex.getO1()),
					artObjectIdMap.get(ex.getO2()), ex.getOccorrenza());
		}
		
	}
	
	
	public ArtsmiaDAO getDao() {
		return dao;
	}

	public void setDao(ArtsmiaDAO dao) {
		this.dao = dao;
	}

	public List<ArtObject> getOpere() {
		return opere;
	}

	public void setOpere(List<ArtObject> opere) {
		this.opere = opere;
	}

	public SimpleWeightedGraph<ArtObject, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(SimpleWeightedGraph<ArtObject, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	public ArtObjectIdMap getArtObjectIdMap() {
		return artObjectIdMap;
	}

	public void setArtObjectIdMap(ArtObjectIdMap artObjectIdMap) {
		this.artObjectIdMap = artObjectIdMap;
	}

	public int calcolaDimensioneCC(int idObj) {

		// trova il vertice di partenza
		ArtObject start = trovaVertice(idObj);

		// visita il grafo
		Set<ArtObject> visitati = new HashSet<>();
		DepthFirstIterator<ArtObject, DefaultWeightedEdge> dfv = new DepthFirstIterator<>(grafo, start);
		while (dfv.hasNext())
			visitati.add(dfv.next());

		// conta gli elementi
		return visitati.size();
		
	}
	
	private ArtObject trovaVertice(int idObj) {
		// trova il vertice di partenza
		ArtObject start = null;
		for (ArtObject ao : this.opere) {
			if (ao.getId() == idObj) {
				start = ao;
				break;
			}
		}
		if (start == null)
			throw new IllegalArgumentException("Vertice " + idObj + " non esistente");
		return start;
}
	
}
