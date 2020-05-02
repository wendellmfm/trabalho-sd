import java.util.ArrayList;

public class Calculadora {
	
	private int qtdOperandos;
	
	private ArrayList<String> operandos;
	
	private ArrayList<String> operadores;
	
	public Calculadora() {
		
	}
	
	public int getQtdOperandos() {
		return qtdOperandos;
	}
	public void setQtdOperandos(int qtdOperandos) {
		this.qtdOperandos = qtdOperandos;
	}
	public ArrayList<String> getOperandos() {
		return operandos;
	}
	public void setOperandos(ArrayList<String> operandos) {
		this.operandos = operandos;
	}
	public ArrayList<String> getOperadores() {
		return operadores;
	}
	public void setOperadores(ArrayList<String> operadores) {
		this.operadores = operadores;
	}
	
}