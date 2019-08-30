package Listas;

import java.util.ArrayList;
import Objetos.Cliente;

public class ListaClientes {

	private ArrayList<Cliente> ListaCliente;

	public void AddCliente(String nome, String senha, String email) {
		
	}

	public void OrdenaLista() {

	}

	public void RemoveCliente(int i) {

	}

	public boolean ConfirmaSenha(String nome, String senha) {
		return true;

	}

	public Cliente getCliente(int i) {
		return null;
	}

	public void setCliente(int i) {

	}

	public ArrayList<Cliente> getListaCliente() {
		return ListaCliente;
	}

	public void setListaCliente(ArrayList<Cliente> listaCliente) {
		ListaCliente = listaCliente;
	}
	
	public Cliente getCliente(String nome){
		for (int i = 0; i < ListaCliente.size(); i++) {
			if (ListaCliente.get(i).getNome().compareTo(nome) == 0){
				return ListaCliente.get(i);
			}
		}
		return null;
	}
	
	

}
