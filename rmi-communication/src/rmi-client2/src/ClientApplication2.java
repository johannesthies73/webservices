import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientApplication2 {

    private final String HOST = "localhost";
    StudentRegistry server;

    public ClientApplication2() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST);
        server = (StudentRegistry) registry.lookup("test");
    }


    private void start() throws RemoteException {
        server.setScore(new Student("Student 5", 804), 100.0);
        System.out.println("Value successfully changed! :-)");
    }



    public static void main(String[] args) throws RemoteException, NotBoundException {
        new ClientApplication2().start();
    }
}
