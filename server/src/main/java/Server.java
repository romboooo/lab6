import Processors.CommandProcessor;
import data.src.communicationDataStructs.Request;
import data.src.communicationDataStructs.Response;
import data.src.exceptions.CommandDoesntExists;
import lombok.SneakyThrows;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends Thread {
    private static final Logger logger = Logger.getLogger("Server");
    static {
        logger.setLevel(Level.ALL);
    }
    private final CommandProcessor commandProcessor;

    private int receivePort = 55252;
    DatagramSocket socket;
    private boolean isRunning;
    private static byte[] INbuffer = new byte[8192];


    @SneakyThrows
    public Server(CommandProcessor commandProcessor){
        this.commandProcessor = commandProcessor;
        socket = new DatagramSocket(receivePort);
        setDaemon(true);
    }



    public void run() {
        isRunning = true;
        try  {

            while (isRunning) {
                DatagramPacket packet = new DatagramPacket(INbuffer, INbuffer.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(INbuffer);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Object obj = objectInputStream.readObject();

                if (!(obj instanceof Request)) {
                    System.out.println("Request type is incorrect");
                    continue;
                }
                logger.info("accepted request " + obj);
                Request request = (Request) obj;
                sendResponse(address, request, packet.getPort());

            }
        } catch (CommandDoesntExists | IOException | ClassNotFoundException e) {
            logger.severe(e.getLocalizedMessage());
            run();
        }
    }

    public void sendResponse(InetAddress address, Request request, int sendPort) throws IOException, CommandDoesntExists {
        String responseMEssage = processingRequest(request);

        Response response = new Response(responseMEssage, Response.status.OK);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.flush();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        socket.setReuseAddress(true);

        socket.connect(new InetSocketAddress(address, sendPort));

        objectOutputStream.flush();
        objectOutputStream.writeObject(response);
        byte[] OUTBuffer = byteArrayOutputStream.toByteArray();

        DatagramPacket OUTpacket = new DatagramPacket(OUTBuffer, OUTBuffer.length, address, sendPort);
        socket.send(OUTpacket);
        socket.disconnect();
        logger.info("send response " + response);

    }
    @SneakyThrows
    public String processingRequest(Request request) {
        String commandName = request.getCommandName().strip();
        if(request.getObjectArgs() != null){
            String[] objectArgs = getObjectArgsFromRequest(request);

            return commandProcessor.getCommands().get(commandName).ExecuteCommand(objectArgs);
        }
        return commandProcessor.getCommands().get(commandName).ExecuteCommand(request.getCommandArgs());
    }


    public static String[] getObjectArgsFromRequest(Request request){
        String[] answer = (String[]) request.getObjectArgs();
        return answer;
    }

    public static String getCommandLineFromRequest(Request request){
        String commandNames = request.getCommandName();
        String[] commandArgs = request.getCommandArgs();
        String firstCommandArg = commandArgs[0];
        String secondCommandArg = commandArgs[1];

        return commandNames + " " + firstCommandArg + " " + secondCommandArg;
    }
}
