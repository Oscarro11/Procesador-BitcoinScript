package com.example;

import com.example.OPCODES.Hash160;
import com.example.OPCODES.OPCODE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para secuencias numéricas con OPCODES y manejo de stack.
 *
 * Se utilizan OP_0..OP_16, OP_DROP, OP_DUP, OP_HASH160, OP_EQUALVERIFY, OP_CHECKSIG.
 */
public class ScriptNumericAndCleanupTest {

    private ScriptStack stack;
    // Before each se utiliza para ejecutar una instrucción antes de cada test
    // Aquí se está inicializando un nuevo stack antes de cada test
    @BeforeEach
    void setUp() {
        stack = new ScriptStack();
    }

    // ─────────────────────────────────────────────────────────────────
    // TEST 5 — Multisig con OPCODES numéricos
    //
    // En Bitcoind OP_CHECKMULTISIG sirve para crear direcciones multifirma.
    // El test verifica que los opcodes numéricos empujan el número correcto
    // al stack y que OP_EQUAL puede comparar esos números.
    //
    // Se comprueba que el threshold (límite) de firmas en el esquema multisig
    // coincida con el número total de claves públicas
    //
    // Opcodes utilizados: OP_2, OP_16, OP_0, OP_DROP, OP_EQUAL
    // ─────────────────────────────────────────────────────────────────
    @Test
    void testNumericOpcodes_MultisigThresholdConsistency() throws Exception {
        // Se pushea la cantidad requerida de firmas (m) 
        // y la cantidad total de claves públicas (n)
        OPCODE.OP_2.aplicar(null, stack); // m = 2
        OPCODE.OP_2.aplicar(null, stack); // n = 2

        // Se verifica que m y n sean iguales para un esquema 2-of-2
        OPCODE.OP_EQUAL.aplicar(null, stack);
        byte[] equalResult = stack.popItem();
        // Se popea el resultado de OP_EQUAL (debería ser 1 porque m = n)
        assertArrayEquals(new byte[]{1}, equalResult, "Threshold 2-of-2 verificado, se obtuvo 1");

        // Se verifica que OP_0..OP_16 empujen los valores correctos
        OPCODE.OP_0.aplicar(null, stack);
        byte[] zero = stack.popItem();
        // Se popea el resultado de OP_0 (debería ser [0])
        assertArrayEquals(new byte[]{0}, zero, "OP_0 debería pushear [0]");

        OPCODE.OP_16.aplicar(null, stack);
        byte[] sixteen = stack.popItem();
        assertArrayEquals(new byte[]{16}, sixteen, "OP_16 debería pushear [16]");

        assertTrue(stack.isEmpty(), "El stack debería estar vacío al finalizar el test");
    }

    // ─────────────────────────────────────────────────────────────────
    // TEST 6 — OP_DROP usado para versión/flag antes de P2PKH
    //
    // P2PKH es el tipo de transacción más común en Bitcoin.
    // A veces se incluye un byte de versión o flag al inicio del scriptSig
    // que no forma parte de la autenticación, sino que es metadata.
    // El test simula un protocolo donde se pushea un byte de versión
    // y luego se usa OP_DROP para descartarlo antes de ejecutar el scriptPubKey.
    //
    // Opcodes utilizados: OP_1, OP_DROP, OP_DUP, OP_HASH160, OP_EQUALVERIFY, 
    // OP_CHECKSIG
    // ─────────────────────────────────────────────────────────────────
    @Test
    void testDropVersionByte_ThenP2PKH() throws Exception {
        // Se pushea un byte de versión (1)
        OPCODE.OP_1.aplicar(null, stack);
        assertEquals(1, stack.size(), "El stack debería tener un elemento");

        // Tronarse el byte de versión con OP_DROP, solo era metadata
        OPCODE.OP_DROP.aplicar(null, stack);
        assertTrue(stack.isEmpty(), "El stack debería estar vacío después de OP_DROP");

        // Proceso P2PKH normal después de limpiar el stack
        byte[] signature = {0x11, 0x22, 0x33, 0x44};
        byte[] publicKey = {0x11, 0x22, 0x33, 0x44};
        
        stack.pushItem(signature);
        stack.pushItem(publicKey);

        // Se pushea la llave pública hasheada para el scriptPubKey
        byte[] pubKeyHash = Hash160.hash160(publicKey);

        // Se duplica la clave pública, se hashea el duplicado, 
        // se pushea el hash esperado y se verifica
        OPCODE.OP_DUP.aplicar(null, stack);
        OPCODE.OP_HASH160.aplicar(null, stack);
        stack.pushItem(pubKeyHash);
        OPCODE.OP_EQUALVERIFY.aplicar(null, stack);
        OPCODE.OP_CHECKSIG.aplicar(null, stack);

        byte[] result = stack.popItem();
        // Se popea el resultado de OP_CHECKSIG (debería ser 1)
        assertArrayEquals(new byte[]{1}, result,
                "P2PKH debería tener resultado 1");
        assertTrue(stack.isEmpty(), "El stack debería estar vacío al finalizar el script");
    }
}