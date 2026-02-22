package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ControladorTest {
    
    @Test
    public void combinacion1(){
        Controlador controlador = new Controlador();
        String rutaFirma = "src/test/resources/firma1.txt";
        String rutaLlave = "src/test/resources/llave1.txt";

        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);

        Assertions.assertTrue(controlador.evaluarTransaccion(false));
    }
}
