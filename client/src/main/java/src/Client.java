package src;

import data.src.communicationDataStructs.Request;
import data.src.communicationDataStructs.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class Client{

    private InetAddress address;
    DatagramSocket socket;

    private byte[] buffer = new byte[65536];
    final int serverPort = 55252;
    public Client(){
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
        try {
            socket = new DatagramSocket((int) (Math.random()*1000+50000));
        } catch (SocketException e) {
            System.err.println(e.getMessage());
        }

    }

    public String sendRequest(Request request){
        try {
            socket.connect(InetAddress.getLocalHost(),serverPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.flush();
            objectOutputStream.writeObject(request);

            buffer = byteArrayOutputStream.toByteArray();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, socket.getInetAddress(), serverPort);
            socket.send(packet);
            Response message = getResponse();
            socket.disconnect();
            return message.getResponseMessage();
        } catch (PortUnreachableException e) {
            System.out.println("server is not responding");
            return "";
        } catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
            System.err.println(e.getCause());
            return "";
        }
    }

    public Response getResponse() throws IOException, ClassNotFoundException {
        byte[] OUTBuffer = new byte[8192];
        DatagramPacket packet = new DatagramPacket(OUTBuffer, OUTBuffer.length);
        socket.receive(packet);
//        InetAddress address = packet.getAddress();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(OUTBuffer);

        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object obj = objectInputStream.readObject();

        if (!(obj instanceof Response)) {
            Response invalidResponse = new Response("response type is invalid", Response.status.ERROR);
            return invalidResponse;
        }
        if(obj == null){
            Response nullResponse = new Response("response is null, maybe we have problems with server connection", Response.status.ERROR);
            return nullResponse;
        }
        return ((Response)obj);
    }
}
