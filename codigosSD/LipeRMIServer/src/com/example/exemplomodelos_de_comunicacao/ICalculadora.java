package com.example.exemplomodelos_de_comunicacao;
import java.io.Serializable;

public interface ICalculadora extends Serializable{

	public double soma(double a, double b);
	
	public double subtracao(double a, double b);
	
	public double multiplicacao(double a, double b);
	
	public double divisao(double a, double b);
}
