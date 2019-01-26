package escritorArquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import leitorArquivo.CoordGeodesica;

public class EscritorArquivoGEODUtils {

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

    public static void escreverCoordenadasGeodesicas(List<CoordGeodesica> coordenadasGeodesicas, String caminhoArquivo) {
        try {
            // CRIAR OS OBJETOS RESPONSÁVEIS POR ESCREVER EM ARQUIVO
            FileWriter arq = new FileWriter(caminhoArquivo);
            PrintWriter gravarArq = new PrintWriter(arq);
            
            // LIMPA O ARQUIVO
            gravarArq.printf("");
            
            // ESCREVE NA PRIMEIRA LINHA DO ARQUIVO AS COLUNAS
            gravarArq.printf("ID     Latitude            Longitude          Altitude");
            
            // ESCREVE AS COORDENADAS EM CADA LINHA DO ARQUIVO
            for(CoordGeodesica coordenada: coordenadasGeodesicas) {
                gravarArq.printf(System.lineSeparator() + coordenada.id + "      " + coordenada.latitude + "        " + coordenada.longitude + "       " + coordenada.altitude);
            }
            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
