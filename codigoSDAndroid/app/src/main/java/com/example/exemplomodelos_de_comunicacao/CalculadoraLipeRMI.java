package com.example.exemplomodelos_de_comunicacao;

import android.os.AsyncTask;
import android.widget.TextView;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;

public class CalculadoraLipeRMI extends AsyncTask<Void, Void, String> {

    TextView tv;
    String oper1,oper2, operacao;
    PrecisaCalcular pc;

    public CalculadoraLipeRMI(TextView tv, String oper1, String oper2, String operacao){
        this.tv=tv;
        this.oper1=oper1;
        this.oper2=oper2;
        this.operacao=operacao;

    }

    public CalculadoraLipeRMI(PrecisaCalcular pc, String oper1, String oper2, String operacao){
        this.oper1=oper1;
        this.oper2=oper2;
        this.pc=pc;
        this.operacao=operacao;

    }

    @Override
    protected String doInBackground(Void... voids) {
        //objeto responsavel por gerenciar todas as instancias de objetos locais exportadas
        // e remotas.
        CallHandler callHandler = new CallHandler();
        //endereco ip do host remoto.
        String remoteHost = "192.168.15.11";
        //porta usada para a conexao.
        int portWasBinded = 4455;

        //objeto cliente LipeRMI usado na requisicao.
        Client client;
        double result = 0;
        try {
            //inicializacao do cliente com os parametros definidos previamente.
            client = new Client(remoteHost, portWasBinded, callHandler);

            //definicao da interface do objeto remoto.
            ICalculadora remoteObject = (ICalculadora) client.getGlobal(ICalculadora.class);

            //escolha da operacao desejada (1=soma; 2=subtracao; 3=multiplicao; 4=divisao).
            switch (Integer.parseInt(operacao)) {
                case 1:
                    //chamada do metodo remoto de soma.
                    result = remoteObject.soma(Double.parseDouble(oper1), Double.parseDouble(oper2));
                    break;
                case 2:
                    //chamada do metodo remoto de subtracao.
                    result = remoteObject.subtracao(Double.parseDouble(oper1), Double.parseDouble(oper2));
                    break;
                case 3:
                    //chamada do metodo remoto de multiplicacao.
                    result = remoteObject.multiplicacao(Double.parseDouble(oper1), Double.parseDouble(oper2));
                    break;
                case 4:
                    //chamada do metodo remoto de divisao.
                    result = remoteObject.divisao(Double.parseDouble(oper1), Double.parseDouble(oper2));
                    break;

                default:
                    break;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //retorno da operacao desejada.
        return Double.toString(result);
    }

    @Override
    protected void onPostExecute(String result) {
        //Codigo
        if(this.tv!=null) {
            this.tv.setText(result);
        }else {
            this.pc.result_calculoRemoto(result);
        }
    }
}
