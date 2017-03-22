

import java.io.*;
import java.net.Socket;

import java.nio.file.Files;



class ClientServiceThread implements Runnable {
    Socket clientSocket;
    File file = new File("C:\\Users\\Brej\\Pictures");

    ClientServiceThread(Socket s) {
        clientSocket = s;
    }

    public void run() {
        System.out.println("Accepted Client ");
        String filename;
        try (DataInputStream input = new DataInputStream(clientSocket.getInputStream());
//             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())
        ){

            filename = input.readUTF();
            saveFile(clientSocket, filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFile(Socket socket, String filename) throws IOException {

        System.out.println("przesyłąnie pliku: " + filename);
        try (BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream out = new BufferedOutputStream(
                     Files.newOutputStream(file.toPath().resolve(filename)))) {

            byte[] buffer = new byte[4096];
            int readBytes;


            while ((readBytes = in.read(buffer)) != -1) {
                out.write(buffer, 0, readBytes);



            }
        }
        System.out.println("Plik przesłany\n");
    }

}