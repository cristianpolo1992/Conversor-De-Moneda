package Principal;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private static final String API_KEY = "86047cd6c89477245305bb89";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private static final List<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== CONVERSOR DE MONEDAS ====");
            System.out.println("1. Dólar ==> Peso Argentino");
            System.out.println("2. Peso Argentino ==> Dólar");
            System.out.println("3. Dólar ==> Real Brasileño");
            System.out.println("4. Real Brasileño ==> Dólar");
            System.out.println("5. Dólar ==> Peso Colombiano");
            System.out.println("6. Peso Colombiano ==> Dólar");
            System.out.println("7. Euro ==> Peso Colombiano");
            System.out.println("8. Peso Colombiano ==> Euro");
            System.out.println("9. Peso Mexicano ==> Peso Colombiano");
            System.out.println("10. Peso Colombiano ==> Peso Mexicano");
            System.out.println("11. Ver historial de conversiones");
            System.out.println("12. Salir del programa");
            System.out.println("***********************************************");
            System.out.print("Seleccione una opción (1-12): ");

            int option = scanner.nextInt();

            if (option == 12) {
                System.out.println("Saliendo del programa... Gracias por usar nuestros servicios.");
                break;
            } else if (option == 11) {
                mostrarHistorial();
                continue;
            }

            String base = "";
            String target = "";

            switch (option) {
                case 1 -> { base = "USD"; target = "ARS"; }
                case 2 -> { base = "ARS"; target = "USD"; }
                case 3 -> { base = "USD"; target = "BRL"; }
                case 4 -> { base = "BRL"; target = "USD"; }
                case 5 -> { base = "USD"; target = "COP"; }
                case 6 -> { base = "COP"; target = "USD"; }
                case 7 -> { base = "EUR"; target = "COP"; }
                case 8 -> { base = "COP"; target = "EUR"; }
                case 9 -> { base = "MXN"; target = "COP"; }
                case 10 -> { base = "COP"; target = "MXN"; }
                default -> {
                    System.out.println("Opción inválida. Por favor digite una opción de 1 a 12.");
                    continue;
                }
            }

            System.out.print("Ingrese el monto a convertir (" + base + "): ");
            double amount = scanner.nextDouble();

            try {
                double rate = getExchangeRate(base, target);
                double result = amount * rate;
                System.out.printf("Tasa de cambio %s a %s: %.4f%n", base, target, rate);
                System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, base, result, target);

                guardarEnHistorial(base, target, amount, result);

            } catch (Exception e) {
                System.out.println("Error al obtener tasa de cambio: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static double getExchangeRate(String base, String target) throws Exception {
        URL url = new URL(BASE_URL + base);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        Gson gson = new Gson();
        ExchangeResponse response = gson.fromJson(content.toString(), ExchangeResponse.class);

        if (response.conversion_rates.containsKey(target)) {
            return response.conversion_rates.get(target);
        } else {
            throw new Exception("Moneda destino no encontrada.");
        }
    }

    private static void guardarEnHistorial(String base, String target, double amount, double result) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String registro = String.format("[%s] %.2f %s => %.2f %s",
                ahora.format(formatter), amount, base, result, target);

        historial.add(registro);
    }

    private static void mostrarHistorial() {
        System.out.println("==== HISTORIAL DE CONVERSIONES ====");
        if (historial.isEmpty()) {
            System.out.println("No se han realizado conversiones aún.");
        } else {
            for (String registro : historial) {
                System.out.println(registro);
            }
        }
        System.out.println("===================================");
    }

    static class ExchangeResponse {
        Map<String, Double> conversion_rates;
    }
}