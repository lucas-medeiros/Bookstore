package Principal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import Listas.*;
import Objetos.*;

public class Principal {

	private static Scanner teclado = new Scanner(System.in);
	static ListaClientes alClientes = new ListaClientes();
	static ListaLivros alLivros = new ListaLivros();
	static ListaPedidos alPedidos = new ListaPedidos();
	static String ArqMsgErro = "Ocorreu um erro com o arquivo"; //mensagem a ser impressa caso aconteca um erro ao ler ou salvar em disco
	static int idcliente; //guarda o id do cliente depois de ele logar no sistema
	
	public static void main(String[] args) {
			int x = -1, y = -1, z = -1; //contadores para os menus
			LerDiscoClientes(); //le os arquivos ja existentes na pasta
			LerDiscoLivros();
			LerDiscoPedidos();
			while (x != 0){
				System.out.println("\n-- Menu inicial --");
				System.out.println("0 - Sair");
				System.out.println("1 - Menu administrativo");
				System.out.println("2 - Menu cliente");
				System.out.print("Informe a opcao que deseja: ");
				x = teclado.nextInt();
				teclado.nextLine();
				switch (x){
				case 0:
					System.out.println("\nAte a proxima!\n");
					break;
				case 1:
					while (y != 0){
						System.out.println("\n-- Menu administrativo --");
						System.out.println("0 - Voltar");
						System.out.println("1 - Cadastrar livro");
						System.out.println("2 - Listar todos os livros");
						System.out.println("3 - Listar todos os clientes");
						System.out.println("4 - Listar todos os pedidos");
						System.out.print("Informe a opcao que deseja: ");
						y = teclado.nextInt();
						teclado.nextLine();
						switch (y){
						case 0:
							System.out.println("Voltando\n");
							break;
						case 1:
							AddLivro();
							break;
						case 2:
							ImprimeListaLivros();
							break;
						case 3:
							ImprimeListaClientes();
							break;
						case 4:
							ImprimeListaPedidos();
							break;
						default:
							System.out.println("Numero invalido, por favor insira outro");
							break;
						}
					}
					break;
				case 2:
					while (y != 0){
						System.out.println("\n-- Menu de cliente --");
						System.out.println("0 - Voltar");
						System.out.println("1 - Cadastrar cliente");
						System.out.println("2 - Login cliente");
						System.out.print("Informe a opcao que deseja: ");
						y = teclado.nextInt();
						teclado.nextLine();
						switch (y){
						case 0:
							System.out.println("Voltando\n");
							break;
						case 1:
							AddCliente();
							break;
						case 2:
							if (LoginCliente()){
								while (z != 0){
									System.out.println("\n-- Menu de compras --");
									System.out.println("0 - Voltar");
									System.out.println("1 - Procurar livro por titulo");
									System.out.println("2 - Procurar livro lista geral");
									System.out.println("3 - Fechar pedido");
									System.out.println("4 - Listar pedidos");
									System.out.print("Informe a opcao que deseja: ");
									z = teclado.nextInt();
									teclado.nextLine();
									switch(z){
									case 0:
										System.out.println("Voltando\n");
										break;
									case 1:
										ProcuraLvroTitulo();
										break;
									case 2:
										ProcuraLivroListaGeral();
										break;
									case 3:
										SalvarDisco();
										break;
									case 4:
										ImprimeListaPedidos();
										break;
									default:
										System.out.println("Numero invalido, por favor insira outro");
										break;
									}
								}
							}else{
								System.out.println("Nome ou senha invalidos, por favor tente novamente");
							}
							break;
						default:
							System.out.println("Numero invalido, por favor insira outro");
							break;
						}
					}
				}
			}
	}

	public static void AddCliente() {
		String nome = "", email = "", senha = "";
		boolean existe = false;
		while((nome.isEmpty()) || (existe)){
			System.out.println("Insira seu nome de usuario: ");
			nome = teclado.nextLine();
			for (int i = 0; i < alClientes.getListaCliente().size(); i++) {//percorre todo o array list de clientes
				if(alClientes.getCliente(i).getNome().compareToIgnoreCase(nome) == 0){//compara o nome fornecido com os nomes ja existentes
					existe = true; //recebe true se o nome fornecido por repetido
				}
			}
			if(existe){//se o nome for repetido imprime mensagem de erro
				System.out.println("Esse nome de usuario ja existe, por favor insira outro");
			}
		}
		System.out.println("Insira seu email: ");
		email = teclado.nextLine();
		while(senha.isEmpty()){//evita uma senha vazia
			System.out.println("Insira sua senha: ");
			senha = teclado.nextLine();
			if(senha.isEmpty()){
				System.out.println("Senha invalida, por favor insira outra");
			}
		}
		
		//Cliente c = new Cliente(nome, senha, email, alClientes.getListaCliente().size(), false, null);
		//alClientes.AddCliente(c);
		
		alClientes.AddCliente(nome, senha, email);//admin = false, pedido = null
		System.out.println("Cliente adicionado com sucesso\n");
	}
	
	public static boolean LoginCliente(){
		boolean existe = false;
		System.out.println("Insira seu nome de usuario: ");
		String nome = teclado.nextLine();
		System.out.println("Insira sua senha: ");
		String senha = teclado.nextLine();
		for (int i = 0; i < alClientes.getListaCliente().size(); i++) {//percorre todo o array list de clientes
			if(alClientes.getCliente(i).getNome().compareToIgnoreCase(nome) == 0){//compara o nome fornecido com os nomes ja existentes
				existe = true; //recebe true se o nome fornecido por repetido
			}
		}
		if(existe == false){//se nao tiver nenhum usuario com o nome fornecido imprime mensagem de erro e retorna falso
			System.out.println("Nao existe nenhum usuario com esse nome");
			return false;
		}else{
			if(alClientes.ConfirmaSenha(nome, senha)){//chama a funcao para verificar se a senha e correta
				idcliente = alClientes.getCliente(nome).getID(); //salva a id do cliente se ele conseguir logar com sucesso
				System.out.println("Logado com sucesso\n");//imprime mensagem de sucesso e retorna true
				return true;
			}else{
				System.out.println("Nome de usuario ou senha incorretas\n");
				return false; //se a senha estiver incorreta imprime mensagem de erro e retorna false
			}	
		}
	}

	public static void ProcuraLvroTitulo() {
		boolean achou = false;
		int idlivro = 0;
		System.out.println("Insira o titulo do livro que deseja comprar: ");
		String titulo = teclado.nextLine();
		for (int i = 0; i < alLivros.getListaLivro().size(); i++) {//percorre todo o array list de livros comparando titulos com o titulo fornecido
			if(alLivros.getLivro(i).getTítulo().compareToIgnoreCase(titulo) == 0){
				achou = true;
				idlivro = alLivros.getLivro(i).getID(); //se o titulo for o mesmo salva o id do livro e marca achou como true
			}
		}
		if(achou == false){//imprime mensagem de erro caso o livro nao tenha sido encontrado
			System.out.println("O livro desejado nao foi encontrado");
		}else{
			alPedidos.AddPedido(idcliente, idlivro); //cria um novo pedido chamando a funcao e mandando as ids de livro e de cliente como parametro
			
			//na funcao AddPedido tem q adicionar o livro ao pedido pq pode cada pedido pode ter mais de um livro
			//entao fica algo tipo cliente.getPedido.add(Livro) 
			
			System.out.println("Livro adicionado com sucesso ao pedido");
		}
	}

	public static void ProcuraLivroListaGeral() {
		int idlivro = -1;
		ImprimeListaLivros();
		while ((idlivro < 0) || (idlivro > alLivros.getListaLivro().size())){//evita que o sistema trave caso o usuario insira um valor invalido
			System.out.println("\nInsira o ID do livro que deseja comprar");
			idlivro = teclado.nextInt();
			teclado.nextLine();
			if((idlivro < 0) || (idlivro > alLivros.getListaLivro().size())){
				System.out.println("Numero invalido, por favor insira outro");
			}
		}
		alPedidos.AddPedido(idcliente, idlivro);
	}

	public static void ImprimeListaPedidos() {
		alPedidos.OrdenaLista(); //OrdenaLista() coloca o array list de pedidos em ordem cronologica
		for (int i = 0; i < alPedidos.getListaPedido().size(); i++) {
			System.out.println(alPedidos.getPedido(i)); //chama o toString redefinido do Pedido na posicao i do array list
		}
	}

	public static void ImprimeListaLivros() {
		alLivros.OrdenaLista(); //OrdenaLista() coloca o array list de livros em ordem alfabetica de titulo
		for (int i = 0; i < alLivros.getListaLivro().size(); i++) {
			System.out.println(alLivros.getLivro(i)); //chama o toString redefinido do Livro na posicao i do array list
		}
	}

	public static void ImprimeListaClientes() {
		alClientes.OrdenaLista(); //OrdenaLista() coloca o array list de clientes em ordem alfabetica de nome
		for (int i = 0; i < alClientes.getListaCliente().size(); i++) {
			System.out.println(alClientes.getCliente(i)); //chama o toString redefinido do Cliente na posicao i do array list
		}
	}

	public static void AddLivro() {
		float valor = -1;
		System.out.println("Insira o titulo do livro que deseja cadastrar: ");
		String titulo = teclado.nextLine();
		System.out.println("Insira o autor do livro que deseja cadastrar: ");
		String autor = teclado.nextLine();
		while (valor < 0){ //evita q o valor do livro seja negativo
			System.out.println("Insira o valor do livro:");
			valor = teclado.nextFloat();
			teclado.nextLine();
			if (valor < 0){
				System.out.println("Numero invalido, por favor insira outro");
			}
		}
		alLivros.AddLivro(titulo, autor, valor);//ID = ListaLivro.size();
		System.out.println("Livro cadastrado com sucesso");
	}

	//NAO SEI SE TA FUNCIONANDO A PARTE DE LER E GRAVAR EM DISCO
	
	public static void LerDiscoClientes(){
		ObjectInputStream ListaClientesArq = null;
		ArrayList<Cliente> listaArq = new ArrayList<Cliente>();  		
		try {
			ListaClientesArq = new ObjectInputStream (new FileInputStream ("C:\\Users\\zedlucas\\Documents\\Eclipse\\Workspace\\BookStore\\src\\Listas\\ListaClientesArq.dab"));
			listaArq = (ArrayList<Cliente>) ListaClientesArq.readObject();		
		} catch (IOException ex) {
			System.out.println(ArqMsgErro + " (erro de io)");			
		} catch (ClassNotFoundException e) {
			System.out.println(ArqMsgErro + " (classe nao encontrada)");
		} finally {
			try {
				alClientes.setListaCliente(listaArq);
				ListaClientesArq.close();
			} catch (Exception ex) {
				System.out.println(ArqMsgErro); 
			}
		}
	}
	
	public static void LerDiscoLivros(){
		ObjectInputStream ListaLivrosArq = null;
		ArrayList<Livro> listaArq = new ArrayList<Livro>();  		
		try {
			ListaLivrosArq = new ObjectInputStream (new FileInputStream ("C:\\Users\\zedlucas\\Documents\\Eclipse\\Workspace\\BookStore\\src\\Listas\\ListaLivrosArq.dab"));
			listaArq = (ArrayList<Livro>) ListaLivrosArq.readObject();		
		} catch (IOException ex) {
			System.out.println(ArqMsgErro + " (erro de io)");			
		} catch (ClassNotFoundException e) {
			System.out.println(ArqMsgErro + " (classe nao encontrada)");
		} finally {
			try {
				alLivros.setListaLivro(listaArq);
				ListaLivrosArq.close();
			} catch (Exception ex) {
				System.out.println(ArqMsgErro); 
			}
		}
	}
	
	
	public static void LerDiscoPedidos(){
		ObjectInputStream ListaPedidosArq = null;
		ArrayList<Pedido> listaArq = new ArrayList<Pedido>();  		
		try {
			ListaPedidosArq = new ObjectInputStream (new FileInputStream ("C:\\Users\\zedlucas\\Documents\\Eclipse\\Workspace\\BookStore\\src\\Listas\\ListaPedidosAqr.dab"));
			listaArq = (ArrayList<Pedido>) ListaPedidosArq.readObject();		
		} catch (IOException ex) {
			System.out.println(ArqMsgErro + " (erro de io)");			
		} catch (ClassNotFoundException e) {
			System.out.println(ArqMsgErro + " (classe nao encontrada)");
		} finally {
			try {
				alPedidos.setListaPedido(listaArq);
				ListaPedidosArq.close();
			} catch (Exception ex) {
				System.out.println(ArqMsgErro); 
			}
		}
	}
	
	public static void SalvarDisco(){
		ArrayList<Cliente> ListaClientesArq = alClientes.getListaCliente(); 
		ArrayList<Livro> ListaLivrosArq = alLivros.getListaLivro();
		ArrayList<Pedido> ListaPedidosArq = alPedidos.getListaPedido();
		ObjectOutputStream alcliente = null, allivro = null, alpedido = null;
		try {
			alcliente = new ObjectOutputStream (new FileOutputStream ("C:\\Users\\zedlucas\\Documents\\Eclipse\\Workspace\\BookStore\\src\\Listas\\ListaClientesAqr.dab"));
			alcliente.writeObject(ListaClientesArq);
			
			allivro = new ObjectOutputStream (new FileOutputStream ("C:\\Users\\zedlucas\\Documents\\Eclipse\\Workspace\\BookStore\\src\\Listas\\ListaLivrosAqr.dab"));
			allivro.writeObject(ListaLivrosArq);
			
			alpedido = new ObjectOutputStream (new FileOutputStream ("C:\\Users\\zedlucas\\Documents\\Eclipse\\Workspace\\BookStore\\src\\Listas\\ListaPedidosAqr.dab"));
			alpedido.writeObject(ListaPedidosArq);
			
		} catch (IOException ex) {
			System.out.println(ArqMsgErro + " (erro de io ao salvar em disco)");
		} finally {
			try {
				alcliente.close();
				allivro.close();
				alpedido.close();
			} catch (IOException ex) {
				System.out.println(ArqMsgErro + " (erro de io tentar fechar os arquivos)");
			}
		}
	}
	
}