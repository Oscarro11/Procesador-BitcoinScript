package com.example;

import java.util.Scanner;

public class App {
    private static boolean traceMode = false;
    private static Scanner teclado = new Scanner(System.in);
    private static Controlador controlador = new Controlador();

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        int opcion = 0;
        
        if (args.length > 0 && args[0].equals("--trace")) {
            traceMode = true;
        }

        escribir("Bienvenido al simulador de interprete de BitScript");
        while (continuar) {
            escribir("""
                
                Seleccione una opcion del menu: 
                
                1. Elegir archivos .txt con firma y llave publica
                2. Evaluar firma con llave publica
                3. Salir
                """
                );
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    String rutaFirma = "";
                    String rutaLlave = "";
                    
                    escribir("Ingrese la ruta absoluta del archivo .txt correspondiente a la firma");
                    rutaFirma = teclado.nextLine();

                    escribir("Ingrese la ruta absoluta del archivo .txt correspondiente a la llave publica");
                    rutaLlave = teclado.nextLine();

                    try {
                        controlador.obtenerFirmaYLlave(rutaFirma, rutaLlave);
                        escribir("Ambas rutas se han cargado adecuadamente al sistema\n");
                    } catch (IllegalArgumentException e) {
                        escribir("La ruta de la firma y/o de la llave publica son incorrectas. Intentelo de nuevo");
                    }
                    break;

                case 2:
                    if (!traceMode) {
                        boolean askTrace = true;

                        while (askTrace) {
                            escribir("¿Desea usar el modo --trace para realizar la operacion? (y/n)");
                            switch (teclado.nextLine().strip()) {
                                case "y", "Y": 
                                    traceMode = true;
                                    askTrace = false; 
                                    break;

                                case "n", "N": 
                                    traceMode = false; 
                                    askTrace = false;
                                    break;

                                default: 
                                    escribir("El caracter ingresado no corresponde con 'y' o 'n', intentelo de nuevo");
                                    break;
                            }
                        }
                    }

                    try {
                        if (controlador.evaluarTransaccion(traceMode)){
                            escribir("La transaccion puede realizarse con exito, accediendo a los fondos");
                        }
                        else{
                            escribir("La firma no es adecuada para la llave publica, por lo que no se puede acceder a los fondos");
                        }
                        
                    } catch (Exception e) {
                        escribir("La firma no es adecuada para la llave publica, por lo que no se puede acceder a los fondos");
                    }
                    break;

                case 3:
                    escribir("Gracias por usar el programa, esperamos volver a verlo pronto");
                    continuar = false;
                    break;
            
                default:
                    escribir("La opcion ingresada no corresponde con alguna de las del menu, intentelo de nuevo\n");
                    break;
            }
        }
    }

    private static void escribir(String texto){
        System.out.println(texto);
    }
}