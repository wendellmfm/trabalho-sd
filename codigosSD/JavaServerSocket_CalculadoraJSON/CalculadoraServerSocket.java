import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculadoraServerSocket {

	

	public static void main(String[] args) {
		
		//objeto que implementa um servidor socket que aguarda requisicoes da rede.
		ServerSocket welcomeSocket;
		//objeto para a escrita e envio de dados.
		DataOutputStream socketOutput; 
	    //objeto que faz a leitura de caracteres a partir de um fluxo de bytes.
	    BufferedReader socketEntrada;
	    //objeto que implementa o calculo de expressoes matematicas.
	    Calculadora calculadora = new Calculadora();
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
               String json = socketEntrada.readLine();
               //objeto GSON usado para converter o objeto Calculadora em JSON.
               Gson gson = new Gson();
               //converter o JSON em objeto Calculadora.
               calculadora = gson.fromJson(json, Calculadora.class);
               
               //montagem da string da expressao a partir do objeto Calculadora.
               StringBuilder sbExpressao = new StringBuilder();
               for(i = 0; i < calculadora.getQtdOperandos(); i++) {
            	   sbExpressao.append(calculadora.getOperandos().get(i));
            	   if(i < calculadora.getOperadores().size()) {
            		   sbExpressao.append(calculadora.getOperadores().get(i));            		   
            	   }
               }
               //calculo da expressao matematica.
               Expression e = new ExpressionBuilder(sbExpressao.toString()).build();
               double resultado = e.evaluate();
               
               //atribuicao do resultado da expressao matematica.
               String result = String.valueOf(resultado);

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
