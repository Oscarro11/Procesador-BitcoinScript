package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;

/*
    * Clase para leer scripts Bitcoin desde archivos de texto
    * y convertirlos a su representación en bytes crudos.
    *
    * Los scripts se almacenan en disco como cadenas hexadecimales
    * para que sean legibles y editables por humanos. Esta clase
    * actúa como puente entre esa representación textual y el formato
    * binario que ConvertBytesToOP necesita para parsear los OPCODES.
    *
    * @see ConvertBytesToOP
 */
public class LectorDeBytes {

    /*
        * Lee un archivo de texto que contiene un script Bitcoin en formato
        * hexadecimal y retorna su equivalente en bytes crudos.
        * Separar la lectura del disco de la lógica de parseo permite que
        * ConvertBytesToOP opere siempre sobre bytes, sin acoplar la fuente
        * de los datos al intérprete del script.
        *
        * @param filePath ruta al archivo .txt que contiene el script en hexadecimal
        * @return el script como array de bytes listo para ser parseado
        * @throws IOException si el archivo no existe, no puede ser leído,
        * o su contenido no es un hexadecimal válido
     */
    public static byte[] getByteArray(String filePath) throws IOException {
        String contenidoArchivo = null;
        HexFormat hexFormat = HexFormat.of();

        try {
            Path path = Paths.get(filePath);
            contenidoArchivo = Files.readString(path);

        } catch (IOException e) {
            throw e;
        }

        return hexFormat.parseHex(contenidoArchivo);
    }
}
