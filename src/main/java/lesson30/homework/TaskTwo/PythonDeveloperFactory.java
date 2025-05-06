package lesson30.homework.TaskTwo;

public class PythonDeveloperFactory implements DeveloperFactory {

    @Override
    public Developer createDeveloper() {
        return new PythonDeveloper();
    }
}
