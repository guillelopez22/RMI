
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author crist
 */
public class rmiImplementation extends UnicastRemoteObject implements FSInterface, Serializable {

    javax.swing.JTextArea console;
    String binnacle = "";
    int idClient = 0;

    protected rmiImplementation(String s) throws RemoteException {
        File storageDir = new File(s);
        File cache = new File("./cache");
        if (!cache.exists()) {
            cache.mkdir();
        } else {
            System.out.println("Ya existe la cache");
        }
        if (!storageDir.exists()) {
            storageDir.mkdir();
        } else {
            System.out.println("Ya existe el directorio");
        }
    }

    public void uploadFileToServer(byte[] mydata, String serverpath, int length) throws RemoteException {

        try {
            File serverpathfile = new File(serverpath);
            FileOutputStream out = new FileOutputStream(serverpathfile);
            byte[] data = mydata;
            out.write(data);
            out.flush();
            out.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("Done writing data...");

    }

    public byte[] downloadFileFromServer(String serverpath) throws RemoteException {

        byte[] mydata;

        File serverpathfile = new File(serverpath);
        mydata = new byte[(int) serverpathfile.length()];
        FileInputStream in;
        try {
            in = new FileInputStream(serverpathfile);
            try {
                in.read(mydata, 0, mydata.length);
            } catch (IOException e) {

                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return mydata;

    }

    public File[] listFiles(String serverpath) throws RemoteException {
        File serverpathdir = new File(serverpath);
        return serverpathdir.listFiles();

    }

    public boolean createDirectory(String serverpath) throws RemoteException {
        File serverpathdir = new File(serverpath);

        return serverpathdir.mkdirs();

    }

    public boolean removeDirectoryOrFile(String serverpath) throws RemoteException {
        File serverpathdir = new File(serverpath);
        return serverpathdir.delete();

    }

    public void addBinnacle(String binnacle) throws RemoteException {
        this.binnacle += "\n" + binnacle;
        try {
            console.setText(this.binnacle);
        } catch (Exception e) {
        }
    }

    public void setServerConsole(javax.swing.JTextArea console) throws RemoteException {
        this.console = console;
    }

    public int addClient() throws RemoteException {
        this.idClient++;
        return this.idClient;
    }

}
