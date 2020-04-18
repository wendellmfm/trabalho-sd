package com.example.exemplomodelos_de_comunicacao;
import java.io.IOException;

import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;

public class CalculadoraServer {

	public static void main(String[] args) {
		//objeto responsavel por gerenciar todas as instancias de objetos locais exportadas
        // e remotas.
		CallHandler callHandler = new CallHandler();
		
		//definicao da interface do objeto exposto.
		ICalculadora calculadora = new Calculadora();

		try {
			//exposicao do objeto.
			callHandler.registerGlobal(ICalculadora.class, calculadora);
			
			//objeto que implementa um servidor LipeRMI.
			Server server = new Server();
			//porta usada para acesso ao objeto exposto.
			int thePortIWantToBind = 4455;

			//ligacao do servidor com a porta usada para a comunicacao e com o objeto
			//responsavel pelas instancias de objetos expostos.
			server.bind(thePortIWantToBind, callHandler);
		} catch (LipeRMIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
