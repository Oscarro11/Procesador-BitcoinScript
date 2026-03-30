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

    //Este test evalua un script que usa if, pero que no tiene hash160
    @Test
    public void combinacion2(){
        Controlador controlador = new Controlador();
        String rutaFirma = "src/test/resources/firma2.txt";
        String rutaLlave = "src/test/resources/llave2.txt";

        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);

        Assertions.assertTrue(controlador.evaluarTransaccion(false));
    }

    //Este test evalua script con if y con hash160
    @Test
    public void combinacion3(){
        Controlador controlador = new Controlador();
        String rutaFirma = "src/test/resources/firma3.txt";
        String rutaLlave = "src/test/resources/llave3.txt";

        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);

        Assertions.assertTrue(controlador.evaluarTransaccion(false));
    }

    //Este test evalua script con ifs anidados y con hash160
    @Test
    public void combinacion4(){
        Controlador controlador = new Controlador();
        String rutaFirma = "src/test/resources/firma4.txt";
        String rutaLlave = "src/test/resources/llave4.txt";

        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);

        Assertions.assertTrue(controlador.evaluarTransaccion(false));
    }

    //Este test evalua script con multisig 2 de 3
    @Test
    public void combinacion5(){
        Controlador controlador = new Controlador();
        String rutaFirma = "src/test/resources/firma5.txt";
        String rutaLlave = "src/test/resources/llave5.txt";

        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);

        Assertions.assertTrue(controlador.evaluarTransaccion(false));
    }

    //Este test evalua script con multisig 2 de 3, pero provoca fallo
    @Test
    public void combinacion6(){
        Controlador controlador = new Controlador();
        String rutaFirma = "src/test/resources/firma6.txt";
        String rutaLlave = "src/test/resources/llave6.txt";

        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);

        Assertions.assertFalse(controlador.evaluarTransaccion(false));
    }
}
