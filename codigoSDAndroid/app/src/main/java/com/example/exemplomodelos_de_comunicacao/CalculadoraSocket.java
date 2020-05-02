package com.example.exemplomodelos_de_comunicacao;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraSocket extends AsyncTask<Void, Void, String> {

    TextView tv;
    String oper1,oper2, operacao;
    PrecisaCalcular pc;

    public CalculadoraSocket(TextView tv, String oper1, String oper2, String operacao){
        this.tv=tv;
        this.oper1=oper1;
        this.oper2=oper2;
        this.operacao=operacao;

    }
    public CalculadoraSocket(PrecisaCalcular pc, String oper1, String oper2, String operacao){
        this.tv=tv;
        this.oper1=oper1;
        this.oper2=oper2;
        this.pc=pc;
        this.operacao=operacao;

    }
    @Override
    protected String doInBackground(Void... voids) {
        String result="";
        try {

            //conexao com o servidor por meio do enderecp IP e porta.
            Socket clientSocket = new Socket("192.168.15.13", 9090);
            // objeto para a escrita e envio de dados.
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());

            //escrita dos dados para envio com os parametros informados, os dois valores para a
            //operacao e a operacao desejada (1=soma; 2=subtracao; 3=multiplicao; 4=divisao).
            socketSaidaServer.writeBytes(operacao+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            //enviando os dados.
            socketSaidaServer.flush();

            //recebendo a resposta.
            //objeto que faz a leitura de caracteres a partir de um fluxo de bytes.
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            //armazenamento da resposta da requisicao.
            result=messageFromServer.readLine();

            //fecha a conexao socket.
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //retorno da operacao desejada.
        return result;
        //Codigo
    }


    @Override
    protected void onPreExecute() {
        //Codigo
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
