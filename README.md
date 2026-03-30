# Procesador-BitcoinScript

## Descripción
Este proyecto es un procesador de scripts de Bitcoin. Permite interpretar y ejecutar scripts escritos en Bitcoin Script, un lenguaje utilizado en las transacciones de Bitcoin.

## Estructura del Proyecto
El proyecto está organizado de la siguiente manera:
- **src/main/java**: Contiene el código fuente principal.
  - `App.java`: Clase principal que inicia el programa.
  - `Controlador.java`: Gestiona la ejecución de los scripts.
  - `ConvertBytesToOP.java`: Convierte bytes en operaciones de Bitcoin Script.
  - `ScriptStack.java`: Implementa la pila utilizada en los scripts.
  - `LectorDeBytes.java`: Lee y procesa los datos de entrada.
  - **OPCODES/**: Contiene las clases relacionadas con las operaciones específicas de Bitcoin Script.
- **src/test/java**: Contiene las pruebas unitarias.

## Requisitos
- Java 17
- Maven

## Cómo Ejecutar
1. Clona este repositorio.
2. Asegúrate de tener Java y Maven instalados.
3. En la terminal, navega a la carpeta del proyecto.
4. Ejecuta el siguiente comando para compilar y ejecutar el programa en modo normal:
   ```
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.example.App"
   ```
5. Si deseas ver cómo funciona cada operación, utiliza el modo trace con el siguiente comando:
   ```
   mvn exec:java -Dexec.mainClass="com.example.App" --trace
   ```

## Cómo Ejecutar las Pruebas
Para ejecutar las pruebas unitarias, utiliza el siguiente comando:
```
mvn test
```

## Notas
Este proyecto fue realizado como parte de la materia "Algoritmos y Estructura de Datos". Es un trabajo académico y puede no estar optimizado para entornos de producción.