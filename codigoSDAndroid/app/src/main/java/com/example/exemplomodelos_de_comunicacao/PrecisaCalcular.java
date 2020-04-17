package com.example.exemplomodelos_de_comunicacao;

import android.widget.TextView;

public class PrecisaCalcular {
    TextView tv;

    public PrecisaCalcular(TextView tv){
        this.tv=tv;
    }

    public String calculoLocal(){
        Calculadora calc = new Calculadora();
        String result= calc.soma(20.0,20.0)+"";
        return result;
    }

    public void calculoRemoto(String operador1, String operador2, String operacao){
        CalculadoraSocket shs = new CalculadoraSocket(this, operador1, operador2, operacao);
        shs.execute();

    }
    public void calculoRemotoHTTP(String operador1, String operador2, String operacao){
        CalculadoraHttpPOST shs = new CalculadoraHttpPOST(this, operador1, operador2, operacao);
        shs.execute();

    }

    public void calculoRemotoLipeRMI(String operador1, String operador2, String operacao){
        CalculadoraLipeRMI shs = new CalculadoraLipeRMI(this, operador1, operador2, operacao);
        shs.execute();

    }

    public void result_calculoRemoto(String result){
        tv.setText(result);
    }

}
