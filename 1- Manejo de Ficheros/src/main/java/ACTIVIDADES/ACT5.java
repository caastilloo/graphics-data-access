package ACTIVIDADES;

import java.io.File;

public class ACT5 {

    static void main(String[] args) {

        String ruta = "src/main/resources";

        System.out.println("=== Archivos .txt ===");
        listarArchivos(ruta);

        System.out.println("\n=== Archivos .pdf ===");
        listarArchivos(ruta, ".pdf");

        System.out.println("\n=== Archivos .jpg ===");
        listarArchivos(ruta, ".jpg");
    }

    public static void listarArchivos(String rutaCarpeta) {
        listarArchivos(rutaCarpeta, ".txt");
    }

    public static void listarArchivos(String rutaCarpeta, String extension) {

        File carpeta = new File(rutaCarpeta);

        if (!carpeta.isDirectory()) {
            System.err.println("La ruta no es una carpeta: " + rutaCarpeta);
            return;
        }

        File[] archivos = carpeta.listFiles();

        if (archivos != null && archivos.length > 0) {
            for (File f : archivos) {
                if (f.isFile() && f.getName().endsWith(extension)) {
                    System.out.println(f.getName() + " - " + f.length() + "bytes");
                }
            }
        } else {
            System.out.println("No se ha encontrado ningún archivo.");
        }
    }
}
