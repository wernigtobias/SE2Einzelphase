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

    }



}