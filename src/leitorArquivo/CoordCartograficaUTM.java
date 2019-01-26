package leitorArquivo;

import java.util.Vector;

public class CoordCartograficaUTM {
    
      public String id;
      public String norteUTM;
      public String lesteUTM;
      public String mc;
      public String hemisferio;
      
      
      public CoordCartograficaUTM(){
    }

    public CoordCartograficaUTM(Vector valoresLinhaAtual) {
        this.id = (String) valoresLinhaAtual.get(0);
        this.norteUTM = (String) valoresLinhaAtual.get(1);
        this.lesteUTM = (String) valoresLinhaAtual.get(2);//To change body of generated methods, choose Tools | Templates.
        this.mc = (String) valoresLinhaAtual.get(3); 
        this.hemisferio = (String) valoresLinhaAtual.get(4); 
    }

    @Override
    public String toString() {
        return String.format("id: %s, norteUTM: %s, lesteUTM: %s, mc: %s, hemisferio: %s" , id, norteUTM, lesteUTM, mc, hemisferio);
    }
    
    
   
}
