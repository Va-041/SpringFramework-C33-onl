package lesson30.homework.TaskFour;

import java.util.List;

public class Developer {
    private final String name;
    private final String language;
    private final int experienceYears;
    private final List<String> skills;
    private final boolean hasHigherEducation;
    private final String specialization;

    private Developer(Builder builder) {
        this.name = builder.name;
        this.language = builder.language;
        this.experienceYears = builder.experienceYears;
        this.skills = builder.skills;
        this.hasHigherEducation = builder.hasHigherEducation;
        this.specialization = builder.specialization;
    }

    @Override
    public String toString() {
        return String.format(
                "\nDeveloper Profile:\n" +
                        "-----------------\n" +
                        "Имя: %s\n" +
                        "\tЯзык программирования: %s\n" +
                        "\tОпыт работы: %d \n" +
                        "\tНавыки: %s\n" +
                        "\tОбразование: %s\n" +
                        "\tСпециальность: %s",
                name, language, experienceYears,
                (skills != null && !skills.isEmpty()) ? String.join(", ", skills) : "В списке нет навыков",
                hasHigherEducation ? "Высшее образование" : "Без высшего образования",
                specialization
        );
    }


    public static class Builder {

        private String name;
        private String language;
        private int experienceYears = 0;
        private List<String> skills;
        private boolean hasHigherEducation = false;
        private String specialization = "Инженер-программист";

        public Builder(String name, String language) {
            this.name = name;
            this.language = language;
        }

        public Builder experienceYears(int years) {
            this.experienceYears = years;
            return this;
        }

        public Builder skills(List<String> skills) {
            this.skills = skills;
            return this;
        }

        public Builder hasHigherEducation(boolean has) {
            this.hasHigherEducation = has;
            return this;
        }

        public Builder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Developer build() {
            return new Developer(this);
        }
    }
}
