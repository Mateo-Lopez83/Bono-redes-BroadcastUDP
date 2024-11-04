import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClienteBroadcast {
    //no tiene socket ya que eso implicaría mandar a una ip + puerto específicos 
    //lo cual ñao ñao
    private static DatagramSocket socket = null;
    public static void main(String[] args) throws IOException {
      InetAddress localHost = InetAddress.getLocalHost();
      String hostname = localHost.getHostName();
      String ipAddress = localHost.getHostAddress();
      while (true){
        try {
          String mensaje ="hostname: "+ hostname +"con direccion: "+ ipAddress;
        generar_broadcast(mensaje, InetAddress.getByName("255.255.255.255"));
        System.out.println("Datagrama enviado");
        Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
        
    }
    public static void generar_broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        //Importantee
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();
        //Se envía al puerto UDP 4445, pero cualquiera por encima de 1024 debería servir
        DatagramPacket packet  = new DatagramPacket(buffer, buffer.length, address,4445);
        socket.send(packet);
        socket.close();
    }
}