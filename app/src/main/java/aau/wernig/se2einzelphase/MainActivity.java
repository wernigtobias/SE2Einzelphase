package aau.wernig.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendButton = findViewById(R.id.buttonSend);
        EditText inputMatNr = findViewById(R.id.textMatNr);
        TextView answerText = findViewById(R.id.txtAnswer);
        Button calculateButton = findViewById(R.id.buttonCalculate);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client client = new Client(inputMatNr.getText().toString());
                Thread t = new Thread(client);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                answerText.setText(client.outputText);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    int matNr = Integer.parseInt(inputMatNr.getText().toString());
                    if(alternierendeQuersummeGerade(matNr)){
                        answerText.setText("Die alternierende Quersumme ist gerade");
                    }else{
                        answerText.setText("Die alternierende Quersumme ist ungerade");
                    }
                } catch (NumberFormatException e) {
                    answerText.setText("Dies ist keine gueltige Matrikelnummer");
                }


            }
        });
    }

    public boolean alternierendeQuersummeGerade(int matNr){

        int quersumme = 0;
        int plusminusFaktor = 1;
        while(matNr > 0){
            int aktuelleZiffer = matNr%10;
            quersumme = quersumme + (aktuelleZiffer * plusminusFaktor);
            matNr = matNr/10;
            plusminusFaktor = -plusminusFaktor;
        }

        if(quersumme %2 == 0){
            return true;
        }else{
            return false;
        }
    }

}