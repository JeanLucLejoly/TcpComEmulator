package testtcpclient;

/**
 *
 * @author lejolyj
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestTCPClient {

    private ServerSocket serverSocket;
    private Socket client;
    private String OutText;
    private int dotcount= 0; 
    private int SocketIsStarted = 0;

// code called if serving as a TCP Client
    public TestTCPClient(String sName, int port) {
        this.OutText = "";
        try {
            OutText = "";
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = "\n" + timeStamp + " --> Connecting to " + sName
                    + " on port " + port;
            client = new Socket(sName, port);
            OutText = OutText + "\n" + timeStamp + " --> Just connected to "
                    + client.getRemoteSocketAddress();
            client.setSoTimeout(1000);
            client.setOOBInline(true);
            client.setKeepAlive(true);
            client.setTcpNoDelay(true);
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = "\n" + timeStamp + " --> connection failed due to: " + e.getMessage();

        }
    }
// code called if serving as a TCP SERVER    
   public TestTCPClient(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(300000);
      this.OutText = "";
        try {
            OutText = "";
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = "\n" + timeStamp + " --> Waiting for client on port " +
            serverSocket.getLocalPort() + "...";
// only establish connection if not already open
            if (SocketIsStarted == 0) {
                client = serverSocket.accept();
                timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
                OutText = "\n" + timeStamp + " --> Just connected to " + client.getRemoteSocketAddress();
                SocketIsStarted = 1;
            }
            client.setSoTimeout(1000);
            client.setOOBInline(true);
            client.setKeepAlive(true);
            client.setTcpNoDelay(true);
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = "\n" + timeStamp + " --> connection failed due to: " + e.getMessage();

        }
    }

    TestTCPClient() {
     //   this.OutText = "";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getStatus() {
        OutText = "";
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
        OutText = OutText + "\n" + timeStamp + " --> connected to "
                + client.getRemoteSocketAddress();
    }

    public String getOutput() {
        String ToSendBack;
        ToSendBack = OutText;
        OutText = "";
        return ToSendBack;

    }

    private String TransformOutputString(String Data2Send) {
        Data2Send = Data2Send.replace("<NUL>", "\u0000");
        Data2Send = Data2Send.replace("<SOH>", "\u0001");
        Data2Send = Data2Send.replace("<STX>", "\u0002");
        Data2Send = Data2Send.replace("<ETX>", "\u0003");
        Data2Send = Data2Send.replace("<EOT>", "\u0004");
        Data2Send = Data2Send.replace("<ENQ>", "\u0005");
        Data2Send = Data2Send.replace("<ACK>", "\u0006");
        Data2Send = Data2Send.replace("<BEL>", "\u0007");
        Data2Send = Data2Send.replace("<BS>", "\u0008");
        Data2Send = Data2Send.replace("<TAB>", "\u0009");
        Data2Send = Data2Send.replace("<LF>", "\n");
        Data2Send = Data2Send.replace("<VT>", "\u000B");
        Data2Send = Data2Send.replace("<FF>", "\u000C");
        Data2Send = Data2Send.replace("<CR>", "\r");
        Data2Send = Data2Send.replace("<SO>", "\u000E");
        Data2Send = Data2Send.replace("<SI>", "\u000F");
        Data2Send = Data2Send.replace("<DLE>", "\u0010");
        Data2Send = Data2Send.replace("<DC1>", "\u0011");
        Data2Send = Data2Send.replace("<DC2>", "\u0012");
        Data2Send = Data2Send.replace("<DC3>", "\u0013");
        Data2Send = Data2Send.replace("<DC4>", "\u0014");
        Data2Send = Data2Send.replace("<NAK>", "\u0015");
        Data2Send = Data2Send.replace("<SYN>", "\u0016");
        Data2Send = Data2Send.replace("<ETB>", "\u0017");
        Data2Send = Data2Send.replace("<CAN>", "\u0018");
        Data2Send = Data2Send.replace("<EM>", "\u0019");
        Data2Send = Data2Send.replace("<SUB>", "\u001A");
        Data2Send = Data2Send.replace("<ESC>", "\u001B");
        Data2Send = Data2Send.replace("<FS>", "\u001C");
        Data2Send = Data2Send.replace("<GS>", "\u001D");
        Data2Send = Data2Send.replace("<RS>", "\u001E");
        Data2Send = Data2Send.replace("<US>", "\u001F");
        return Data2Send;
    }

    private String TransformInputString(String RecievedData) {
        RecievedData = RecievedData.replace("\u0000", "<NUL>");
        RecievedData = RecievedData.replace("\u0001", "SOH");
        RecievedData = RecievedData.replace("\u0002", "<STX>");
        RecievedData = RecievedData.replace("\u0003", "<ETX>");
        RecievedData = RecievedData.replace("\u0004", "<EOT>");
        RecievedData = RecievedData.replace("\u0005", "<ENQ>");
        RecievedData = RecievedData.replace("\u0006", "<ACK>");
        RecievedData = RecievedData.replace("\u0007", "<BEL>");
        RecievedData = RecievedData.replace("\u0008", "<BS>");
        RecievedData = RecievedData.replace("\u0009", "<TAB>");
        RecievedData = RecievedData.replace("\n", "<LF>");
        RecievedData = RecievedData.replace("\u000B", "<VT>");
        RecievedData = RecievedData.replace("\u000C", "<FF>");
        RecievedData = RecievedData.replace("\r", "<CR>");
        RecievedData = RecievedData.replace("\u000E", "<SO>");
        RecievedData = RecievedData.replace("\u000F", "<SI>");
        RecievedData = RecievedData.replace("\u0010", "<DLE>");
        RecievedData = RecievedData.replace("\u0011", "<DC1>");
        RecievedData = RecievedData.replace("\u0012", "<DC2>");
        RecievedData = RecievedData.replace("\u0013", "<DC3>");
        RecievedData = RecievedData.replace("\u0014", "<DC4>");
        RecievedData = RecievedData.replace("\u0015", "<NAK>");
        RecievedData = RecievedData.replace("\u0016", "<SYN>");
        RecievedData = RecievedData.replace("\u0017", "<ETB>");
        RecievedData = RecievedData.replace("\u0018", "<CAN>");
        RecievedData = RecievedData.replace("\u0019", "<EM>");
        RecievedData = RecievedData.replace("\u001A", "<SUB>");
        RecievedData = RecievedData.replace("\u001B", "<ESC>");
        RecievedData = RecievedData.replace("\u001C", "<FS>");
        RecievedData = RecievedData.replace("\u001D", "<GS>");
        RecievedData = RecievedData.replace("\u001E", "<RS>");
        RecievedData = RecievedData.replace("\u001F", "<US>");
        return RecievedData;
    }

    public static byte[] stringToBytesASCII(String str) {
        char[] buffer = str.toCharArray();
        byte[] b = new byte[buffer.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) buffer[i];
        }
        return b;
    }

    public void SendData(String DataToSend) {
        OutText = "";
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
        OutText = OutText + "\n" + timeStamp + " --> " + DataToSend;

        DataToSend = TransformOutputString(DataToSend);

        try {
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeBytes(DataToSend);

        } catch (IOException e) {
            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- exceptionCase: " + e.getMessage();
        }
    }

    public void readSocketShort(){
        //set socket Timeout to be 5 seconds
        int currentTimeout = 10;
        String messageString = "";
        
        try {
            currentTimeout = client.getSoTimeout();
            client.setSoTimeout(1000);
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- Cannot set TimeOut because " + e.getMessage();
        }
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            while (in.available() > 0) {
                int counter = 0;
                byte inByte = in.readByte();
                messageString += Character.toString((char) Byte.toUnsignedInt(inByte));
            }
            String theString = TransformInputString(messageString);
            if (dotcount>=21){ dotcount=0;}
            dotcount++;
            
            if (theString.isEmpty()&&dotcount<20){
               OutText = ".";
            }else{
                dotcount=0;
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
                OutText = OutText + "\n" + timeStamp + " <-- " + theString;
            }
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- exceptionCase: " + e.getMessage();
        }

        try {
            client.setSoTimeout(currentTimeout);
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- Cannot set TimeOut because " + e.getMessage();
        }
    }

            
            
    public void readSocket() {
        //set socket Timeout to be 5 seconds
        int currentTimeout = 10;
        String messageString = "";
        dotcount=0;
        try {
            currentTimeout = client.getSoTimeout();
            client.setSoTimeout(1000);
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- Cannot set TimeOut because " + e.getMessage();
        }
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            while (in.available() > 0) {
                int counter = 0;
                byte inByte = in.readByte();
                messageString += Character.toString((char) Byte.toUnsignedInt(inByte));
            }
            String theString = TransformInputString(messageString);
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " <-- " + theString;

        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- exceptionCase: " + e.getMessage();
        }

        try {
            client.setSoTimeout(currentTimeout);
        } catch (IOException e) {
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            OutText = OutText + "\n" + timeStamp + " --- Cannot set TimeOut because " + e.getMessage();
        }
    }

    public void closeSocket() {
        try {
            OutText = "";
            OutText = OutText + "\nClosing now Socket :"
                    + client.getRemoteSocketAddress();
            client.close();
        } catch (IOException e) {
        }
    }
}

