package leitorArquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LeitorArquivoCARTUtils {

    public static List<CoordCartesiana> lerCoordenadasCartesianas(String caminhoArquivo) {
        List<CoordCartesiana> coordenadasCartesianas = new LinkedList<CoordCartesiana>();
        File file = new File(caminhoArquivo);
        coordenadasCartesianas.addAll(lerCoordenadasCartesianas(file));
        return coordenadasCartesianas;
    }
    
    public static List<CoordCartesiana> lerCoordenadasCartesianas(File arquivo) {
        List<CoordCartesiana> coordenadasCartesianas = new LinkedList<CoordCartesiana>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(arquivo);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] valores = linha.split("\\s+");

                CoordCartesiana coordenada = new CoordCartesiana();
                coordenada.id = valores[0];
                coordenada.x = valores[1];
                coordenada.y = valores[2];
                coordenada.z = valores[3];
                coordenadasCartesianas.add(coordenada);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return coordenadasCartesianas;
    }
}
