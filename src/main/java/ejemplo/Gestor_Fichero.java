package ejemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Gestor_Fichero {

    public static void main(String[] args) {

        
    }


    public List<Cliente> leer_Cliente() {
 
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


    public List <Cliente> comprobar_lista(List<Cliente> clientes){
        List <Cliente> clientesFiltrados =clientes.stream()
            .filter(cliente-> cliente.getTelefono().startsWith("+34"))
            .filter(cliente -> !cliente.getEmail().matches(".*[A-Z].*"))
            //.filter(cliente-> cliente.getEmail().toLowerCase().equals(cliente.getEmail())) OTRA FORMA DE SABER QUE SEA MINUSCULA
            .toList();
        return clientesFiltrados;

    }


    public static void comprobar_linea(String linea){

        if(linea.isEmpty()){
            throw new IllegalArgumentException("NO se pueden lineas vacias ");
        }
        if(linea.)
    }
}
