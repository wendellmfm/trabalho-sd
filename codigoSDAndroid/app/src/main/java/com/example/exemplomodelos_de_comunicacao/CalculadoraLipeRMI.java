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
        CallHandler callHandler = new CallHandler();
        String remoteHost = "192.168.15.11";
        int portWasBinded = 4455;

        Client client;
        double result = 0;
        try {
            client = new Client(remoteHost, portWasBinded, callHandler);

            ICalculadora remoteObject = (ICalculadora) client.getGlobal(ICalculadora.class);
            result = remoteObject.soma(8,2);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
