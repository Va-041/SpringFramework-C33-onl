package lesson35.homework;

import org.flywaydb.core.Flyway;

public class Main {

    public static void main(String[] args) {

        Flyway load = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/lesson35", "postgres", "root")
                .load();

        load.migrate();
    }
}
