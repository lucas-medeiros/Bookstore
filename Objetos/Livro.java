package Objetos;

public class Livro {

	private String título;
	private String autor;
	private float valor;
	private int ID;
	
	public Livro(String título, String autor, float valor, int iD) {
		super();
		this.título = título;
		this.autor = autor;
		this.valor = valor;
		ID = iD;
	}
	
	public String getTítulo() {
		return título;
	}
	
	public void setTítulo(String título) {
		this.título = título;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}

}