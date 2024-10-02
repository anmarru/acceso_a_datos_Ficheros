package ejemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        try (Stream<String> lineas = Files.lines(Path.of("texto.txt"))) {
            //lineas.forEach(linea -> System.out.println(linea));
            lineas.filter(linea-> linea.contains("hecho ")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}