import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class client2 {

    public final static String IP = "127.0.0.1";
    public final static int PORT = 9090;

    public static void main(String[] args) throws IOException, InterruptedException {

        try {
            Socket socket = null;
            socket = new Socket(IP, PORT);

            System.out.println("Connected: " + socket);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            int[] mangSoNguyen = { 1, 2, 3, 4, 5, 6};

            DataOutputStream dos = new DataOutputStream(os);


            dos.writeUTF(Arrays.toString(mangSoNguyen));
            dos.flush();

            DataInputStream dis = new DataInputStream(is);
            String msg = dis.readUTF();
            System.out.println("server_msg: " + msg);

            dos.close();
            dis.close();
            socket.close();
    

        } catch (Exception e) {
        }

    }
}