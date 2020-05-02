import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		Calculadora calculadora = new Calculadora();
		
		//Definicao da quantidade de operandos.
		calculadora.setQtdOperandos(5);
		
		//Definicao do array list de operandos.
		ArrayList<String> operandos = new ArrayList<String>();
		operandos.add("3");
		operandos.add("4");
		operandos.add("8");
		operandos.add("5");
		operandos.add("9");
		calculadora.setOperandos(operandos);
		
		//Definicao do array list de operadores.
		ArrayList<String> operadores = new ArrayList<String>();
		operadores.add("-");
		operadores.add("/");
		operadores.add("+");
		operadores.add("*");
		calculadora.setOperadores(operadores);
		
		//objeto GSON usado para converter o objeto Calculadora em JSON.
		Gson gson = new Gson();
		//converter do objeto Calculadora em JSON.
		String json = gson.toJson(calculadora); 
		
		String result="";
		
        try {

        	//Conexão com o Servidor
            Socket clientSocket = new Socket("192.168.15.15", 9090);
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Enviando os dados
            socketSaidaServer.writeBytes(json + "\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            result = messageFromServer.readLine();
            
            System.out.println("resultado = "+ result);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
