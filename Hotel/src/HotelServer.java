import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the Hotel Server. It has an infinite loop so it is always available and is multithreaded for multiple clients to connect at once.
 * The port number is 9999.
 */

public class HotelServer
{
    /**
     * This is the main method of the server program.
     * It lets a user connect to the server using a socket and adds users to thread for multi-use.
     * It uses the HotelService class as the main driver for each thread.
     * @param args is not used.
     * @throws IOException
     */

    public static void main(String[] args) throws IOException
    {
        Hotel myHotel = new Hotel();
        final int PORT_NUM = 9999;
        ServerSocket server = new ServerSocket(PORT_NUM);
        System.out.println("\nWaiting for client to connect...");

        try
        {
            while (true)
            {
                Socket socket = server.accept();
                System.out.println("\nClient has connected!");
                HotelService service = new HotelService(socket, myHotel);
                Thread thread = new Thread(service);
                thread.start();
            }
        }
        finally
        {
            server.close();
            System.out.println("\nServer closed");
        }
    }
}

