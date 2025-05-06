package lesson30.homework.TaskThree;

public class PythonCourseFactory implements CourseFactory {

    @Override
    public Teacher createTeacher() {
        return new PythonTeacher();
    }

    @Override
    public Language createLanguage() {
        return new PythonLanguage();
    }

    @Override
    public LessonProgram createLessonProgram() {
        return new PythonLessonProgram();
    }
}
