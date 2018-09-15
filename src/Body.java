
public class Body 
{
	// instance variables for Body
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	
	/**
	 * first constructor; makes a new Body from inputs
	 * @param xPos is assigned to myXPos
	 * @param yPos is assigned to myYPos
	 * @param xVel is assigned to myXVel
	 * @param yVel is assigned to myYVel
	 * @param mass is assigned to myMass
	 * @param fName is assigned to myFileName
	 */
	public Body(double xPos, double yPos, double xVel, 
			double yVel, double mass, String fName)
	{
		myXPos = xPos;
		myYPos = yPos;
		myXVel = xVel;
		myYVel = yVel;
		myMass = mass;
		myFileName = fName;
	}
	
	/**
	 * second constructor; makes a new Body using another body b
	 * @param b; the new Body is created with instance variables
	 * equal to the instance variables of b
	 */
	public Body(Body b)
	{
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	/**
	 * getter method; for x position
	 * @return returns myXPos
	 */
	public double getX()
	{
		return myXPos;
	}
	
	/**
	 * getter method; for y position
	 * @return returns myYPos
	 */
	public double getY()
	{
		return myYPos;
	}
	
	/**
	 * getter method; for x velocity
	 * @return returns myXVel
	 */
	public double getXVel()
	{
		return myXVel;
	}
	
	/**
	 * getter method; for y velocity
	 * @return returns myYVel
	 */
	public double getYVel()
	{
		return myYVel;
	}
	
	/**
	 * getter method; for mass of Body
	 * @return returns myMass
	 */
	public double getMass()
	{
		return myMass;
	}
	
	/**
	 * getter method; for file name
	 * @return returns myFileName
	 */
	public String getName()
	{
		return myFileName;
	}

	/**
	 * calc distance between this Body and another Body
	 * @param b; the Body being compared to this Body
	 * @return returns distance between this Body and b
	 */
	public double calcDistance(Body b)
	{
		double bX = b.getX();
		double bY = b.getY();
		double dX = myXPos - bX;
		double dY = myYPos - bY;
		double dist = Math.sqrt((dX*dX) + (dY*dY));
		
		return dist;
	}
	
	/**
	 * calc force exerted on this Body by another Body
	 * @param b; a Body exerting force on this Body 
	 * @return returns force exerted on this Body by b
	 */
	public double calcForceExertedBy(Body b)
	{
		double r = this.calcDistance(b);
		double bMass = b.getMass();
		double G = 6.67*1e-11;
		double F = G*bMass*myMass/(r*r);
		
		return F;
			
	}
	
	/**
	 * calc X component of force exerted on 
	 * this Body by another Body;
	 * @param b; a Body exerting force on this Body 
	 * @return returns X component of the force
	 */
	public double calcForceExertedByX(Body b)
	{
		double F = this.calcForceExertedBy(b);
		double bX = b.getX();
		double dX = bX - myXPos;
		double dist = this.calcDistance(b);
		double Fx = F * dX / dist;
		
		return Fx;
	}
	
	/**
	 * calc Y component of force exerted on 
	 * this Body by another Body;
	 * @param b; a Body exerting force on this Body 
	 * @return returns Y component of the force
	 */
	public double calcForceExertedByY(Body b)
	{
		double F = this.calcForceExertedBy(b);
		double bY = b.getY();
		double dY = bY - myYPos;
		double dist = this.calcDistance(b);
		double Fy = F * dY / dist;
		
		return Fy;
	}
	
	/**
	 * calc net X component of force exerted on
	 * this Body by array of other Bodies
	 * @param bodies; an array of bodies exerting a force
	 * on this Body
	 * @return returns the sum of all X forces acting on
	 * this Body
	 */
	public double calcNetForceExertedByX(Body[] bodies)
	{
		double netXForce = 0;
		for(Body b : bodies)
		{
			if (! b.equals(this))
			{
				netXForce += this.calcForceExertedByX(b);
			}
		}
		return netXForce;
	}
	
	/**
	 * calc net Y component of force exerted on
	 * this Body by array of other Bodies
	 * @param bodies; an array of bodies exerting a force
	 * on this Body
	 * @return returns the sum of all Y forces acting on
	 * this Body
	 */
	public double calcNetForceExertedByY(Body[] bodies)
	{
		double netYForce = 0;
		for(Body b : bodies)
		{
			if (! b.equals(this))
			{
				netYForce += this.calcForceExertedByY(b);
			}
		}
		return netYForce;
	}
	
	
	/**
	 * update position and velocity of this Body from
	// a given change in time, X force, and Y force
	 * @param deltaT; change in time for the simulation
	 * @param xForce; net X force on this Body
	 * @param yForce; net Y force on this Body
	 */
	public void update(double deltaT, double xForce,
			double yForce)
	{
		double ax = xForce/myMass;
		double ay = yForce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	
	/**
	 * draws this Body
	 */
	public void draw()
	{
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
}
