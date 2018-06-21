package it.polito.tdp.artsmia.model;

public class ExhibitionResult {
	
	private int o1;
	private int o2;
	private int occorrenza;
	
	public ExhibitionResult(int o1, int o2, int occorrenza) {
		super();
		this.o1 = o1;
		this.o2 = o2;
		this.occorrenza = occorrenza;
	}
	public int getO1() {
		return o1;
	}
	public void setO1(int o1) {
		this.o1 = o1;
	}
	public int getO2() {
		return o2;
	}
	public void setO2(int o2) {
		this.o2 = o2;
	}
	public int getOccorrenza() {
		return occorrenza;
	}
	public void setOccorrenza(int occorrenza) {
		this.occorrenza = occorrenza;
	}
	
	
	
}
