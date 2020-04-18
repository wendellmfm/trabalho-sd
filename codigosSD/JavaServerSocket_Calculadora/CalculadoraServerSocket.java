import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	

	public static void main(String[] args) {
		
		//objeto que implementa um servidor socket que aguarda requisicoes da rede.
		ServerSocket welcomeSocket;
		//objeto para a escrita e envio de dados.
		DataOutputStream socketOutput; 
		//objeto para a leitura de dados.
	    DataInputStream socketInput;
	    //objeto que faz a leitura de caracteres a partir de um fluxo de bytes.
	    BufferedReader socketEntrada;
	    //objeto que implementa as quatro operacoes basicas de uma calculadora.
	    Calculadora calc = new Calculadora();
		try {
			//intancia um servidor socket na porta 9090.
			welcomeSocket = new ServerSocket(9090);
		  int i = 0; //numero de clientes
	  
	      System.out.println ("Servidor no ar");
	      while(true) { 
	  
	    	  //objeto que implementa um cliente socket.
	           Socket connectionSocket = welcomeSocket.accept(); 
	           //numero de clientes incrementado. 
	           i++;
	           //imprimi que uma nova conexao foi estabelecida.
	           System.out.println ("Nova conexão");
	           
	           //Interpretando dados do servidor.
	           socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	           //recebimentos dos parametros da operacao desejada.
	           //informacao da operacao desejada.
               String operacao = socketEntrada.readLine();
               //primeiro valor da operacao.
               String oper1 = socketEntrada.readLine();
               //segundo valor da operacao.
               String oper2 = socketEntrada.readLine();
               
               String result = "";
               //Chamando a calculadora.
               //definicao da operacao desejada (1=soma; 2=subtracao; 3=multiplicao; 4=divisao).
               switch (Integer.parseInt(operacao)) {
					case 1:
						//chamada do metodo de soma e armazenamento do resultado.
						result = String.valueOf(calc.soma(Double.parseDouble(oper1), Double.parseDouble(oper2)));
						break;
					case 2:
						//chamada do metodo de subtracao e armazenamento do resultado.
						result = String.valueOf(calc.subtracao(Double.parseDouble(oper1), Double.parseDouble(oper2)));
						break;
					case 3:
						//chamada do metodo de multiplicacao e armazenamento do resultado.
						result = String.valueOf(calc.multiplicacao(Double.parseDouble(oper1), Double.parseDouble(oper2)));
						break;
					case 4:
						//chamada do metodo de divisao e armazenamento do resultado.
						result = String.valueOf(calc.divisao(Double.parseDouble(oper1), Double.parseDouble(oper2)));
						break;

					default:
						break;
				}
               
               
               //Enviando dados para o cliente.
               //prepara a escrita da resposta.
               socketOutput= new DataOutputStream(connectionSocket.getOutputStream()); 
               //escreve a resposta em bytes.
	           socketOutput.writeBytes(result+ '\n');
	           //imprimi o resultado no console.
	           System.out.println (result);	
	           //envia os dados.
	           socketOutput.flush();
	           //fecha a conexao com o cliente.
	           socketOutput.close();

	                    
	      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	}

}
