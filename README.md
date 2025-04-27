💱 Conversor de Monedas en Java

Aplicación de consola en Java que permite convertir montos entre diferentes monedas en tiempo real utilizando la ExchangeRate-API. Guarda también un historial de conversiones realizadas durante la sesión.

📋 Tabla de Contenidos
Características

Tecnologías Utilizadas

Requisitos

Instalación y Ejecución

Configuración de la API

Estructura del Código

Ejemplo de Uso

Consideraciones

Licencia

✨ Características
Conversión entre múltiples monedas:

USD ⇄ ARS (Peso argentino)

USD ⇄ BRL (Real brasileño)

USD ⇄ COP (Peso colombiano)

EUR ⇄ COP

MXN ⇄ COP

Tasas de cambio actualizadas en tiempo real.

Registro de historial de conversiones con fecha y hora.

Menú interactivo por consola.

🛠️ Tecnologías Utilizadas
Java (JDK 8 o superior)

Gson - Librería para parsear JSON

HTTPURLConnection - Cliente HTTP nativo de Java

ExchangeRate-API - API de tasas de cambio

✅ Requisitos
Java JDK 8+

Gson (agregado al proyecto)

xml
Copiar
Editar
<!-- Maven -->
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.9</version>
</dependency>

⚡ Instalación y Ejecución
Clona este repositorio o descarga Principal.java.

Asegúrate de incluir la librería Gson.

Compila el archivo:

bash
Copiar
Editar
javac Principal.java
Ejecuta el programa:

bash
Copiar
Editar
java Principal

🔑 Configuración de la API
Este proyecto requiere una clave de acceso (API Key) para consultar las tasas de cambio.

Obtén una en ExchangeRate-API.

Modifica la siguiente constante en el código:

java
Copiar
Editar
private static final String API_KEY = "TU_API_KEY";
🏗️ Estructura del Código
main ➔ Muestra el menú y procesa las opciones.

getExchangeRate ➔ Consulta la tasa de cambio.

guardarEnHistorial ➔ Registra las conversiones.

mostrarHistorial ➔ Muestra el historial de operaciones.

ExchangeResponse ➔ Clase interna para parseo de JSON.

🎯 Ejemplo de Uso
plaintext
Copiar
Editar
==== CONVERSOR DE MONEDAS ====
Seleccione una opción (1-12): 3
Ingrese el monto a convertir (USD): 250
Tasa de cambio USD a BRL: 5.1000
Resultado: 250.00 USD = 1275.00 BRL

🧠 Consideraciones
Se requiere conexión a Internet para obtener tasas de cambio.

El historial de conversiones se mantiene en memoria (no se guarda en archivos).

Manejo de excepciones en la conexión y parseo de datos.

📝 Licencia
Este proyecto está licenciado bajo la Licencia MIT.
Puedes usarlo, modificarlo y distribuirlo libremente. 🚀
