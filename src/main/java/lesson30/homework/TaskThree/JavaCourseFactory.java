package lesson30.homework.TaskThree;

public class JavaCourseFactory implements CourseFactory {

    @Override
    public Teacher createTeacher() {
        return new JavaTeacher();
    }

    @Override
    public Language createLanguage() {
        return new JavaLanguage();
    }

    @Override
    public LessonProgram createLessonProgram() {
        return new JavaLessonProgram();
    }
}
