import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    PrintWriter writer;
    BufferedReader reader;
    boolean gameIsRunning = true;
    boolean firstGuess = true;
    Scanner scanner = new Scanner(System.in);
    String incomingMessage = "";

    public void start() throws IOException {

        try {
            Socket socket = new Socket("localhost", 8080);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            writer = new PrintWriter(output, true);
            reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("connection made");

            int myGuess;

            while (gameIsRunning) {

                if (firstGuess) {

                    System.out.println(reader.readLine());
                    Scanner scanner = new Scanner(System.in);
                    myGuess = scanner.nextInt();

                    writer.println(myGuess);
                    System.out.println("I guessed " + myGuess);
                    System.out.println("hej");
                    firstGuess = false;
                } else {


                    if (reader.ready()) {
                        //tar emot servens meddelande
                        String incomingMessage = reader.readLine();
                        System.out.println(incomingMessage);

                        //skickar iväg mitt
                        myGuess = scanner.nextInt();
                        writer.println(myGuess);
//                        System.out.println("I guessed "+myGuess);
//                        System.out.println("hej igen");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}