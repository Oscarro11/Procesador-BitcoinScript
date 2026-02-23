package com.example.OPCODES;

import java.security.MessageDigest;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/*
    * Utilidad criptográfica que implementa el algoritmo HASH160,
    * estándar de Bitcoin para derivar identificadores compactos de 20 bytes
    * a partir de claves públicas.
    *
    * HASH160 aplica SHA-256 seguido de RIPEMD-160 en secuencia.
    * Usar dos funciones de hash distintas aporta resistencia a colisiones
    * (SHA-256) y un output más corto (RIPEMD-160), lo que reduce el tamaño
    * de las transacciones y es la base de los tipos de dirección P2PKH y P2SH.
    *
    * El proveedor BouncyCastle se registra en el bloque estático porque
    * la JVM estándar no incluye RIPEMD-160 de forma nativa.
 */

public class Hash160 {

     /*
        * Registra el proveedor criptográfico BouncyCastle en la JVM.
        * Necesario para que MessageDigest pueda resolver el algoritmo
        * RIPEMD-160, que no está disponible en los proveedores estándar de Java.
        * Se ejecuta una única vez al cargar la clase.
     */

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /*
        * Aplica el algoritmo SHA-256 a los bytes de entrada y retorna su digest.
        * SHA-256 es la primera etapa del HASH160 y es responsable de la
        * resistencia criptográfica del resultado final, garantizando que
        * modificaciones mínimas en la entrada produzcan outputs completamente distintos.
        *
        * @param input el array de bytes a hashear; típicamente una clave pública serializada
        * @return el digest SHA-256 de 32 bytes correspondiente al input
        * @throws Exception si el algoritmo SHA-256 no está disponible en el entorno
     */

    public static byte[] sha256(byte[] input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input);
    }

    /*
        * Aplica el algoritmo RIPEMD-160 a los bytes de entrada y retorna su digest.
        * RIPEMD-160 es la segunda etapa del HASH160 y es responsable de
        * comprimir el output de 32 bytes de SHA-256 a un identificador
        * compacto de 20 bytes, reduciendo el tamaño de las direcciones Bitcoin.
        * Requiere el proveedor BouncyCastle registrado en el bloque estático.
        *
        * @param input el array de bytes a hashear; en el contexto de HASH160,
        * corresponde al digest SHA-256 de la clave pública
        * @return el digest RIPEMD-160 de 20 bytes correspondiente al input
        * @throws Exception si el algoritmo RIPEMD-160 no está disponible
        * o el proveedor BouncyCastle no fue registrado
     */

    public static byte[] ripemd160(byte[] input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("RIPEMD160", "BC");
        return digest.digest(input);
    }

    /*
        * Calcula el HASH160 de los bytes de entrada aplicando SHA-256 seguido de RIPEMD-160.
        * Es el método principal de esta clase y el que consume OP_HASH160 directamente.
        * El resultado de 20 bytes es lo que se almacena en el scriptPubKey como
        * identificador de la clave pública del destinatario, sin exponer la clave misma.
        *
        * @param input el array de bytes a hashear; típicamente una clave pública serializada
        * @return el digest HASH160 de 20 bytes: RIPEMD-160(SHA-256(input))
        * @throws Exception si alguno de los algoritmos de hash no está disponible
        * @see #sha256(byte[])
        * @see #ripemd160(byte[])
     */

    public static byte[] hash160(byte[] input) throws Exception {
        byte[] sha256 = sha256(input);
        return ripemd160(sha256);
    }
}