import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class BroadcastServidor {

    final static int PORT = 4445;

    public static void main(String[] args) {
        // Asumo que con 512 está bien
        byte[] buffer = new byte[512];

        try {
            while (true) {

                DatagramSocket socket = new DatagramSocket(PORT);
                socket.setBroadcast(true); 
                System.out.println("socket disponible");
 
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                FileWriter registros = new FileWriter("registrosCliente.txt", true);
                registros.write(msg + System.lineSeparator());
                registros.close();
                System.out.println("Received broadcast message: " + msg);
                //resetear el buffer
                java.util.Arrays.fill(buffer, (byte) 0);
                socket.close();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
