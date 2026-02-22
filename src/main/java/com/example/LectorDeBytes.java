package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;

public class LectorDeBytes {
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
