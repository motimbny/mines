package mines;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class MyController {
    
	private int h,w,m;
	private Mines ga;
	public GridPane gp;
	private boolean checkStat;
	 @FXML
	protected Pane allPane;
    @FXML
    private Button rest;

    @FXML
    private TextField heighbox;

    @FXML
    private TextField widthbox;

    @FXML
    private TextField minesbox;

    @FXML
    private StackPane gamevisual;
    @FXML
    private void newgame(MouseEvent event)              //setting a new game when pressed
    {
        h=Integer.parseInt(heighbox.getText());
        m=Integer.parseInt(minesbox.getText());
        w=Integer.parseInt(widthbox.getText());
        ga=new Mines(h,w,m);                               //making a new mine game
        checkStat=false;                                  //making losing flag to false
        makeGridPane();                                   //making a new grid 
        gamevisual.getChildren().add(gp);                   //adding grid to stack pane 
    }
    
    private void changeText()                         //changing all buttons text as game settings
    {
        for(int i=0;i<h;i++)  //updating all buttons test 
    	 for(int j=0;j<w;j++)
    	        	{
    	        	   for (Node btn : gp.getChildren()) 
    	        		  if (GridPane.getRowIndex(btn).intValue() == j && GridPane.getColumnIndex(btn).intValue() == i)
    						 ((Labeled) btn).setText(ga.get(i, j));
    	        	}
    	if(checkStat==true)   //checking if lost
        {
        	 gamevisual.getChildren().clear();
        	 Label label=new Label("Try next time :)");
        	 label.setFont((new Font("Arial", 30)));
			 gamevisual.getChildren().add(label);
	    }
		if(ga.isDone())    //checking if won
    	{
		     gamevisual.getChildren().clear();
		     Label label=new Label("OMG!!!You won!!!");
        	 label.setFont((new Font("Arial", 30)));
		     gamevisual.getChildren().add(label);
    	}
    }
	private void makeGridPane()                 //making a new grind
   	 {
		     gamevisual.getChildren().clear();
		     gp=new GridPane();
             for(int i=0;i<h;i++)
   	        	for(int j=0;j<w;j++)
   	        	{
   	        		 Button button = makeButton(i,j);	//adding a new button to grind
   	        		 gp.add(button,i,j);
   	        	}
   	  }
	private Button makeButton(int i,int j)
   	{
  		 Button button = new Button(ga.get(i, j));
  		 button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);             //setting button to max val of hieght and width
  		 button.setStyle("-fx-font-size: 20px;");                             //setting button font
  		 button.setOnMouseClicked(new EventHandler<MouseEvent>()             //making a new mouse event
  		 {
			@Override
			public void handle(MouseEvent event) 
			{
				if(event.getButton().equals(MouseButton.PRIMARY))           //if left key is clicked on mouse
				{
				   if(ga.open(i, j)==true)                            //if its not a mine then update all buttons text
				   {
					   changeText();               
				   }
				   else                                            //if place is a mine then setting show all to true and change lose flag to true
				   {
                       ga.setShowAll(true);    
					   changeText();
					   checkStat=true;
				   }
				}
				if(event.getButton().equals(MouseButton.SECONDARY))     //if right key is clicked then changing place to flag and also button text 
				{
					ga.toggleFlag(i, j);
					button.setText(ga.get(i, j));
				}  
			}
  		 });  
  		 return button;
   	}

}
