package leitorArquivo;

import java.util.Vector;


public class CoordGeodesica {

    public String id;
    public String latitude;
    public String longitude;
    public String altitude;

    public CoordGeodesica(){
    }

    public CoordGeodesica(Vector valoresLinhaAtual) {
        this.id = (String) valoresLinhaAtual.get(0);
        this.latitude = (String) valoresLinhaAtual.get(1);
        this.longitude = (String) valoresLinhaAtual.get(2);
        this.altitude = (String) valoresLinhaAtual.get(3);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return String.format("id: %s, latitude: %s, longitude: %s, altitude: %s", id, latitude, longitude, altitude);
    }
    
    
	
}
