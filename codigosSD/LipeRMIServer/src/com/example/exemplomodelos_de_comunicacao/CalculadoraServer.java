package com.example.exemplomodelos_de_comunicacao;
import java.io.IOException;

import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;

public class CalculadoraServer {

	public static void main(String[] args) {
		CallHandler callHandler = new CallHandler();
		
		ICalculadora calculadora = new Calculadora();

		try {
			callHandler.registerGlobal(ICalculadora.class, calculadora);
			
			Server server = new Server();
			int thePortIWantToBind = 4455;

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
