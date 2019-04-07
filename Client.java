import com.google.gson.JsonSyntaxException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import com.google.gson.Gson;
import java.util.Scanner;


public class Client {
    public static void main(String[] args){
        try {
            InetAddress address = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            byte[] buffer = new byte[1000000000];
            byte[] bufferResponce = new byte[1000000000];
            int port = 6879;
            Scanner scanner = new Scanner(System.in);
            String line = new String();
            do{
                line = scanner.nextLine();
                if (line.split(" ")[0].equals("import")){
                    try {
                        String path = new String(line.split(" ")[1]);
                        File importedFile = new File(path);
                        if (!(importedFile.canRead())) throw new SecurityException("Данный файл не может быть прочитан.");
                        //date = new Date(importedFile.lastModified());
                        Scanner importedFileText = new Scanner(System.in);
                        importedFileText = new Scanner(importedFile);
                        String importedCollectionText = new String();
                        while (importedFileText.hasNextLine()) {
                            importedCollectionText = importedCollectionText.concat(importedFileText.nextLine());
                        }
                        importedFileText.close();
                        line = "import " + importedCollectionText;
                    } catch (FileNotFoundException mes) {
                        System.out.println(mes);
                    } catch (SecurityException mes) {
                        System.out.println(mes);
                    }
                    catch(JsonSyntaxException mes){
                        System.out.println("Неверный формат данных");
                    }
                }
                buffer = line.getBytes();
                DatagramPacket sending = new DatagramPacket(buffer,buffer.length,address,port);
                DatagramPacket response = new DatagramPacket(bufferResponce, bufferResponce.length);
                socket.send(sending);
                socket.setSoTimeout(3000);

                    try {
                        socket.receive(response);
                        String quote = new String(bufferResponce, 0, response.getLength());

                        System.out.println(quote);
                    }
                    catch (SocketTimeoutException e) {
                        // timeout exception.
                        System.out.println("Ответ от сервера не получен");
                    }


            }while(!line.equalsIgnoreCase("exit"));
        }catch (Exception o){

        }
    }
}
