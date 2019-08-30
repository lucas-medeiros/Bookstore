package Objetos;

public class Cliente {

	private String nome;
	private String senha;
	private String email;
	private int ID;
	private boolean admin;
	private Pedido pedido;
	
	public Cliente(String nome, String senha, String email, int iD, boolean admin, Pedido pedido) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		ID = iD;
		this.admin = admin;
		this.pedido = pedido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}