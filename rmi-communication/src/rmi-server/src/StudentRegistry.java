import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface StudentRegistry extends Remote {
    /*
        public List<Student> receiveAllStudents() throws RemoteException;
    */
    public Double findScore(Student student) throws RemoteException;

    public void setScore(Student student, Double val) throws RemoteException;

    public void print(Map<Student, Double> studentScores) throws RemoteException;
}
