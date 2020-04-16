import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;		
		try {
			reg = LocateRegistry.getRegistry(1099);
			calc = (ICalculadora) reg.lookup("calculadora");
			
			System.out.println(calc.soma(8,2));
			System.out.println(calc.subtracao(8,2));
			System.out.println(calc.multiplicacao(8,2));
			System.out.println(calc.divisao(8,2));
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}
