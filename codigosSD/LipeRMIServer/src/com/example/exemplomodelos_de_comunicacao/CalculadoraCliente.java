package com.example.exemplomodelos_de_comunicacao;
import java.io.IOException;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;


public class CalculadoraCliente {
	
	public static void main(String[] args) {
		CallHandler callHandler = new CallHandler();
		String remoteHost = "localhost";
		int portWasBinded = 4455;

		Client client;
		try {
			client = new Client(remoteHost, portWasBinded, callHandler);
			
			ICalculadora remoteObject = (ICalculadora) client.getGlobal(ICalculadora.class);
			System.out.println(remoteObject.soma(8,2));
			System.out.println(remoteObject.subtracao(8,2));
			System.out.println(remoteObject.multiplicacao(8,2));
			System.out.println(remoteObject.divisao(8,2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}		

}
