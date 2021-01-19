
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Arrays;

public class server2 {

    public final static int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
        serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server started: " + serverSocket);
        System.out.println("Waiting for a client ...");

        Socket socket = serverSocket.accept();
        System.out.println("Client accepted: " + socket);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        DataInputStream dis = new DataInputStream(is);
        String msg = dis.readUTF();
        System.out.println("client_msg" + msg);

        int[] arr = Arrays.stream(msg.substring(1, msg.length() - 1).split(", ")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr); 
        System.out.println("min: " + arr[0]);
        System.out.println("max: " + arr[arr.length - 1]);

        DataOutputStream dos = new DataOutputStream(os);

        dos.writeUTF("min, max: " + arr[0] + ", " + arr[arr.length - 1]);

        // os.write(min); // Send the results to client

        dos.close();
        dis.close();
        socket.close();

    }

}