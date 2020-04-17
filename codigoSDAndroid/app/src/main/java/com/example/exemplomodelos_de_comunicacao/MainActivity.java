package com.example.exemplomodelos_de_comunicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String OPERACAO_SOMA = "1";
    public static final String OPERACAO_SUBTRACAO = "2";
    public static final String OPERACAO_MULTIPLICACAO = "3";
    public static final String OPERACAO_DIVISAO = "4";

    public static final String TIPO_SOCKET = "1";
    public static final String TIPO_HTTP = "2";
    public static final String TIPO_RMI = "3";

    private String tipoComunicacao = TIPO_HTTP;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText operador1 = findViewById(R.id.editText1);
        final EditText operador2 = findViewById(R.id.editText2);

        Button btSomar = findViewById(R.id.buttonSomar);
        Button btSubtrair = findViewById(R.id.buttonSubtrair);
        Button btMultiplicar = findViewById(R.id.buttonMultiplicar);
        Button btDividir = findViewById(R.id.buttonDividir);

        tv = findViewById(R.id.textView);

        btSomar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               PrecisaCalcular shc = new PrecisaCalcular(tv);

               switch (tipoComunicacao){
                   case TIPO_SOCKET:
                       shc.calculoRemoto(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_SOMA);
                       break;
                   case TIPO_HTTP:
                       shc.calculoRemotoHTTP(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_SOMA);
                       break;
                   case TIPO_RMI:
                       shc.calculoRemotoLipeRMI(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_SOMA);
                       break;
               }
            }
        });

        btSubtrair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PrecisaCalcular shc = new PrecisaCalcular(tv);
                switch (tipoComunicacao){
                    case TIPO_SOCKET:
                        shc.calculoRemoto(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_SUBTRACAO);
                        break;
                    case TIPO_HTTP:
                        shc.calculoRemotoHTTP(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_SUBTRACAO);
                        break;
                    case TIPO_RMI:
                        shc.calculoRemotoLipeRMI(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_SUBTRACAO);
                        break;
                }
            }
        });

        btMultiplicar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PrecisaCalcular shc = new PrecisaCalcular(tv);
                switch (tipoComunicacao){
                    case TIPO_SOCKET:
                        shc.calculoRemoto(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_MULTIPLICACAO);
                        break;
                    case TIPO_HTTP:
                        shc.calculoRemotoHTTP(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_MULTIPLICACAO);
                        break;
                    case TIPO_RMI:
                        shc.calculoRemotoLipeRMI(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_MULTIPLICACAO);
                        break;
                }
            }
        });

        btDividir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PrecisaCalcular shc = new PrecisaCalcular(tv);
                switch (tipoComunicacao){
                    case TIPO_SOCKET:
                        shc.calculoRemoto(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_DIVISAO);
                        break;
                    case TIPO_HTTP:
                        shc.calculoRemotoHTTP(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_DIVISAO);
                        break;
                    case TIPO_RMI:
                        shc.calculoRemotoLipeRMI(operador1.getText().toString(), operador2.getText().toString(), OPERACAO_DIVISAO);
                        break;
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtonSocket:
                if (checked)
                    tipoComunicacao = TIPO_SOCKET;
                    break;
            case R.id.radioButtonHTTP:
                if (checked)
                    tipoComunicacao = TIPO_HTTP;
                    break;
            case R.id.radioButtonLipeRMI:
                if (checked)
                    tipoComunicacao = TIPO_RMI;
                    break;
        }
    }

}
