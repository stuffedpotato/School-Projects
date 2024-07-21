import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This is the working class for each thread created in HotelServer.
 * User can choose to reserve, cancel, check availability or quit. A single client can work for multiple users.
 * This class implements Runnable to be able to use threads.
 */

public class HotelService implements Runnable
{
    private Hotel myHotel;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private String userName = null;

    /**
     * This is the main constructor of HotelService. It initializes the socket and the Hotel object.
     * @param socket is the socket by which client is connected.
     * @param myHotel is Hotel for which the reservation system is working.
     */

    public HotelService(Socket socket, Hotel myHotel)
    {
        this.myHotel = myHotel;
        this.socket = socket;
    }

    /**
     * This overridden run method connects the socket to the input and output streams of the server.
     * It closes the socket once user has finished using the program.
     */

    @Override
    public void run()
    {
        try
        {
            try
            {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
                
                System.out.println("\nSending greeting/menu to user");

                greetingAndMenu();
                doService();
            }
            finally
            {
                this.socket.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * This menu is immediately sent to the client once the client connects.
     * @throws IOException
     */

    public void greetingAndMenu() throws IOException
    {
        output.writeUTF("Hello User!\n" +
                "\nPlease use the following protocol to interact with this server: " +
                "\n1. To enter your name: USER yourName" +
                "\n2. To reserve: RESERVE startDate endDate" +
                "\n3. To cancel: CANCEL" +
                "\n4. To check availability: AVAIL" +
                "\n5. To quit: QUIT" +
                "\nPlease use command #1 first to identify yourself.");
        output.flush();
    }

    /**
     * This method checks if the user has entered their name first.
     * If user chooses to quit, it quits the connection by going back to the run method.
     * If the user has successfully identified themself, it moves on to execute commands.
     * @throws IOException
     */

    public void doService() throws IOException
    {
        String command;
        String incoming = input.readUTF();

        if (incoming.indexOf(" ") != -1)
        {
            command = incoming.substring(0, incoming.indexOf(" "));
        }
        else
        {
            command = incoming;
        }

        if (command.equals("USER"))
        {
            System.out.println("\nSending hello to user");

            userName = incoming.substring(incoming.indexOf(" ") + 1);
            output.writeUTF("Hello, " + userName);
            output.flush();
        }
        else
        {
            output.writeUTF("Failed to identify user: closing connection.");
            output.flush();
            return;
        }

        while(true)
        {
            System.out.println("\nBeginning of while");
            incoming = input.readUTF();

            if (incoming.indexOf(" ") != -1)
            {
                command = incoming.substring(0, incoming.indexOf(" "));
            }
            else
            {
                command = incoming;
            }

            if (command.equals("QUIT") || command.isEmpty())
            {
                System.out.println("\nUser requested QUIT");
                output.writeUTF("Closing connection.");
                output.flush();
                return;
            }

            executeCommand(incoming, command);
            System.out.println("\nEnd of while");
        }
    }

    /**
     * This method runs the program as per user commands - reserve, cancel or available slots are shown as per user request.
     * If user fails to follow protocol, the method ends and the connection is closed.
     * @param incoming is the command user has sent to the server.
     * @param command is the first word of the protocol that contains the actual command.
     * @throws IOException
     */

    public void executeCommand(String incoming, String command) throws IOException
    {
        if (command.equals("USER"))
        {
            System.out.println("\nSending hello to user");
            
            userName = incoming.substring(incoming.indexOf(" ") + 1);
            output.writeUTF("Hello, " + userName);
            output.flush();
        }

        else if (command.equals("RESERVE"))
        {
            int startDay = Integer.parseInt(incoming.substring(incoming.indexOf(" ") + 1, incoming.lastIndexOf(" ")));
            int endDay = Integer.parseInt(incoming.substring(incoming.lastIndexOf(" ") + 1));

            if (myHotel.requestReservation(userName, startDay, endDay))
            {
                System.out.println("\nSending reservation confirmation to user");
                
                output.writeUTF("Reservation made: " + userName + " from " + startDay + " to " + endDay);
                output.flush();
            }
            else
            {
                System.out.println("\nSending reservation failure message to user");
                
                output.writeUTF("Reservation unsuccessful: " + userName + " from " + startDay + " to " + endDay);
                output.flush();
            }
        }

        else if (command.equals("CANCEL"))
        {
            if(myHotel.cancelReservation(userName))
            {
                System.out.println("\nSending reservation cancellation confirmation to user");
                
                output.writeUTF("Reservation successfully cancelled for " + userName);
                output.flush();
            }
            else
            {
                System.out.println("\nSending reservation cancellation failure message to user");
                
                output.writeUTF("Reservation not cancelled for " + userName + ", no current reservation.");
                output.flush();
            }
        }

        else if (command.equals("AVAIL"))
        {
            System.out.println("\nSending availability to user");
            
            output.writeUTF(myHotel.toString());
            output.flush();
        }

        else
        {
            System.out.println("\nCommand invalid, closing connection");

            output.writeUTF("Invalid command: closing connection");
            output.flush();
        }
    }
}
