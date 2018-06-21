package it.polito.tdp.artsmia.model;

import java.util.HashMap;

public class ArtObjectIdMap extends HashMap<Integer, ArtObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtObject get(int id) {
		return super.get(id);
	}

	public ArtObject get(ArtObject artObject) {
		ArtObject old = super.get(artObject.getId());
		if (old != null) {
			return old;
		}
		super.put(artObject.getId(), artObject);
		return artObject;
	
	}
	
}
