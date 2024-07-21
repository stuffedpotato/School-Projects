import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HotelClient2
{
    public static void main(String[] args) throws IOException
    {
        final int PORT_NUM = 9999;
        Socket s = new Socket("localhost", PORT_NUM);

        DataInputStream instream = new DataInputStream(s.getInputStream());
        DataOutputStream outstream = new DataOutputStream(s.getOutputStream());

        String command = "USER Shashank";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        String response = instream.readUTF();
        System.out.println("Receiving: " + response);

        command = "RESERVE 8 15";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        response = instream.readUTF();
        System.out.println("Receiving: " + response);

        command = "AVAIL";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        response = instream.readUTF();
        System.out.println("Receiving: " + response);

        command = "CANCEL";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        response = instream.readUTF();
        System.out.println("Receiving: " + response);

        command = "RESERVE 20 25";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        response = instream.readUTF();
        System.out.println("Receiving: " + response);

        command = "AVAIL";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        response = instream.readUTF();
        System.out.println("Receiving: " + response);

        command = "QUIT";
        System.out.println("Sending: " + command);
        outstream.writeUTF(command);
        outstream.flush();
        response = instream.readUTF();
        System.out.println("Receiving: " + response);

        s.close();
    }
}
