package com.example.exemplomodelos_de_comunicacao;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraHttpPOST extends AsyncTask<Void, Void, String> {

    TextView tv;
    String oper1,oper2, operacao;
    PrecisaCalcular pc;

    public CalculadoraHttpPOST(TextView tv, String oper1, String oper2, String operacao){
        this.tv=tv;
        this.oper1=oper1;
        this.oper2=oper2;
        this.operacao=operacao;
    }

    public CalculadoraHttpPOST(PrecisaCalcular pc, String oper1, String oper2, String operacao){
        this.oper1=oper1;
        this.oper2=oper2;
        this.pc=pc;
        this.operacao=operacao;

    }
    @Override
    protected String doInBackground(Void... voids) {
        String result="";
        try {

           //URL do serviço de calculadora.
           URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
           //obtem uma conexão HTTP a partir da URL indicada e prepara a requisicao
           // para o serviço definindo parametros.
           HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //efine o timeout para a leitura do recurso em milisegundos.
            conn.setReadTimeout(10000);
            //define o timeout, em milisegundos, para ser usada quando a conexao for aberta.
            conn.setConnectTimeout(15000);
            //define o metodo de reuqisicao HTTP.
            conn.setRequestMethod("POST");
            //define a conexao para a entrada de dados;
            conn.setDoInput(true);
            //define a conexao para o recebimento de dados;
            conn.setDoOutput(true) ;

            //ENVIO DOS PARAMETROS
            //objeto que representa a saida de fluxo de bytes para o envio do parametros.
            OutputStream os = conn.getOutputStream();
            //objeto usado para transcrever string em bytes.
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            //escreve a string passando como parametros os valores para a operacao e o tipo de
            // operacao solictada. (1=soma; 2=subtracao; 3=multiplicao; 4=divisao).
            writer.write("oper1="+oper1+"&oper2="+oper2+"&operacao="+operacao);
            //envia o fluxo de bytes criado.
            writer.flush();
            //encerra o processo de transcricao do string em bytes.
            writer.close();
            //fecha o canal para a saida do fluxo de bytes e libera recursos associados
            // a essa operacao.
            os.close();

            //recebimento do codigo de resposta HTTP do servico.
            int responseCode=conn.getResponseCode();
            //verifica se o codigo de resposta corresponde a uma requisicao bem sucedida.
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                //RECBIMENTO DOS PARAMETROS
                //objeto que faz a leitura de caracteres a partir de um fluxo de bytes.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                //objeto criado para armazenar a resposta transcrita em string.
                StringBuilder response = new StringBuilder();
                //objeto criado para a leitura das linhas da resposta.
                String responseLine = null;
                //processo de leitura das linhas da resposta.
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                //armazenamento da resposta da requisicao.
                result = response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //retorno da requisicao.
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

