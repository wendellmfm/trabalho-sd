import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		Calculadora calculadora = new Calculadora();
		
		//definicao da quantidade de operandos.
		calculadora.setQtdOperandos(5);
		
		//definicao do array list de operandos.
		ArrayList<String> operandos = new ArrayList<String>();
		operandos.add("3");
		operandos.add("4");
		operandos.add("8");
		operandos.add("5");
		operandos.add("9");
		calculadora.setOperandos(operandos);
		
		//definicao do array list de operadores.
		ArrayList<String> operadores = new ArrayList<String>();
		operadores.add("-");
		operadores.add("/");
		operadores.add("+");
		operadores.add("*");
		calculadora.setOperadores(operadores);
		
		//mapa para armazenamento do objeto.
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("Calculo", calculadora);
        
        //objeto para a criacao do YAML.
        Yaml yaml = new Yaml();
        //criacao da string no formato YAML.
        String yamlString = yaml.dump(data);
        
		String result="";
		
        try {

        	//conexão com o Servidor
            Socket clientSocket = new Socket("192.168.15.15", 9090);
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //enviando os dados
            socketSaidaServer.writeBytes(yamlString + "\n");
            socketSaidaServer.flush();

            //recebendo a resposta
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
