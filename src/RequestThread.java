import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;

public class RequestThread extends Thread {
    DatagramPacket request;
    String[] splitedLine;
    String result = new String();
    byte[] buffer = new byte[3000];
    public  RequestThread(String[] splitedLine, DatagramPacket request) {
        this.splitedLine = splitedLine;
        this.request = request;

    }
    public void run(){

        result = splitedLine[0];
        switch (result) {
            case ("insert"): {
                try {
                    //System.out.println(splitedLine[1]);
                    Server.collection = Orders.insert(Server.collection, splitedLine[1], request);

                } catch (Exception e) {
                    String sout = new String("Не было введено достаточно данных");
                    Orders.send(sout, request);

                }
            }
            break;
            case ("save"):
            case ("exit"): {
                if (!(Server.collection.isEmpty())) {
                    File defaultFile = new File(Orders.lastPath);
                    if (Orders.lastPath.length() != 0 & defaultFile.canWrite()) {
                        Orders.exit(Server.collection, Orders.lastPath,request);
                    } else {
                        String Path = new File("").getAbsolutePath();
                        String newPath = Path.concat("/" + (int) (Math.random() * 10000) + ".json");
                        File checkFile = new File(newPath);
                        while (checkFile.isFile() & checkFile.canRead()) {
                            newPath = Path.concat("/" + (int) (Math.random() * 10000) + ".json");
                            checkFile = new File(newPath);
                        }
                        String sout = new String(newPath);
                        Orders.lastPath = sout;
                        Orders.send(sout, request);

                        Orders.exit(Server.collection, newPath, request);
                    }
                }
            }
            break;
            case ("info"): {
                if (!(Server.collection.isEmpty())) Orders.info(Server.collection, request);
                else{
                    String sout = new String("Коллекция пуста");
                    Orders.send(sout, request);
                }
            }
            break;
            case ("remove_greater_key"): {
                if (!(Server.collection.isEmpty())) Orders.removeGreaterCollection(Server.collection, splitedLine[1], request);
                else{
                    String sout = new String("Коллекция пуста");
                    Orders.send(sout, request);
                }
            }
            break;
            case ("show"): {
                if (!(Server.collection.isEmpty())) Orders.show(Server.collection, request);
                else{
                    String sout = new String("Коллекция пуста");
                    Orders.send(sout, request);
                }
            }
            break;
            case ("load"): {
                if (Orders.lastPath.length() != 0) {
                    Server.collection = Orders.load(Orders.lastPath);
                }
                if (Orders.lastImportedCollection.length() != 0)
                {

                }
                    else {
                    String sout = new String("Нельзя перезагрузить коллекцию, так как изначально не был указан путь.");
                    Orders.send(sout, request);
                }
                break;
            }
            case ("import"): {
                Server.collection = Orders.importCollection(Server.collection, splitedLine[1], request);
            }
            break;

            case ("remove"): {
                try {

                    if (!(Server.collection.isEmpty()))
                        Orders.removeFromCollection(Server.collection, splitedLine[1], request);
                    else {
                        String sout = new String("Коллекция пуста");
                        Orders.send(sout, request);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    String sout = new String("Не указан элемент");
                    Orders.send(sout, request);
                }
            }
            break;


            default: String sout = new String("Такой команды не существует");
                Orders.send(sout, request);

        }
    }
}
