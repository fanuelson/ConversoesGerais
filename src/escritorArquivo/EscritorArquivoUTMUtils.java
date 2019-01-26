
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import leitorArquivo.CoordCartograficaUTM;

public class EscritorArquivoUTMUtils {

//    public static void main(String[] args) throws IOException {
//
//        CoordGeodesica c1 = new CoordGeodesica();
//        c1.id = "1";
//        c1.latitude = "11";
//        c1.longitude = "22";
//        c1.altitude = "33";
//        
//        List<CoordGeodesica> coordenadas = new ArrayList<>();
//        coordenadas.add(c1);
//        
//        escreverCoordenadasCartesianas(coordenadas, "/Users/taynara/Desktop/Teste2.txt");
//    }

    public static void escreverCoordenadasCartograficasUTM(List<CoordCartograficaUTM> coordenadasCartograficasUTM, String caminhoArquivo) {
        try {
            // CRIAR OS OBJETOS RESPONSÁVEIS POR ESCREVER EM ARQUIVO
            FileWriter arq = new FileWriter(caminhoArquivo);
            PrintWriter gravarArq = new PrintWriter(arq);
            
            // LIMPA O ARQUIVO
            gravarArq.printf("");
            
            // ESCREVE NA PRIMEIRA LINHA DO ARQUIVO AS COLUNAS
            gravarArq.printf("ID            Norte (UTM)            Leste (UTM)            mc");
            
            // ESCREVE AS COORDENADAS EM CADA LINHA DO ARQUIVO
            for(CoordCartograficaUTM coordenada: coordenadasCartograficasUTM) {
                gravarArq.printf(System.lineSeparator() + coordenada.id + "      " + coordenada.norteUTM + "        " + coordenada.lesteUTM + "       " + coordenada.mc);
            }
            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
