
package escritorArquivo;
    
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
 
class java{
 
  public static void main(String[] args) throws IOException {
    Scanner ler = new Scanner(System.in);
    int i, n;
 
    System.out.printf("Informe o número para a tabuada:\n");
    n = ler.nextInt();
 
    FileWriter arq = new FileWriter("/Users/taynara/Desktop/Teste.txt");
    PrintWriter gravarArq = new PrintWriter(arq);
 
    gravarArq.printf("+--Resultado--+%n");
    for (i=1; i<=10; i++) {
      gravarArq.printf("| %2d X %d = %2d |%n", i, n, (i*n));
    }
    gravarArq.printf("+-------------+%n");
 
    arq.close();
 
    System.out.printf("\nTabuada do %d foi gravada com sucesso em \"d:\\tabuada.txt\".\n", n);
  }
 
}

//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class java {
//    
//    public static void main (String[] args) {
//        
//        Path caminho = Paths.get("C:/Users/Taynara/Desktop/Teste.txt");
//        String texto = "Olaaa " ;
//        byte[] textoEmByte = texto.getBytes();
//        try {
//        Files.write(caminho, textoEmByte);
//    }catch(Exception erro){
//        
//    }
//    }
//    }