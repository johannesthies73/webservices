import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApplication {

    public static void main(String[] args) throws RemoteException {
        new ServerApplication().start();
    }

    public void start() throws RemoteException {
        StudentRegistry server = new StudentRegistryImpl();
        int rmiRegistryPort = Registry.REGISTRY_PORT;
        Registry registry = LocateRegistry.createRegistry(rmiRegistryPort);
        registry.rebind("test", server);
    }
}
