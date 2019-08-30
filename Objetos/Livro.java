package Objetos;

public class Livro {

	private String t�tulo;
	private String autor;
	private float valor;
	private int ID;
	
	public Livro(String t�tulo, String autor, float valor, int iD) {
		super();
		this.t�tulo = t�tulo;
		this.autor = autor;
		this.valor = valor;
		ID = iD;
	}
	
	public String getT�tulo() {
		return t�tulo;
	}
	
	public void setT�tulo(String t�tulo) {
		this.t�tulo = t�tulo;
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