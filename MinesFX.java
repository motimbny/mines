package mines;

import java.io.IOException;

import mines.MyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MinesFX extends Application 
{
    MyController controller;
	 @Override
	 public void start(Stage primaryStage) 
	 {
		    Pane pane;
	        try 
	        {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("ms.fxml"));
	            pane =  loader.load();
	            controller = loader.getController();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            return;
	        }
	        Scene s = new Scene(pane,800,600);
	        primaryStage.setScene(s);
	        primaryStage.show();
	 }	  
	 
	 public static void main(String[] args) 
	 {
	        launch(args);
	 }
}

