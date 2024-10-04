package ejemplo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

public class Gestor_Fichero {

    public static void main(String[] args) throws IOException {

        // exportarClientesCSV();
        leerClienteCSV();

        try (
                Reader reader = Files.newBufferedReader(Path.of("prueba.csv"));
                CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withSkipLines(1) // procesar a partir de segunda línea
                        .withCSVParser(new CSVParserBuilder().build())
                        .build()) {
            String[] linea;
            try {
                while ((linea = csvReader.readNext()) != null) {
                    for (String columna : linea) {
                        System.out.println("Columna: " + columna);
                    }
                }
            } catch (CsvValidationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // EJERCICIO 1
    public static List<Cliente> leer_Cliente() {

        List<Cliente> clientes = new ArrayList<>();

        try (Stream<String> lineas = Files.lines(Path.of("clientes.txt"))) {
            clientes = lineas.map(linea -> {
                String[] campos = linea.split(",");
                if (campos.length == 5) {

                    return new Cliente(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4]);

                } else {
                    return null;
                }

            }).toList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public static List<Cliente> comprobar_lista(List<Cliente> clientes) {
        List<Cliente> clientesFiltrados = clientes.stream()
                .filter(cliente -> cliente.getTelefono().startsWith("+34"))
                .filter(cliente -> !cliente.getEmail().matches(".*[A-Z].*"))
                // .filter(cliente->
                // cliente.getEmail().toLowerCase().equals(cliente.getEmail())) OTRA FORMA DE
                // SABER QUE SEA MINUSCULA
                .toList();
        return clientesFiltrados;

    }

    public static Cliente comprobar_linea(String linea) {
        String[] campos = linea.split(",");
        if (linea.isEmpty()) {
            throw new IllegalArgumentException("NO se pueden lineas vacias ");
        }
        if (linea.length() != 5) {
            throw new IllegalArgumentException(" Formato no valido : debe de haber 5 campos");
        }
        if (Arrays.stream(campos).anyMatch(campo -> campo.isEmpty())) {
            throw new IllegalArgumentException("FORMATO INCORRECTO");
        }
        return new Cliente(Integer.parseInt(campos[0].trim()), campos[1].trim(), campos[2].trim(),
                campos[3].trim().toLowerCase(), campos[4].trim());
    }

    public static String lineasCliente(Cliente cliente) {
        return cliente.getId() + "," + cliente.getNombre() + "," + cliente.getDni() + "," + cliente.getTelefono();
    }

    /*
     * // ejercicio2
     * public List<Cliente> exportarCliente() {
     * 
     * }
     */

    // Ejercicio3
    public static List<Cliente> leerClienteCSV() {
            try (FileReader fileReader = new FileReader("clientes.csv")) {
    // Se crea un csvToBean de clase Alumno
    CsvToBean<Cliente> csvToBean = new
    CsvToBeanBuilder<Cliente>(fileReader)
    .withType(Cliente.class)
    .build();
    // Parsea el fichero CSV en una lista de alumnos
    List<Cliente> listaAlumnos = csvToBean.parse();
    } catch (IOException e) {
        System.out.println("Error");
        }

    // EJERCICIO 4
    public static void exportarClientesCSV() {
        List<Cliente> listaAlumnos = new ArrayList<>();
        Collections.addAll(listaAlumnos,
                new Cliente(1, "Javier", "111111", "javier@gmail.com", "635254125"),
                new Cliente(2, "andrea", "111111", "javier@gmail.com", "635254125"),
                new Cliente(3, "luis", "111111", "javier@gmail.com", "635254125"));
        try (PrintWriter pw = new PrintWriter("ClientesPrueba.csv")) {
            StatefulBeanToCsv<Cliente> beanToCsv = new StatefulBeanToCsvBuilder<Cliente>(
                    pw).build();
            beanToCsv.write(listaAlumnos);
        } catch (FileNotFoundException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }

}


