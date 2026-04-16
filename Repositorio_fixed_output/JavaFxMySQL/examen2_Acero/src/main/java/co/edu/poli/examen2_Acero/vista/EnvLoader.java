package co.edu.poli.examen2_Acero.vista;

import java.io.*;

public class EnvLoader {
    public static void load() throws IOException {
        File envFile = new File(".env");
        if (!envFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    System.setProperty(parts[0].trim(), parts[1].trim());
                }
            }
        }
    }
}