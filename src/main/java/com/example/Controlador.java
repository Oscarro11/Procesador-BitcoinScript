package com.example;

import java.io.IOException;

import java.util.List;
import java.util.Arrays;

import com.example.OPCODES.OPCODE;

public class Controlador {
    private ScriptStack stack = new ScriptStack();
    private ConvertBytesToOP convertidorFirma;
    private ConvertBytesToOP convertidorLlave;
    private boolean traceMode = false;

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

    private void execute(OPCODE opcode, byte[] data, ScriptStack stack) throws Exception {
        opcode.aplicar(data, stack);

        if (traceMode) {
            System.out.println(opcode.name());
            stack.printStackState();
        }
    }
}
