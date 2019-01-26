package leitorArquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LeitorArquivoUTMUtils {

    public static List<CoordCartograficaUTM> lerCoordenadasUTM(String caminhoArquivo) {
        List<CoordCartograficaUTM> coordenadasUTM = new LinkedList<CoordCartograficaUTM>();
        File file = new File(caminhoArquivo);
        coordenadasUTM.addAll(lerCoordenadasUTM(file));
        return coordenadasUTM;
    }
    
    public static List<CoordCartograficaUTM> lerCoordenadasUTM(File arquivo) {
        List<CoordCartograficaUTM> coordenadasUTMs = new LinkedList<CoordCartograficaUTM>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(arquivo);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] valores = linha.split("\\s+");

                CoordCartograficaUTM coordenada = new CoordCartograficaUTM();
                coordenada.id = valores[0];
                coordenada.norteUTM = valores[1];
                coordenada.lesteUTM = valores[2];
                coordenada.mc = valores[3];
                coordenada.hemisferio = valores[4];
                coordenadasUTMs.add(coordenada);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return coordenadasUTMs;
    }
}
