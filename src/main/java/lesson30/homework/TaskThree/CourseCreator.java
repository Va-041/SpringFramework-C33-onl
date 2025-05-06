package lesson30.homework.TaskThree;

public class CourseCreator {
    public static void createCourse(String language) {
        CourseFactory factory = getFactory(language);

        if (factory == null) {
            System.out.println("\nИзвините, курс для языка '" + language + "' не доступен.");
            System.out.println("Поддерживаемые языки: java, python");
            return;
        }

        System.out.println("\nСоздание курса для " + language + "...");

        Teacher teacher = factory.createTeacher();
        Language lang = factory.createLanguage();
        LessonProgram program = factory.createLessonProgram();

        lang.getName();
        teacher.teach();
        program.showProgram();
    }

    private static CourseFactory getFactory(String language) {
        switch (language.toLowerCase()) {
            case "java":
                return new JavaCourseFactory();
            case "python":
                return new PythonCourseFactory();
            default:
                return null;
        }
    }
}