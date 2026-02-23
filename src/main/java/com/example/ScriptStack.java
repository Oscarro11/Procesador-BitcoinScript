package com.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

/*
    * Stack de ejecución para el intérprete de Bitcoin Script.
    * Extiende LinkedBlockingDeque para representar una estructura LIFO
    * donde cada elemento es un array de bytes, tal como lo define el
    * protocolo Bitcoin Script.
    *
    * LinkedBlockingDeque almacena sus elementos con la cabeza al frente
    * y la cola al fondo, por lo que la cima del stack se mapea al último
    * elemento de la deque. Esta clase sobreescribe el iterador y forEach
    * para que cualquier recorrido externo refleje el orden visual correcto
    * del stack: de la cima hacia la base.
    *
    * @see Controlador
    * @see OPCODE
 */
public class ScriptStack extends LinkedBlockingDeque<byte[]> {

     /*
        * Empuja un elemento a la cima del stack.
        * Es la operación fundamental que todos los OPCODES de tipo PUSH
        * utilizan para depositar sus resultados o cargas de datos en el stack.
        *
        * @param item el array de bytes a colocar en la cima del stack
     */
    public void pushItem(byte[] item) {
        super.addLast(item);
    }

    /*
        * Extrae y retorna el elemento en la cima del stack, removiéndolo.
        * Los OPCODES que consumen operandos, como OP_EQUAL o OP_HASH160,
        * usan este método para obtener los valores sobre los que operan.
        *
        * @return el array de bytes que estaba en la cima del stack
     */
    public byte[] popItem() {
        return super.removeLast();
    }

    /*
        * Retorna el elemento en la cima del stack sin removerlo.
        *
        * @return el array de bytes en la cima del stack
     */
    public byte[] peekItem() {
        return super.peekLast();
    }

    /*
        * Sobreescribe el iterador para que recorra el stack de la cima a la base.
        * Sin esta sobreescritura, el iterador heredado de LinkedBlockingDeque
        * recorrería los elementos de la base a la cima, invirtiendo el orden
        * visual esperado del stack al momento de imprimirlo o inspeccionarlo.
        *
        * @return un iterador que recorre los elementos desde la cima hacia la base
     */
    @Override
    public Iterator<byte[]> iterator() {
        return super.descendingIterator();
    }

    /*
        * Sobreescribe forEach para que aplique la acción de la cima a la base,
        * consistente con el orden definido por el iterador sobreescrito.
        * Garantiza que cualquier uso externo del stack vea los elementos
        * en el orden correcto sin depender de la implementación interna de la deque.
        *
        * @param action la acción a aplicar a cada elemento del stack
     */
    @Override
    public void forEach(Consumer<? super byte[]> action) {
        super.descendingIterator().forEachRemaining(action);
    }

    /*
        * Imprime el estado actual del stack en consola, de la cima a la base.
        * Diseñado para el modo trace del Controlador, donde inspeccionar
        * el stack después de cada OPCODE es esencial para depurar
        * y verificar el flujo de ejecución del script.
     */

    public void printStackState() {
        System.out.println("Stack state:");
        Iterator<byte[]> it = super.descendingIterator();
        while (it.hasNext()) {
            System.out.println(Arrays.toString(it.next()));
        }
    }
}