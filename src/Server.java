import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Brej on 21.03.2017.
 */
public class Server {

    private static final int PORT = 6666;

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            System.out.print("Server start \n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientServiceThread(clientSocket));
            }
        }
    }
}
