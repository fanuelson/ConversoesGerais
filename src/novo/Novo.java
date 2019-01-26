/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import janelas.JanelaPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;


public class Novo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       JanelaPrincipal janela = new JanelaPrincipal();
       janela.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
