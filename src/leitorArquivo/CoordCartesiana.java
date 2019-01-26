package leitorArquivo;

import java.util.Vector;

public class CoordCartesiana {

	public String id;
	public String x;
	public String y;
	public String z;
        
        public CoordCartesiana(){
        }
        
        public CoordCartesiana(Vector vector){
            this.id = (String) vector.get(0);
            this.x = (String) vector.get(1);
            this.y = (String) vector.get(2);
            this.z = (String) vector.get(3);
        }
            
       	
}
