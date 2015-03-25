package ie.dit;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;

//Assignment 3 Try codes
public class Main extends PApplet
{
	Minim minim; //for microphone
	AudioInput in;
	
	float sampleRate = 44100;
	float frameSize = 2048;
	
	float x = 100;   // x location of square
	float y = 0.0f;     // y location of square
	
	double speed = 0.0;   // speed of square
	
	float gravity = 1;  
	float rectW = 50;
	float rectH = 30;
	float rectX = width/2 + 10;
	float rectY = 170;
	float playerW = 30;
	float playerH = 30;
	
	public void setup()
	{
		size(2048,500);
		
		minim = new Minim(this);
		in = minim.getLineIn(Minim.MONO,(int)frameSize, sampleRate,16);
		
	}
	
	public void draw()
	{
		background(0);
		stroke(255);
		float circW = 50;
		float circH = 50;
		
		for(int i = 0; i < in.bufferSize(); i++)
		{
			float halfH = height/2;
			float signal = in.left.get(i) * 500;
			
			line(i, halfH, i, halfH- signal);
			
			if(y < halfH - signal + playerH && halfH- signal > x)
			  {
			    speed = speed * -1;
			    
			  }
			text("speed is " + speed,10,50);
		}
		
		ellipse(width/2, height/2, circW, circH);
		
		// Display the square
		  fill(175);
		  stroke(0);
		  //rectMode(CENTER);
		  rect(x,y,playerW,playerH);
		  
		  if(keyPressed && key == 'a')
		  {
		    x -= 5;
		  }
		  if(keyPressed && key == 'd')
		  {
		    x += 5; 
		  }
		  
		  if(keyPressed && key == ' ')
		  {
			  if(speed>0)
			  {
			   speed = speed * -1.0;
			  }
		  }
			  
		  if(y < 0)
		  {
			  speed = 0.0;
			  y = 1;
		  }
		  
		  // Add speed to location.
		  y = y + (float)speed;
		  
		  // Add gravity to speed.
		  speed = speed + gravity;
		  
		  // If square reaches the bottom
		  // Reverse speed
		  if (y > height) {
		    // Multiplying by -0.95 instead of -1 slows the square down each time it bounces (by decreasing speed).  
		    // This is known as a "dampening" effect and is a more realistic simulation of the real world (without it, a ball would bounce forever).
		    speed = speed * -1;  
		  }
		 
		  if( x > width || x < 0 )
		  {
		    x = -2;
		    
		  }
		  
		  rect(rectX,rectY,rectW,rectH);
		  
		  if(y >= rectY-(1.5*playerH) && x >= rectX-1 && x <= rectX+playerW+50 )
		  {
		    speed = speed * -1;
		  }
		  
	}
}//end of class
