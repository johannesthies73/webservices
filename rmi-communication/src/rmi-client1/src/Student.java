import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private String name;
    private int matrNr;

    public Student(String name, int matrNr) {
        this.name = name;
        this.matrNr = matrNr;
    }

    public int getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(int matrNr) {
        this.matrNr = matrNr;
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return matrNr == student.matrNr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrNr);
    }
}
