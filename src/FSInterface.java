/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author guill
 */
public interface FSInterface extends Remote {

    public void uploadFileToServer(byte[] mybyte, String serverpath, int length) throws RemoteException;

    public byte[] downloadFileFromServer(String servername) throws RemoteException;

    public File[] listFiles(String serverpath) throws RemoteException;

    public boolean createDirectory(String serverpath) throws RemoteException;

    public boolean removeDirectoryOrFile(String serverpath) throws RemoteException;
}
