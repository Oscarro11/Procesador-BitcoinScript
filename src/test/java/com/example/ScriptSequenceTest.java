package com.example;

import com.example.OPCODES.Hash160;
import com.example.OPCODES.OPCODE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;  

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test para secuencias de Bitcoid Script 
 *
 * Cada test sigue un flujo realista de ScriptPub a ScriptSig
 */
public class ScriptSequenceTest {

    private ScriptStack stack;

    @BeforeEach
    void setUp() {
        stack = new ScriptStack();
    }

    // ─────────────────────────────────────────────────────────────────
    // TEST 1 — P2PKH (Pay-to-Public-Key-Hash)
    //
    // Es la transacción más común en Bitcoin
    //   scriptSig:    <sig> <pubKey>
    //   scriptPubKey: OP_DUP OP_HASH160 <pubKeyHash> OP_EQUALVERIFY OP_CHECKSIG
    //
    // Opcodes utilizados: OP_DUP, OP_HASH160, OP_EQUALVERIFY, OP_CHECKSIG
    // ─────────────────────────────────────────────────────────────────
    @Test
    void testP2PKH_ValidSignatureAndPublicKey() throws Exception {
        // Se usa una variable para una firma y una clave pública
        byte[] signature = {10, 20, 30, 40, 50};
        byte[] publicKey = {10, 20, 30, 40, 50}; 
        // Es la misma
        // Para que CHECKSIG pase, es un test mockeado, es decir
        // solo necesita que la firma y la clave pública sean iguales.

        // Se pushean la firma y la clave pública en el stack (scriptSig)
        stack.pushItem(signature);
        stack.pushItem(publicKey);
        
        // Se espera la presencia del pubKeyHash para el scriptPubkey
        byte[] pubKeyHash = Hash160.hash160(publicKey);

        // ejecución del scriptPubKey
        // aplicar() es un método proveniente de la clase OPCODE
        // cuyos parametros son un byte y el stack
        OPCODE.OP_DUP.aplicar(null, stack);          // Se duplica la clave pública
        OPCODE.OP_HASH160.aplicar(null, stack);      // se hashea la clave pública duplicada
        stack.pushItem(pubKeyHash);                  // se pushea el hash esperado (desde el scriptPubKey)
        OPCODE.OP_EQUALVERIFY.aplicar(null, stack);
        // Se verifica si el hash del duplicado y el esperado son iguales
        OPCODE.OP_CHECKSIG.aplicar(null, stack);     // se aplica el mock (comparar ambas claves)

        byte[] result = stack.popItem(); // se hace un pop para obtener el resultado final del script
        assertArrayEquals(new byte[]{1}, result, "P2PKH, ambas claves deberían coincidir");
        assertTrue(stack.isEmpty(), "El stack debería estar vacío al finalizar el script");
    }

    // ─────────────────────────────────────────────────────────────────
    // TEST 2 — P2PKH — La llave pública hasheada es inválida
    //
    // Simula una transacción donde la llave pública proporcionada no corresponde
    // a la hash bloqueada en el scriptPubKey.
    //
    // Opcodes utilizados: OP_DUP, OP_HASH160, OP_EQUALVERIFY
    // ─────────────────────────────────────────────────────────────────
    @Test
    void testP2PKH_InvalidPublicKeyHashFails() throws Exception {
        byte[] signature  = {1, 2, 3};
        byte[] publicKey  = {1, 2, 3};
        byte[] wrongKey   = {9, 8, 7}; //clave diferente para generar un hash distinto
        
        // Se hace un push de la firma y la clave pública en el stack (scriptSig)
        stack.pushItem(signature);
        stack.pushItem(publicKey);

        // el scriptPubKey espera el hash de wrongKey, no de publicKey
        byte[] lockedHash = Hash160.hash160(wrongKey);

        OPCODE.OP_DUP.aplicar(null, stack); //Se duplica la clave pública
        OPCODE.OP_HASH160.aplicar(null, stack); // Se hashea la clave pública duplicada
        stack.pushItem(lockedHash); // Se pushea el hash esperado
        // es decir, el hash de wrongKey.

        // Debería fallar en OP_EQUALVERIFY porque el hash del duplicado 
        // no coincide con el hash esperado
        assertThrows(IllegalStateException.class,
                () -> OPCODE.OP_EQUALVERIFY.aplicar(null, stack),
                "OP_EQUALVERIFY cuando el hash del duplicado no coincide con el bloqueado debería lanzar una excepción");
    }

    // ─────────────────────────────────────────────────────────────────
    // TEST 3 — OP_EQUAL comparación de datos simple
    // Simula un script simple estilo P2SH
    // P2SH es un tipo de transacción donde el scriptPubKey
    // espera un hash de un script, y el scriptSig provee el script completo
    //   scriptSig:    <data>
    //   scriptPubKey: OP_PUSHDATA1 <expected_data> OP_EQUAL
    //
    // Opcodes utilizados: OP_PUSHDATA1, OP_EQUAL
    // ─────────────────────────────────────────────────────────────────
    @Test
    void testPushDataEqual_MatchingData() throws Exception {
        byte[] secret = {0x4a, 0x4b, 0x4c}; // secreto que el usuario quiere revelar

        // scriptSig: se pushea el secreto que el usuario quiere revelar
        OPCODE.OP_PUSHDATA1.aplicar(secret, stack);

        // scriptPubKey: se pushea el valor esperado (el mismo secreto) 
        // y se compara con OP_EQUAL
        byte[] expectedSecret = {0x4a, 0x4b, 0x4c}; 
        OPCODE.OP_PUSHDATA1.aplicar(expectedSecret, stack); 
        OPCODE.OP_EQUAL.aplicar(null, stack);
        // OP_EQUAL compara ambos valores en el stack

        byte[] result = stack.popItem();
        // Se popea el resultado de OP_EQUAL (debería ser 1)
        assertArrayEquals(new byte[]{1}, result, "OP_EQUAL debería pushear 1 cuando los datos coinciden");
    }

    // ─────────────────────────────────────────────────────────────────
    // TEST 4 — OP_EQUAL pushea 0 cuando los datos no coinciden
    //
    // A diferencia de OP_EQUALVERIFY, OP_EQUAL pushea 0 en el stack
    // si los datos no coinciden, en lugar de lanzar una excepción.
    // El test simula que se valide este contrato
    //
    // Opcodes utilizados: OP_PUSHDATA2, OP_EQUAL
    // ─────────────────────────────────────────────────────────────────
    @Test
    void testPushDataEqual_MismatchedDataPushesZero() throws Exception {
        byte[] userInput    = {0x01, 0x02, 0x03}; // dato que el usuario provee
        byte[] lockedValue  = {(byte)0xFF, (byte)0xFE, (byte)0xFD}; 
        // valor bloqueado en el scriptPubKey que se espera comparar

        OPCODE.OP_PUSHDATA2.aplicar(userInput,   stack);
        // se pushea el dato del usuario (scriptSig)
        OPCODE.OP_PUSHDATA2.aplicar(lockedValue, stack);
        // se pushea el valor bloqueado (scriptPubKey)
        OPCODE.OP_EQUAL.aplicar(null, stack);
        // se compara ambos datos, debería pushear 0 porque no coinciden

        byte[] result = stack.popItem();
        // Se popea el resultado de OP_EQUAL (debería ser 0)
        assertArrayEquals(new byte[]{0}, result, "OP_EQUAL debería pushear 0 cuando los datos no coinciden");
    }
}