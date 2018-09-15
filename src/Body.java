
public class Body 
{
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
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
	
	public Body(Body b)
	{
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	public double getX()
	{
		return myXPos;
	}
	
	public double getY()
	{
		return myYPos;
	}
	
	public double getXVel()
	{
		return myXVel;
	}
	
	public double getYVel()
	{
		return myYVel;
	}
	
	public double getMass()
	{
		return myMass;
	}
	
	public String getName()
	{
		return myFileName;
	}

	public double calcDistance(Body b)
	{
		double bX = b.getX();
		double bY = b.getY();
		double dX = myXPos - bX;
		double dY = myYPos - bY;
		double dist = Math.sqrt((dX*dX) + (dY*dY));
		
		return dist;
	}
	
	public double calcForceExertedBy(Body b)
	{
		double r = this.calcDistance(b);
		double bMass = b.getMass();
		double G = 6.67*1e-11;
		double F = G*bMass*myMass/(r*r);
		
		return F;
			
	}
	
	public double calcForceExertedByX(Body b)
	{
		double F = this.calcForceExertedBy(b);
		double bX = b.getX();
		double dX = bX - myXPos;
		double dist = this.calcDistance(b);
		double Fx = F * dX / dist;
		
		return Fx;
	}
	
	public double calcForceExertedByY(Body b)
	{
		double F = this.calcForceExertedBy(b);
		double bY = b.getY();
		double dY = bY - myYPos;
		double dist = this.calcDistance(b);
		double Fy = F * dY / dist;
		
		return Fy;
	}
	
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
	
	public void draw()
	{
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
}