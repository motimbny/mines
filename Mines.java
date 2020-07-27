package mines;

import java.util.Random;

public class Mines 
{
	@SuppressWarnings("unused")
	private int height,width,numMines;
	private boolean showAll;
	private place[][] game;   
	public Mines(int height, int width, int numMines)
	{
		Random rand = new Random();          //making a random variable to random place of mines
		int i,j,m;
		this.height=height;
		this.width=width;
		game=new place[height][width];    //setting new game of place[][]
		for(i=0;i<height;i++)
			for(j=0;j<width;j++)
				game[i][j]=new place();    //inihilaizing game[i][j] place
		this.numMines=numMines;
		this.showAll=false;
		m=numMines;
		while(m>0)                        //adding number of mines to random location
		{
			i=rand.nextInt(height);   
			j=rand.nextInt(width);
			if(!game[i][j].bomb)          //checking if random location is not a mine yet
			{
				game[i][j].bomb=true;
				m--;
				updatemine(i,j);            //updating all places near mine that they have a mine near them
			}
		}	
	}
	public boolean addMine(int i, int j)
	{
		if(!game[i][j].bomb)                   //checking if there is not a mine yet
		{
			game[i][j].bomb=true;
			//add bomb +1 to all near blocks
			updatemine(i, j);
			return true;
		}
		return false;
	}
	public boolean open(int i, int j) 
	{
            if(!game[i][j].open)              //if place is not open yet
            {
            	if(game[i][j].bomb)              //if its a mine then return false
            	{
            		return false;
            	}
            	game[i][j].open=true;               //setting place to be open
	            if(game[i][j].numine==0)           //recurs going on all 8 neighbors
		            {
	            	if(i-1>=0 && !game[i-1][j].bomb)
	    				open(i-1,j);
	    			if(j-1>=0&& !game[i][j-1].bomb)
	    				open(i,j-1);
	    			if(i+1<height && !game[i+1][j].bomb)
	    				open(i+1,j);
	    		     if(j+1<width && !game[i][j+1].bomb)
		    			open(i,j+1);
	    		     if(j-1>=0 && i-1>=0&& !game[i-1][j-1].bomb)
		    			open(i-1,j-1);
	    		     if(i+1<height && j+1<width&& !game[i+1][j+1].bomb)
		    			open(i+1,j+1);
	    		     if(i-1>=0 && j+1<width&&!game[i-1][j+1].bomb)
		    			open(i-1,j+1);
	    		     if(i+1<height && j-1>=0 && !game[i+1][j-1].bomb)
		    			open(i+1,j-1);
		            }
	            return true;
            }
            return true;
	}
	public void toggleFlag(int x, int y) 
	{
		if(game[x][y].flag)                       //checking if place is a flag
			game[x][y].flag=false;
		else
			game[x][y].flag=true;
	}
	public boolean isDone()
	{
		int i,j;
		for(i=0;i<height;i++)                        //checking if all places that are not mines are open
			for(j=0;j<width;j++)
				if(!game[i][j].bomb && !game[i][j].open)    //if one place is not mine and close then return false
					return false;
		return true;                                     //else return true
	}
	public String get(int i, int j)
	{
		return game[i][j].toString();
	}
	public void setShowAll(boolean showAll) 
	{
		this.showAll=showAll;                           //setting show all flag to true or false
	}
	public String toString()
	{
		String s="";
		int i,j;
		for(i=0;i<height;i++)
		{
			for(j=0;j<width;j++)
				s+=get(i,j);
			s+="\n";
		}
		return s;
	}
	private void updatemine(int i, int j)               //updating place number of mines near it
	{
		if(i-1>=0 && !game[i-1][j].bomb)
			game[i-1][j].numine++;
		if(j-1>=0&& !game[i][j-1].bomb)
			game[i][j-1].numine++;
		if(i+1<height && !game[i+1][j].bomb)
			game[i+1][j].numine++;
	     if(j+1<width && !game[i][j+1].bomb)
	    	 game[i][j+1].numine++;
	     if(j-1>=0 && i-1>=0&& !game[i-1][j-1].bomb)
	    	 game[i-1][j-1].numine++;
	     if(i+1<height && j+1<width&& !game[i+1][j+1].bomb)
	    	 game[i+1][j+1].numine++;
	     if(i-1>=0 && j+1<width&&!game[i-1][j+1].bomb)
	    	 game[i-1][j+1].numine++;
	     if(i+1<height && j-1>=0 && !game[i+1][j-1].bomb)
	    	 game[i+1][j-1].numine++;
	}
	private class place                          //private class of mine
	{
		protected boolean open,flag,bomb;
		private int numine;
		public place()
		{
			open=false;
			flag=false;
			bomb=false;
			numine=0;
		}
		public String toString()
		{
			if(!open && !showAll)
			{
				if(flag)
				    return "F";
				else
				    return ".";
			}
			else
			{
				if(bomb)
				return "X";
				if(numine==0)
				   return " ";
				else
				   return ""+numine;
			}
		}
	}
}
