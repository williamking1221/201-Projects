

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 * @author William Joshua King
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Accessor for the x-position
	 * @return the value of this object's x-position
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 * Accessor for the y-position
	 * @return the value of this object's y-position
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Accessor for the y-velocity
	 * @return the value of this object's y-velocity
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 * Accessor for the mass
	 * @return the mass of the object
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 * Accessof for the file name
	 * @return the file name of the object
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		return Math.sqrt((myXPos - b.myXPos) * (myXPos - b.myXPos) + (myYPos - b.myYPos) * (myYPos - b.myYPos));
	}

	public double calcForceExertedBy(CelestialBody b) {
		return (6.67e-11) * (myMass * b.myMass) / (calcDistance(b) * calcDistance(b));
	}

	public double calcForceExertedByX(CelestialBody b) {
		return calcForceExertedBy(b) * (b.myXPos - myXPos) / calcDistance(b);
	}
	public double calcForceExertedByY(CelestialBody b) {
		return calcForceExertedBy(b) * (b.myYPos - myYPos) / calcDistance(b);
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b: bodies) {
			if(! b.equals(this)) {
				sum+= this.calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b: bodies) {
			if(! b.equals(this)) {
				sum+= this.calcForceExertedByY(b);
			}
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double Ax = xforce / this.myMass;
		double Ay = yforce / this.myMass;
		double nvx = this.myXVel + Ax * deltaT;
		double nvy = this.myYVel + Ay * deltaT;
		double nx = this.myXPos + nvx * deltaT;
		double ny = this.myYPos + nvy * deltaT;
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy; 
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
