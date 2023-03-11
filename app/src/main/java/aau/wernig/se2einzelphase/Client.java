package aau.wernig.se2einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable {

    private static final String server = "se2-isys.aau.at";
    private static final int port = 53212;

    String inputText;
    String outputText;

    public Client(String inputText) {
        this.inputText = inputText;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(server, port);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            dos.writeBytes(this.inputText + "\n");
            dos.flush();
            this.outputText = br.readLine();
            s.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
