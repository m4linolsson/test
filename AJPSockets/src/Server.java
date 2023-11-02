import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server {

    BufferedReader reader;
    PrintWriter writer;
    Random random = new Random();
    int randomnumber = random.nextInt(5) + 1;
    boolean gameIsRunning = true;


    public void start() throws IOException {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Waiting for client");
            Socket socket = serverSocket.accept();
            System.out.println("client connected");
            System.out.println("Secret number is " + randomnumber);


            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);

            writer.println("make a guess between 1-10");
            System.out.println("client typing");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (gameIsRunning) {

            if (reader.ready()) {
                String incomingMessage = reader.readLine();
                System.out.println("you guessed: " + incomingMessage);

                String outgoingMessage = checkAnswerAndGenerateReply(incomingMessage);

                writer.println(outgoingMessage);
                System.out.println(outgoingMessage);
            }
        }

    }


    public String checkAnswerAndGenerateReply(String incomingmessage) {
        int guess = Integer.parseInt(incomingmessage);
        if (guess == randomnumber) {
            gameIsRunning = false;
            return "Correct game over";
        } else {
            return "Wrong, guess again";
        }


    }


}
