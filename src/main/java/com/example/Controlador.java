package com.example;

import java.io.IOException;

import java.util.List;
import java.util.Arrays;

import com.example.OPCODES.OPCODE;
/*
    * Controlador principal de la validación de transacciones Bitcoin.
    * Coordina la lectura de los scripts, su conversión a OPCODES y la
    * ejecución secuencial del scriptSig (firma) seguido del scriptPubKey (llave),
    * determinando si una transacción es válida según las reglas del protocolo.
    *
    * El flujo de validación sigue el modelo original de Bitcoin:
    * primero se ejecutan los OPCODES del scriptSig para preparar el stack,
    * y luego los del scriptPubKey para verificar las condiciones de gasto.
    * Una transacción es válida si al final del script el stack contiene
    * exactamente un elemento con valor 1.
    *
    * @see ConvertBytesToOP
    * @see ScriptStack
    * @see LectorDeBytes
 */
public class Controlador {

     /*
        * Stack compartido sobre el que se ejecutan todos los OPCODES.
        * Se limpia al inicio de cada evaluación para garantizar que
        * transacciones anteriores no contaminen el estado actual.
     */
    private ScriptStack stack = new ScriptStack();

    /*
        * Convertidor que procesa los bytes del scriptSig (firma del gastador)
        * y los traduce a una lista de OPCODES ejecutables.
    */
    private ConvertBytesToOP convertidorFirma;

    /*
        * Convertidor que procesa los bytes del scriptPubKey (llave pública bloqueada)
        * y los traduce a una lista de OPCODES ejecutables.
    */
    private ConvertBytesToOP convertidorLlave;

    /*
        * Controla si la ejecución imprime el estado del stack después
        * de cada OPCODE.
     */
    private boolean traceMode = false;


    /*
        * Carga y procesa los scripts de firma y llave pública desde archivos de texto
        * que contienen su representación hexadecimal.
        * Debe llamarse antes de evaluarTransaccion, ya que establece los
        * convertidores que el evaluador necesita para operar.
        *
        * @param rutaFirma  ruta al archivo .txt con el scriptSig en hexadecimal
        * @param rutaLlave  ruta al archivo .txt con el scriptPubKey en hexadecimal
        * @throws IllegalArgumentException si alguna de las rutas no existe
        * o no puede ser leída
     */
    public void obtenerFirmaYLlave(String rutaFirma, String rutaLlave){
        byte[] firma = null;
        byte[] llavePublica = null;
        
        try {
            firma = LectorDeBytes.getByteArray(rutaFirma);
            llavePublica = LectorDeBytes.getByteArray(rutaLlave);

        } catch (IOException e) {
            throw new IllegalArgumentException("La ruta del archivo.txt en donde esta la firma o la llave publica es incorrecta(s)");
        }

        convertidorFirma = new ConvertBytesToOP(firma);
        convertidorLlave = new ConvertBytesToOP(llavePublica);
    }

    /*
        * Evalúa si la transacción es válida ejecutando el scriptSig seguido
        * del scriptPubKey sobre un stack limpio.
        * Replica el modelo de validación de Bitcoin: el scriptSig aporta
        * los datos de desbloqueo y el scriptPubKey impone las condiciones
        * que deben cumplirse. Si cualquier OPCODE lanza una excepción durante
        * la ejecución (por ejemplo OP_EQUALVERIFY al detectar una llave incorrecta),
        * la transacción se considera inválida sin propagar el error.
        *
        * @param traceMode si es {@code true}, imprime el nombre del OPCODE y el
        * estado del stack después de cada instrucción ejecutada
        * @return {@code true} si el stack contiene exactamente un elemento con
        * valor 1 al finalizar la ejecución; {@code false} en cualquier
        * otro caso, incluyendo errores durante la ejecución
        * @throws IllegalStateException si obtenerFirmaYLlave no fue llamado antes
     */

    public boolean evaluarTransaccion(boolean traceMode) throws IllegalStateException{
        if (convertidorFirma == null || convertidorLlave == null) {
            throw new IllegalStateException("No se han elegido los archivos de donde provienen la llave publica ni la firma");
        }

        this.traceMode = traceMode;        
        stack.clear();

        List<byte[]> dataToPushFirma = convertidorFirma.getDataToPush();
        List<byte[]> dataToPushLlave = convertidorLlave.getDataToPush();
        List<OPCODE> opcodesFirma = convertidorFirma.getOpcodes();
        List<OPCODE> opcodesLlave = convertidorLlave.getOpcodes();
    
        for (OPCODE opcode : opcodesFirma) {
            try {
                if (OPCODE.isPUSHDATA(opcode)) {
                    execute(opcode, dataToPushFirma.remove(0), stack);
                }
                else{
                    execute(opcode, null, stack);
                }   
            } catch (Exception e) {
                return false;
            }
        }

        for (OPCODE opcode : opcodesLlave) {
            try {
                if (OPCODE.isPUSHDATA(opcode)) {
                    execute(opcode, dataToPushLlave.remove(0), stack);
                }
                else{
                    execute(opcode, null, stack);
                }   
            } catch (Exception e) {
                return false;
            }
        }

        return (stack.size() == 1 && Arrays.equals(stack.popItem(), new byte[]{ 1 }));
    }

    /*
        * Despacha un OPCODE sobre el stack e imprime el estado resultante
        * si el modo trace está activo.
        * Al centralizar la ejecución en este método, el traceMode se aplica
        * de forma uniforme sin duplicar lógica en los dos ciclos del evaluador.
        *
        * @param opcode el OPCODE a ejecutar
        * @param data   la carga de bytes para OPCODES de tipo PUSHDATA;
        * {@code null} para el resto
        * @param stack  el stack compartido sobre el que opera el OPCODE
        * @throws Exception si el OPCODE encuentra un error durante su ejecución
     */

    private void execute(OPCODE opcode, byte[] data, ScriptStack stack) throws Exception {
        opcode.aplicar(data, stack);

        if (traceMode) {
            System.out.println(opcode.name());
            stack.printStackState();
        }
    }
}
