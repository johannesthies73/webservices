import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StudentRegistryImpl extends UnicastRemoteObject implements StudentRegistry {

    Map<Student, Double> studentScores;

    public StudentRegistryImpl() throws RemoteException {
        //fill the hashmap with values
        studentScores = new HashMap<>();
        for (int j = 0; j < 10; j++) {
            Student student = new Student("Student " + j, new Random().nextInt(1000));
            Double score = new Random().nextDouble() * 100;

            studentScores.put(student, score);
        }
        print(studentScores);
        System.out.println("waiting for requests...");
    }


    @Override
    public Double findScore(Student student) throws RemoteException {
        for (Map.Entry<Student, Double> entry : studentScores.entrySet()) {
            if (entry.getKey().getMatrNr() == student.getMatrNr()) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void setScore(Student student, Double val) throws RemoteException {
        for (Map.Entry<Student, Double> entry : studentScores.entrySet()) {
            if (entry.getKey().getMatrNr() == student.getMatrNr()) {
                entry.setValue(val);
                print(studentScores);
            }
        }
    }

    @Override
    public void print(Map<Student, Double> studentScores) throws RemoteException {
        System.out.println("############################");
        studentScores.forEach((key, value) -> System.out.println(key.getName() + ", " + key.getMatrNr()
                + ": " + value.toString()));
    }
}
