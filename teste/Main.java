package teste;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

class Main {

	static ArrayList<Aviao> listaAviao = new ArrayList<Aviao>();
	static ArrayList<Voo> listaVoo = new ArrayList<Voo>(10);
	static ArrayList<Passageiro> listaPassageiro = new ArrayList<Passageiro>();
	
	static int numAviao = -1;
	static int numVoo = -1;

    public static void main(String[] args) {
        int opcao = 0;
        opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                    "1 - PARÂMETROS DO SISTEMA\n" +
                    "2 - RESERVA DE PASSAGENS\n" +
                    "3 - SAIR"));
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    parametrosDoSistema();
                    break;
                case 2:
                    if (listaAviao.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há aviões cadastrados!");
                        MenuPrincipal();
                        break;
                    }
                    reservaDePassagens();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        }
    }

    public static void MenuPrincipal(){
        int opcao = 0;
        opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                    "1 - PARÂMETROS DO SISTEMA\n" +
                    "2 - RESERVA DE PASSAGENS\n" +
                    "3 - SAIR"));
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    parametrosDoSistema();
                    break;
                case 2:
                    if (listaAviao.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há aviões cadastrados!");
                        MenuPrincipal();
                        break;
                    }
                    reservaDePassagens();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        }
    }

    public static void parametrosDoSistema() {
        	int escolha = 0;
            do {
                escolha = Integer.parseInt(JOptionPane.showInputDialog(
                        "PARÂMETROS DO SISTEMA:\n" +
                        "1 - CADASTRAR AERONAVE\n" +
                        "2 - CADASTRAR VOO\n" +
                        "3 - VOLTAR"));
                switch (escolha) {
                    case 1:
                    	cadastrarAeronave();
                        break;
                    case 2:
                    	cadastrarVoo();
                        break;
                    case 3:
                        MenuPrincipal();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                        break;
                }
            } while (escolha != 0); // Cadastra aeronave apenas uma vez no início
        }

	public static String cadastrarAeronave() {
		String modelo;
		int fileiras, assentos;

		while (true) {
			try {
				modelo = JOptionPane.showInputDialog(null, "Insira o modelo da aeronave:");
					if (modelo.length() < 1) {
						JOptionPane.showMessageDialog(null, "O nome precisa ter mais de um caractere!");
						continue;
					}
						String Fil = JOptionPane.showInputDialog(null, "Insira o número de fileiras da aeronave:");
						if (Fil == null) {
							JOptionPane.showMessageDialog(null, "Operação cancelada!");
							return("Cancel");
						}
						fileiras = Integer.parseInt(Fil);
						String Ass = JOptionPane.showInputDialog(null, "Insira o número de assentos por fileira:");
						if (Ass == null) {
							JOptionPane.showMessageDialog(null, "Operação cancelada!");
							return("Cancel");
						}
						assentos = Integer.parseInt(Ass);

						listaAviao.add(new Aviao(modelo, fileiras, assentos));
						break;
					}

      //tratando excessões
			catch (NumberFormatException |  NullPointerException e){
				 if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
				}
				 else if (e.getClass().toString().equals("class java.lang.NullPointerException")){
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				 }
			}
		}

    //aviso da criação do avião para o usuário
		numAviao++;
		JOptionPane.showMessageDialog(null, "Avião " + listaAviao.get(numAviao).modelo + " criado com sucesso.");
		return("Success");
	}

	public static String cadastrarVoo() {
		while (true) {
			try {
			String Avi = JOptionPane.showInputDialog(null, "Insira o número da Aeronave:");
			if (Avi == null) {
				JOptionPane.showMessageDialog(null, "Operação cancelada!");
				return("Cancel");
			}
			int aviao = Integer.parseInt(Avi);
			int total = listaAviao.size();
			if (aviao > total) {
				JOptionPane.showMessageDialog(null, "O avião selecionado não existe.\nExistem " + listaAviao.size() + " aeronaves cadastradas");
			}
			else {
				String Num = (JOptionPane.showInputDialog(null, "Insira o número do voo:"));
				if (Num == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				}

				int num = Integer.parseInt(Num);
				String data = JOptionPane.showInputDialog(null, "Insira a data do voo:");
				if (data.length() > 0) {
					String hora = JOptionPane.showInputDialog(null, "Insira a hora do voo:");
					if (hora.length() > 0) {
						numVoo++;
						listaVoo.add(numVoo,new Voo(listaAviao.get(aviao-1), num, data, hora));
						break;
					}
					else 
						JOptionPane.showMessageDialog(null, "A hora não pode ser vazia!");
				}	
				else
					JOptionPane.showMessageDialog(null, "A data não pode ser vazia!");	
				}
			}

			catch (NumberFormatException |  NullPointerException | IndexOutOfBoundsException  e){
				 if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
				}
				 else if (e.getClass().toString().equals("class java.lang.NullPointerException")){
					JOptionPane.showMessageDialog(null, "Operação cancelada!");	
					return("Cancel");
				 }
				 else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					 JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
				 }
			}
		}

		JOptionPane.showMessageDialog(null, "Voo " + listaVoo.get(numVoo).getNro() + " criado com sucesso.");
		return("OK");
	}

    public static void reservaDePassagens() {
        int escolha = 0;
        do {
            escolha = Integer.parseInt(JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                    "1 - Reservar passagem\n" +
                    "2 - Mostrar Lugar\n" +
                    "3 - Consulta Reserva\n" +
                    "4 - Voltar"));
            switch (escolha) {
                case 1:
                    fazerReserva();
                    break;
                case 2:
                    consultaLugar();
                    break;
                case 3:
                    consultaReserva();
                    break;
                case 4:
                    MenuPrincipal();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (escolha != 0);
    }

    /*fazer reserva*/
	public static String fazerReserva() {

		boolean lugar;
		int num = 0, fileira = 0, assento = 0;
		String nome = "", cpf = "";
		Iterator<Voo> itr = listaVoo.iterator();
		
		if (itr.hasNext() == false) {
			JOptionPane.showMessageDialog(null,"Nenhum voo cadastrado!");
			return("Noo flights");
		}
		
		while (true) {
			try {

				nome = JOptionPane.showInputDialog(null, "Insira o nome do passageiro:");
				if (nome == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				}
				if (nome.length() < 1) {
					JOptionPane.showMessageDialog(null, "O nome do passageiro não pode ser vazio!");
					continue;
				}

				cpf = JOptionPane.showInputDialog(null, "Insira o CPF do passageiro:");
				if (cpf == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				}
				if (cpf.length() < 1) {
					JOptionPane.showMessageDialog(null, "O CPF do passageiro não pode ser vazio!");
					continue;
				}

				String Num = JOptionPane.showInputDialog(null, "Insira o número do voo desejado:");
				if (Num == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				}
				if (Num.length() == 0) {
					JOptionPane.showMessageDialog(null, "O número do voo não pode ser vazio!");
					continue;
				}
                num = Integer.parseInt(Num);

				String Fil = JOptionPane.showInputDialog(null, "Insira o número da fileira:");
				if (Fil == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				}
				if (Fil.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número da fileira não pode ser vazio!");
					continue;
				}
				fileira = Integer.parseInt(Fil);

				String Ass = JOptionPane.showInputDialog(null, "Insira o número do assento:");
				if (Ass == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
					return("Cancel");
				}
				if (Ass.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número do assento não pode ser vazio!");
					continue;
				}
				assento = Integer.parseInt(Ass);
			}

			catch (NumberFormatException |  NullPointerException | IndexOutOfBoundsException e){
				 if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
				}
				 else if (e.getClass().toString().equals("class java.lang.NullPointerException")){
					JOptionPane.showMessageDialog(null, "Operação cancelada!");	
					return("Cancel");
				 }
				 else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					 JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
					 continue;
				 }
				 else if ((e.getClass().toString().equals("class java.lang.ArrayIndexOutOfBoundsException"))) {
					 JOptionPane.showMessageDialog(null, "Operação cancelada!");	
						return("Cancel");
				 }
			}
            
        //sequência de verificação da disponibilidade do lugar
			int len = listaVoo.size();
			for (int k = 0; k < len; k++) {
				 Voo voo = listaVoo.get(k);
				if (voo.getNro() == num) {
					lugar = voo.getAeronave().verificaLugarOcupado((fileira - 1), (assento - 1));

          //caso o lugar esteja disponível
					if (lugar == false) {
						voo.getAeronave().setPassageiro((fileira - 1), (assento - 1), new Passageiro(nome, cpf));

            //lugar é reservado, e aviso é mostrado ao usuário
						listaPassageiro.add(new Passageiro(nome, cpf));
						JOptionPane.showMessageDialog(null, String.format("Reserva realizada com sucesso!\n\nFileira:      [%d]\nAssento:  [%d]", fileira, assento));
						return("OK");
					}

          //caso o lugar esteja ocupado
					else if(lugar == true) {
						JOptionPane.showMessageDialog(null,"O lugar está ocupado.\nPor favor, escolha outro!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"O número do voo informado não existe.\nPor favor, escolha outro!");
				}
				
			}
		
		}
	}

	/*consulta lugares disponíveis*/
	public static String consultaLugar() {
		Iterator<Voo> itr = listaVoo.iterator();
		
    //caso não hajam voos cadastrados
		if (itr.hasNext() == false) {
			JOptionPane.showMessageDialog(null,"Nenhum voo cadastrado!");
			return("Noo flights");
		}

    //sequência de consulta de lugar
		while (true) {
			int num = 0;
			try {
				String sNum = (JOptionPane.showInputDialog("Digite o número do Voo:"));
				if (sNum.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número do voo não pode ser vazio!");
					continue;
				}
				num = Integer.parseInt(sNum);
			}

      //tratando excessões
			catch (NumberFormatException |  NullPointerException | IndexOutOfBoundsException  e){
				 if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
					continue;
				}
				 else if (e.getClass().toString().equals("class java.lang.NullPointerException")){
					JOptionPane.showMessageDialog(null, "Operação cancelada!");	
					return("Cancel");
				 }
				 else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					 JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
					 continue;
				 }
			}

      //lê na lista de voos quantos lugares disponíveis existem no voo escolhido
			int len = listaVoo.size();
			for (int k = 0; k < len; k++) {
				 Voo voo = listaVoo.get(k);
				if (voo.getNro() == num) {
					int lugar = 0;
					for (int i = 0; i < voo.getAeronave().lugares.length; i++) {
						for (int j = 0; j < voo.getAeronave().lugares[0].length; j++) {
							if (voo.getAeronave().lugares[i][j] == null) {
								lugar++;
							}
						}
					}
        
        //mostra ao usuário
				JOptionPane.showMessageDialog(null, "O Voo de número " + voo.getNro() + " tem " + lugar + " lugares disponível(s)");
				return("Success");
				} 
				else {

          //caso o voo escolhido não exista
					JOptionPane.showMessageDialog(null,"O número do voo não existe.\nPor favor, escolha outro!");
				}
			}
		}
	}
	
	/*consulta reservas concluídas*/
	public static String consultaReserva() {

    //inicializa variiaveis e o iterator
		 Iterator<Voo> itr = listaVoo.iterator();
		 int len = listaVoo.size();
		 int num = 0;

    //caso não existam voos cadastrados
		if (itr.hasNext() == false) {
			JOptionPane.showMessageDialog(null,"Nenhum voo cadastrado!");
			return("Noo flights");
		}

    //sequência de consulta de reserva
		while (true) {
			try {
				String sNum = (JOptionPane.showInputDialog("Digite o número do Voo:"));
				if (sNum.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número do voo não pode ser vazio!");
					continue;
				}
				num = Integer.parseInt(sNum);
				
			}

      //tratando excessões
			catch (NumberFormatException |  NullPointerException | IndexOutOfBoundsException  e){
				 if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
					continue;
				}
				 else if (e.getClass().toString().equals("class java.lang.NullPointerException")){
					JOptionPane.showMessageDialog(null, "Operação cancelada!");	
					return("Cancel");
				 }
				 else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					 JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
					 continue;
				 }
			}
			  //contabiliza reservas de lugar
			  for (int k = 0; k < len; k++) {
				  Voo voo = listaVoo.get(k);
				  if (voo.getNro() == num) {
	
			      //inicializa índices
			      int i, j;
			    
			    //pesquisa quantos lugares estão reservados
			      String numb = " ";
			      for (i = 0; i < voo.getAeronave().lugares.length; i++) {
			        for (j = 0; j < voo.getAeronave().lugares.length; j++) {
			          if (voo.getAeronave().lugares[i][j] != null) {
			            numb = numb + "[" + (i+1) + "][" + (j+1) + "]\n";
			          }
			        }
			      }

            //mostra matriz de lugares reservados ao usuário
			      JOptionPane.showMessageDialog(null,"O voo de número " + num + " tem os seguintes lugares reservados: \n\n" + numb);
			      return("Success");
			    }

         //caso o voo escolhido não exista
				 else {
						JOptionPane.showMessageDialog(null,"O número do voo não existe.\nPor favor, escolha outro!");
					}
			  }  	
		}
	}
}