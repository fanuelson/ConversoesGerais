package leitorArquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LeitorArquivoGEOUtilsNovo {

    public static List<CoordGeodesica> lerCoordenadasGeodesicas(String caminhoArquivo) {
        List<CoordGeodesica> coordenadasGeodesicas = new LinkedList<CoordGeodesica>();
        File file = new File(caminhoArquivo);
        coordenadasGeodesicas.addAll(lerCoordenadasGeodesicas(file));
        return coordenadasGeodesicas;
    }
    
    public static List<CoordGeodesica> lerCoordenadasGeodesicas(File arquivo) {
        List<CoordGeodesica> coordenadasGeodesicas = new LinkedList<CoordGeodesica>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(arquivo);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] valores = linha.split("\\s+");

                CoordGeodesica coordenada = new CoordGeodesica();
                coordenada.id = valores[0];
                coordenada.latitude = valores[1];
                coordenada.longitude = valores[2];
                coordenada.altitude = valores[3];
                coordenadasGeodesicas.add(coordenada);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return coordenadasGeodesicas;
    }
}
