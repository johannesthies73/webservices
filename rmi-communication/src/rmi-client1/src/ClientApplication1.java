import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientApplication1 {

    private final String HOST = "localhost";
    StudentRegistry server;

    public ClientApplication1() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST);
        server = (StudentRegistry) registry.lookup("test");
    }


    private void start() throws RemoteException {
        Double score = server.findScore(new Student("Student 5", 804));
        System.out.println(score);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        new ClientApplication1().start();
    }
}
