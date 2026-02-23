package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.OPCODES.OPCODE;

/*
    * Parser que traduce un script Bitcoin en formato de bytes crudos
    * a dos listas paralelas: los OPCODES identificados y las cargas de datos
    * asociadas a los OPCODES de tipo PUSHDATA.
    *
    * En el protocolo Bitcoin, un script es una secuencia de bytes donde cada
    * byte puede representar un OPCODE o formar parte de una carga de datos.
    * Esta clase recorre esa secuencia, clasifica cada byte y extrae las cargas
    * de los PUSHDATA de forma anticipada, dejando todo listo para que el
    * Controlador pueda ejecutar el script sin necesidad de volver a parsear.
    *
    * @see OPCODE
    * @see Controlador
 */

public class ConvertBytesToOP{

    /*
        * Posición actual dentro del array de bytes del script.
        * Se avanza manualmente durante el parseo para permitir que
        * PushDataBytes consuma los bytes de longitud y carga sin
        * perder la posición global del recorrido.
     */ 
    private int cursor = 0;

    /*
        * Cargas de datos extraídas de los OPCODES de tipo PUSHDATA,
        * en el mismo orden en que aparecen en el script.
        * Se mantienen separadas de los OPCODES para que el Controlador
        * pueda despacharlas de forma independiente al ejecutar cada instrucción.
     */
    private List<byte[]> dataToPush = new ArrayList<>();


    /*
        * Secuencia de OPCODES identificados en el script, en orden de aparición.
        * Incluye tanto los OPCODES de tipo PUSHDATA como el resto,
        * preservando el flujo de ejecución original del script.
     */
    private List<OPCODE> opcodes = new ArrayList<>();

     /*
        * El script Bitcoin en su representación de bytes crudos.
        * Es la fuente de datos que traducirToList y PushDataBytes recorren
        * durante el parseo inicial.
     */
    private byte[] linea;

    /*
        * Construye el convertidor a partir de un script en bytes y lo parsea
        * de inmediato, dejando las listas de OPCODES y datos listas para su consulta.
        * El parseo ocurre en construcción porque el convertidor no tiene estado
        * válido sin haber procesado el script que lo define.
        *
        * @param linea el script Bitcoin en bytes crudos, 
        * ya sea scriptSig o scriptPubKey
     */

    public ConvertBytesToOP(byte[] linea){
        this.linea = linea;
        traducirToList();
    }

    /*
        * Recorre el array de bytes del script e identifica cada OPCODE.
        * Cuando encuentra un OPCODE de tipo PUSHDATA, delega la extracción
        * de su carga a PushDataBytes para que el cursor avance correctamente
        * más allá de los bytes de longitud y datos antes de continuar.
        * El resultado queda almacenado en las listas opcodes y dataToPush.
     */
    private void traducirToList(){
        while (cursor < linea.length) {
            byte byteActual = linea[cursor];
            opcodes.add(OPCODE.fromId(byteActual));

            if (byteActual == (byte) 0x4c || byteActual == (byte) 0x4d || byteActual == (byte) 0x4e){
                dataToPush.add(PushDataBytes(byteActual));    
            }

            cursor++;
        }
    }

     /*
        * Extrae la carga de datos de un OPCODE PUSHDATA a partir de la
        * posición actual del cursor.
        * Cada variante de PUSHDATA indica en cuántos bytes viene codificada
        * la longitud de la carga: 1 byte para PUSHDATA1, 2 para PUSHDATA2
        * y 4 para PUSHDATA4. Este método lee primero esos bytes de longitud
        * y luego los bytes de carga, avanzando el cursor en consecuencia
        * para que traducirToList pueda continuar desde el byte correcto.
        *
        * @param OP el byte identificador del OPCODE PUSHDATA que se está procesando
        * @return el array de bytes que representa la carga a pushear en el stack
     */
    private byte[] PushDataBytes(byte OP){
        int bytesALeer = 0;
        int bytesAIngresar = 0;

        switch (OP) {
            case 0x4c: bytesALeer = 1; break;
            case 0x4d: bytesALeer = 2; break;
            case 0x4e: bytesALeer = 4; break;

            default: break;
        }        

        for (int i = 0; i < bytesALeer; i++) {
            cursor++;
            bytesAIngresar += linea[cursor];
        }

        byte[] newHead = new byte[bytesAIngresar];

        for (int i = 0; i < bytesAIngresar; i++){
            cursor++;
            newHead[i] = linea[cursor];
        }

        return newHead;   
    }
    /*
        * Retorna la lista de cargas de datos extraídas de los OPCODES PUSHDATA,
        * en el orden en que aparecen en el script original.
        * El Controlador consume esta lista de forma secuencial al despachar
        * cada OPCODE PUSHDATA durante la ejecución del script.
        *
        * @return lista de arrays de bytes listos para ser pusheados al stack
     */
    public List<byte[]> getDataToPush() {
        return dataToPush;
    }

    /*
        * Retorna la secuencia completa de OPCODES identificados en el script,
        * en el orden de aparición original.
        * El Controlador itera sobre esta lista para ejecutar el script
        * instrucción por instrucción sobre el stack compartido.
        *
        * @return lista de OPCODES en orden de ejecución
     */

    public List<OPCODE> getOpcodes() {
        return opcodes;
    }
}