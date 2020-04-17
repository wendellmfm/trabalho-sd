package com.example.exemplomodelos_de_comunicacao;

public class Calculadora  implements ICalculadora {

    private static final long serialVersionUID = 1L;

    private static int chamadas = 0;

    public double soma(double a, double b) {
        //System.out.println("Método soma chamado " + chamadas++);
        return a + b;
    }

    public double subtracao(double a, double b) {
        //System.out.println("Método soma chamado " + chamadas++);
        return a - b;
    }

    public double multiplicacao(double a, double b) {
        //System.out.println("Método soma chamado " + chamadas++);
        return a * b;
    }

    public double divisao(double a, double b) {
        //System.out.println("Método soma chamado " + chamadas++);
        return a / b;
    }

}
