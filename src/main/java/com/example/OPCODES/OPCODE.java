package com.example.OPCODES;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.example.ConvertBytesToOP;
import com.example.ScriptStack;
/**
    * Enum que representa todos los OPCODES del Script de Bitcoin.
    * Cada const en el enum representa una instrucción en Bitcoin.
    * Cuando un script se ejecuta, sus bytes son decodificados en
    * {@code OPCODE} y aplicados en un stack compartido {@link ScriptStack}.
    * Siguiendo los lineamientos originales del Script de Bitcoin.
    * 
    * Cada OPCODE tiene su identificador byte y hace override a:
    * {@link #aplicar(byte[], ScriptStack)} que define su comportamiento específico.
    * 
    * Los OPCODES están organizados por tarea:
    * Pusheo numérico (@code OP_0.. @code OP_16), 
    * Pusheo de datos (@code OP_PUSHDATA1..4), 
    * Manipulación de stack (@code OP_DUP, @code OP_DROP), 
    * Comparación (@code OP_EQUAL, @code OP_EQUALVERIFY), 
    * Hashing (@code OP_HASH160) y 
    * Verificación de firma (@code OP_CHECKSIG).
*/
public enum OPCODE {

    // ─────────────────────────────────────────────────────────────────
    // OPCODES NUMÉRICOS: OP_0..OP_16
    // Hay distintos OPCODES para empujar los números del 0 al 16 al stack,
    // en lugar de usar OP_PUSHDATA.
    // ─────────────────────────────────────────────────────────────────

    /**
        * Pushea el Integer {@code 0} al stack como un byte array [0].
        * Byte de OPCODE: {@code 0x00}
    */
    OP_0((byte) 0x00) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            stack.pushItem(new byte[] { 0 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },
    /**
        * Pushea el Integer {@code 1} al stack como un byte array [1].
        * Byte de OPCODE: {@code 0x51}
    */

    OP_1((byte) 0x51) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 1 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 2} al stack como un byte array [2].
        * Comunmente se usa para el {@code m} y {@code n} en esquemas multisig m-of-n.
        * Byte de OPCODE: {@code 0x52}
    */
    OP_2((byte) 0x52) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 2 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 3} al stack como un byte array [3].
        * Byte de OPCODE: {@code 0x53}
    */
    OP_3((byte) 0x53) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 3 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 4} al stack como un byte array [4].
        * Byte de OPCODE: {@code 0x54}
    */
    OP_4((byte) 0x54) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 4 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 5} al stack como un byte array [5].
        * Byte de OPCODE: {@code 0x55}
    */
    OP_5((byte) 0x55) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 5 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 6} al stack como un byte array [6].
        * Byte de OPCODE: {@code 0x56}
    */
    OP_6((byte) 0x56) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 6 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 7} al stack como un byte array [7].
        * Byte de OPCODE: {@code 0x57}
    */
    OP_7((byte) 0x57) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 7 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 8} al stack como un byte array [8].
        * Byte de OPCODE: {@code 0x58}
    */
    OP_8((byte) 0x58) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 8 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 9} al stack como un byte array [9].
        * Byte de OPCODE: {@code 0x59}
    */
    OP_9((byte) 0x59) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 9 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 10} al stack como un byte array [10].
        * Byte de OPCODE: {@code 0x5a}
    */
    OP_10((byte) 0x5a) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 10 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 11} al stack como un byte array [11].
        * Byte de OPCODE: {@code 0x5b}
    */
    OP_11((byte) 0x5b) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 11 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 12} al stack como un byte array [12].
        * Byte de OPCODE: {@code 0x5c}
    */
    OP_12((byte) 0x5c) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 12 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 13} al stack como un byte array [13].
        * Byte de OPCODE: {@code 0x5d}
    */
    OP_13((byte) 0x5d) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 13 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 14} al stack como un byte array [14].
        * Byte de OPCODE: {@code 0x5e}
    */
    OP_14((byte) 0x5e) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 14 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 15} al stack como un byte array [15].
        * Byte de OPCODE: {@code 0x5f}
    */
    OP_15((byte) 0x5f) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 15 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Pushea el Integer {@code 16} al stack como un byte array [16].
        * 16 es el número máximo que se puede pushear con un OPCODE numérico,
        * para números mayores se debe usar OP_PUSHDATA.
        * Byte de OPCODE: {@code 0x60}
    */

    OP_16((byte) 0x60) {
        @Override
        public void aplicar(byte[] d, ScriptStack s) {
            s.pushItem(new byte[] { 16 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    // ─────────────────────────────────────────────────────────────────
    // OPCODES DE PUSHEO DE DATOS: OP_PUSHDATA1, OP_PUSHDATA2, OP_PUSHDATA4
    // Estos OPCODES permiten pushear datos de longitud variable al stack.
    // ─────────────────────────────────────────────────────────────────


    /**
        * Hace push de un array de bytes de hasta 255 bytes al stack.
        * En el script puro el opcode va seguido de un byte que funciona como
        * prefijo. El {@link ConvertBytesToOP} se encarga de extraer el prefijo
        * y la carga antes de que este método se llame. De esta forma el {@code dato} que recibe este método es exactamente el array de bytes a pushear.
        * ya solo contiene la carga a pushear.
        * 
        * @param dato es la carga de bytes a pushear
        * @param stack el stack donde se pushea el dato
    */
    OP_PUSHDATA1((byte) 0x4c) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            stack.pushItem(dato);
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Hace push de un array de bytes de hasta 65535 bytes al stack.
        * En el script puro el opcode va seguido de dos bytes que funcionan como
        * prefijo. El {@link ConvertBytesToOP} se encarga de extraer el prefijo
        * y la carga antes de que este método se llame. De esta forma el {@code dato} que recibe este método es exactamente el array de bytes a pushear.
        * ya solo contiene la carga a pushear.
        * 
        * @param dato es la carga de bytes a pushear
        * @param stack el stack donde se pushea el dato
    */
    OP_PUSHDATA2((byte) 0x4d) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            stack.pushItem(dato);
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Hace push de un array de bytes de hasta 4294967295 bytes al stack.
        * En el script puro el opcode va seguido de cuatro bytes que funcionan como
        * prefijo. El {@link ConvertBytesToOP} se encarga de extraer
        * el prefijo y la carga antes de que este método se llame. 
        * De esta forma el {@code dato} que recibe este método es 
        * exactamente el array de bytes a pushear.
        * 
        * @param dato es la carga de bytes a pushear
        * @param stack el stack donde se pushea el dato
    */

    OP_PUSHDATA4((byte) 0x4e) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            stack.pushItem(dato);
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    // ─────────────────────────────────────────────────────────────────
    // OPCODES DE MANIPULACIÓN DE STACK: OP_DUP, OP_DROP
    // ─────────────────────────────────────────────────────────────────

    /**
        * Duplica el elemento en la cima del stack.
        * Escencial para el P2PKH, donde se duplica la clave pública para hashearla 
        * y comparar con el hash esperado.
        * Sin este OPCODE, una copia de la llave pública debería aparecer 2 veces 
        * en el scriptSig, lo que no es eficiente.
        * 
        * Byte de OPCODE: {@code 0x76}
        * @param dato no se utiliza en este OPCODE
        * @param stack el stack donde se duplica el elemento superior
        * @throws IllegalStateException si el stack está vacío al intentar duplicar
    */
    OP_DUP((byte) 0x76) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            if (stack.size() < 1)
                throw new IllegalStateException("Stack vacio en OP_DUP");

            stack.pushItem(stack.peekItem());
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Elimina el elemento en la cima del stack.
        * Útil para descartar datos que no son necesarios para la autenticación,
        * como un byte de versión o flag al inicio del scriptSig.
        * 
        * Byte de OPCODE: {@code 0x75}
        * @param dato no se utiliza en este OPCODE
        * @param stack el stack donde se elimina el elemento superior
        * @throws IllegalStateException si el stack está vacío al intentar eliminar
    */

    OP_DROP((byte) 0x75) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            if (stack.size() < 1)
                throw new IllegalStateException("Stack vacio en OP_DROP");

            stack.popItem();
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    // ─────────────────────────────────────────────────────────────────
    // OPCODES DE COMPARACIÓN: OP_EQUAL, OP_EQUALVERIFY
    // ─────────────────────────────────────────────────────────────────

    /**
        * Compara los dos elementos superiores del stack. Si son iguales, 
        * pushea 1, si no, pushea 0.
        * Esencial para verificar que el hash de la clave pública coincida con 
        * el hash bloqueado en el scriptPubKey.
        * 
        * Byte de OPCODE: {@code 0x87}
        * @param dato no se utiliza en este OPCODE
        * @param stack el stack donde se comparan los dos elementos superiores 
        * y se pushea el resultado
        * @throws IllegalStateException si el stack tiene menos de 2 elementos 
        * al intentar comparar
    */
    OP_EQUAL((byte) 0x87) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            if (stack.size() < 2)
                throw new IllegalStateException("Stack insuficiente en OP_EQUAL");

            byte[] a = stack.popItem();
            byte[] b = stack.popItem();

            stack.pushItem(Arrays.equals(a, b) ? new byte[] { 1 } : new byte[] { 0 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Hace POP de los 2 elementos superiores del stack y hace un throw
        * si no son iguales. Es como OP_EQUAL pero en lugar de pushear el resultado,
        * valida que sean iguales y falla el script si no lo son.
        * Es esencial para el P2PKH, donde se compara el hash de la clave pública 
        * duplicada con el hash bloqueado en el scriptPubKey. Si no coinciden, 
        * el script falla inmediatamente.
        * 
        * Byte de OPCODE: {@code 0x88}
        * 
        * @param dato no se utiliza en este OPCODE
        * @param stack el stack donde se comparan los dos elementos superiores 
        * y se valida que sean iguales
        * @throws IllegalStateException si el stack tiene menos de 2 
        * elementos al intentar comparar
    */

    OP_EQUALVERIFY((byte) 0x88) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            if (stack.size() < 2)
                throw new IllegalStateException("Stack insuficiente en OP_EQUALVERIFY");

            byte[] a = stack.popItem();
            byte[] b = stack.popItem();

            if (!Arrays.equals(a, b))
                throw new IllegalStateException("OP_EQUALVERIFY fallo");
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    // ─────────────────────────────────────────────────────────────────
    // OPCODES DE HASHING Y VERIFICACIÓN DE FIRMA: OP_HASH160, OP_CHECKSIG
    // ─────────────────────────────────────────────────────────────────

    /**
        * Hace POP del elemento superior del stack, le aplica la función hash160 (RIPEMD160(SHA256(x)))
        * y pushea el resultado en 20 bytes.
        * Hash160 es el estándar en como Bitcoin compacta las claves públicas para 
        * usarlas en los scripts.
        * 
        * Byte de OPCODE: {@code 0xa9}
        * 
        * @param dato no se utiliza en este OPCODE
        * @param stack el stack donde se hace POP del elemento superior, 
        * se le aplica hash160 y se pushea el resultado
        * @throws IllegalStateException si el stack está vacío al intentar 
        * hacer POP
    */

    OP_HASH160((byte) 0xa9) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) throws Exception {
            if (stack.size() < 1)
                throw new IllegalStateException("Stack vacio en OP_HASH160");

            byte[] value = stack.popItem();
            stack.pushItem(Hash160.hash160(value));
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    /**
        * Verifica una firma digital con una llave pública.
        * Hace POP de la firma y la clave pública, y pushea 1 si la firma es 
        * válida para esa clave, o 0 si no lo es.
        * Esta es la última etapa en un script P2PKH: solo el propietario de la clave
        * privada correspondiente a la pública bloqueda puede producir 
        * una firma válida y hacer la transacción.
        * 
        * Esta implementación utiliza un mock de comparación de Bytes para simular
        * la verificación real ECDSA.
        * 
        * Byte de OPCODE: {@code 0xac}
        * @param dato no se utiliza en este OPCODE
        * @param stack el stack donde se hace POP de la firma y la clave pública.
        * @throws IllegalStateException si el stack tiene menos de 2 
        * elementos al intentar hacer POP
    */

    OP_CHECKSIG((byte) 0xac) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            if (stack.size() < 2)
                throw new IllegalStateException("Stack insuficiente en OP_CHECKSIG");

            byte[] publicKey = stack.popItem();
            byte[] signature = stack.popItem();

            stack.pushItem(Arrays.equals(publicKey, signature)
                    ? new byte[] { 1 }
                    : new byte[] { 0 });
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    OP_CHECKMULTISIG((byte) 0xae){
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) throws Exception {
            List<byte[]> pubKeyList = new ArrayList<byte[]>();
            List<byte[]> sigList = new ArrayList<byte[]>();
            
            int cantPubKey = stack.popItem()[0];
            for (int i = 0; i < cantPubKey; i++) {
                pubKeyList.add(stack.popItem());
            }

            int cantSig = stack.popItem()[0];
            if (cantSig > cantPubKey) { throw new IllegalStateException("Hay mas firmas que llaves en OP_CHECKMULTISIG"); }
            for (int i = 0; i < cantSig; i++) {
                sigList.add(stack.popItem());
            }

            int contExitos = 0;
            int indexCurrentSig = 0;
            int indexCurrentPubKey = 0;
            byte[] currentSig = sigList.get(0);

            while (indexCurrentPubKey < pubKeyList.size()) {
                if (Arrays.equals(currentSig, pubKeyList.get(indexCurrentPubKey))) {
                    contExitos++;

                    if (contExitos == cantSig) {
                        stack.pushItem(new byte[]{ 1 });
                        break;
                    }
                    else{
                        indexCurrentSig++;
                        currentSig = sigList.get(indexCurrentSig);
                    }
                }

                indexCurrentPubKey++;
            }

            if (contExitos != cantSig) {
                throw new Exception("El checkeo de OP_CHECKMULTISIG resulto negativo");
            }
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    OP_IF((byte) 0x63) {
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            return Arrays.equals(stack.popItem(), new byte[]{ 1 });
        }
    },

    OP_ELSE((byte) 0x67){
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            throw new IllegalStateException("Este else no tiene un if previo");
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    },

    OP_ENDIF((byte) 0x68){
        @Override
        public void aplicar(byte[] dato, ScriptStack stack) {
            throw new IllegalStateException("Este endif no tiene un if previo");
        }

        @Override
        public boolean evaluar(ScriptStack stack) {
            throw new IllegalStateException("Esta operacion no puede evaluar el Stack");
        }
    };

    // ─────────────────────────────────────────────────────────────────
    // Infraestructura del enum OPCODE
    // ─────────────────────────────────────────────────────────────────

    /**
        * Mapea cada byte de protocolo hacia su respectivo const {@code OPCODE}
        * Se precarga para que {@link #fromId(byte)} pueda obtener el OPCODE en O(1) 
        * dado su byte de protocolo. Sin tener que iterar sobre {@link #values()} 
        * cada vez.
    */
    private static final Map<Byte, OPCODE> BY_CODE = new HashMap<>();

    static {
        for (OPCODE op : values()) {
            BY_CODE.put(op.code, op);
        }
    }
    // El identificador byte asignado al protocolo Bitcoin
    private final byte code;

    /**
        * Constructor del enum OPCODE, asigna el byte de protocolo a cada const.
        * @param code el byte de protocolo que identifica este OPCODE en Bitcoin
    */
    OPCODE(byte code) {
        this.code = code;
    }
    /**
        * Método abstracto que cada OPCODE implementa para definir su comportamiento específico.
        * @param dato es un array de bytes que algunos OPCODES pueden usar como carga (e.g. OP_PUSHDATA)
        * @param stack el stack donde el OPCODE realiza sus operaciones
        * @throws Exception si ocurre algún error durante la aplicación del OPCODE
    */
    public abstract void aplicar(byte[] dato, ScriptStack stack) throws Exception;
    public abstract boolean evaluar(ScriptStack stack);

    /**
        * Dado un byte de protocolo, retorna el OPCODE correspondiente.
        * @param code el byte de protocolo a buscar
        * @return el OPCODE correspondiente al byte dado
        * @throws IllegalArgumentException si el byte no corresponde a 
        * ningún OPCODE válido
    */
    public static OPCODE fromId(byte code) {
        OPCODE op = BY_CODE.get(code);
        if (op == null)
            throw new IllegalArgumentException("Codigo no valido: " + code);
        return op;
    }

    /**
        * Método de utilidad para verificar si un OPCODE es un OP_PUSHDATA.
        * @param op el OPCODE a verificar
        * @return true si el OPCODE es OP_PUSHDATA1, OP_PUSHDATA2 o OP_PUSHDATA4; 
        * false en caso contrario
    */

    public static boolean isPUSHDATA(OPCODE op){
        return (op.equals(OPCODE.OP_PUSHDATA1) || op.equals(OPCODE.OP_PUSHDATA2) || op.equals(OPCODE.OP_PUSHDATA4)); 
    }
}