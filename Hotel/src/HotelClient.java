import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is the client program. It allows the user to interact with the server based on the protocol guidelines.
 */

public class HotelClient
{

    /**
     * This is the main method of the program. Client must first send their name using the USER ~name~ protocol.
     * Only then is the client allowed to move forward.
     * Client can choose to use this program for different users.
     * @param args is not used.
     * @throws IOException
     */

    public static void main(String[] args) throws IOException
    {
        String command, response;
        Scanner input = new Scanner(System.in);

        final int PORT_NUM = 9999;
        Socket s = new Socket("localhost", PORT_NUM);

        DataInputStream instream = new DataInputStream(s.getInputStream());
        DataOutputStream outstream = new DataOutputStream(s.getOutputStream());

        response = instream.readUTF();
        System.out.println(response);

        System.out.println("\nPlease enter your command:");
        command = input.nextLine();
        outstream.writeUTF(command);
        outstream.flush();

        response = instream.readUTF();
        System.out.println(response);

        if (command.indexOf(" ") != -1)
        {
            command = command.substring(0, command.indexOf(" "));
        }

        if (command.equals("USER"))
        {
            do
            {
                System.out.println("\nPlease enter your command:");
                command = input.nextLine();
                outstream.writeUTF(command);
                outstream.flush();

                if (command.indexOf(" ") != -1)
                {
                    command = command.substring(0, command.indexOf(" "));
                }

                response = instream.readUTF();
                System.out.println(response);
            }
            while (command.equals("USER") || command.equals("RESERVE") || command.equals("AVAIL") || command.equals("CANCEL"));
        }

        s.close();
    }
}
