import java.util.Vector;

/**
the Particle class builds particles in the simulation
 */
public class Particle {
    private double x;
    private double y;
    private double radius;
    private double velocityX;
    private double velocityY;
    private double gravity;

    /**
    construct a Particle with position and radius
    @param xPos x-coordinate
    @param yPos y-coordinate
    @param r radius
     */
    public Particle(double xPos, double yPos, double r) {
        //same name must have this.
        this.x = xPos;
        this.y = yPos;
        this.radius = r;
        
        double velocityX = 0.0;
        double velocityY = 0.0;
        gravity = -1.0;
    }

    //getters & setters

    /**
     * gets x coordinate
     * @return x-coordiate
     */
    public double getX() {return x;}
    /**
    get y coordinate
     @return y-coordinate
     */
    public double getY() {return y;}

    /**
     * get radius
     * @return  the radius
     */
    public double getRadius() {return radius;}

    //sorry it's too much xD i hope you understand
    public double getVelocityX() {return velocityX;}
    public double getVelocityY() {return velocityY;}
    public double getGravity() {return gravity;}
    
    public void setX(double xPos) {this.x  = xPos;}
    public void setY(double yPos) {this.y  = yPos;}
    public void setRadius(double r) {
        if (r > 0) {
            this.radius  = r;
        }
        else {
            System.out.println("Radius must be positive.");
        }
    }

    public void setVelocityX(double velocityX) {this.velocityX  = velocityX;}
    public void setVelocityY(double velocityY) {this.velocityY  = velocityY;}
    public void setGravity(double g) {this.gravity  = g;}

    /**
     * updates position & velocity of particle
     * @param dt time step for teh update
     */
    public void update(double dt) {
        //velocity = velocity + acceleration*dt
        velocityY = velocityY + gravity*dt;

        // position = position + velocity*dt
        x = x + velocityX * dt;
        y = y + velocityY * dt;  
        
        //if the particle hits the floor (when y = radius -> particle's edge hits the floor)
        if (y <= radius) {
            y = radius;

            //bounce 
            velocityY = -1.0 * velocityY*0.6;
        }
    }

    /**
     * handle collision with obstacles
     * @param obstacle of which particles collide with
     */
    public void handleCollision(Obstacle obstacle) {
        if (obstacle.checkCollision(this)) {
                double[] normal = obstacle.collide(this);

                //bounce (reflect) 
                //if the velocity is too small to bounce, bounce it anywa
                if (Vector2DMath.magnitude(velocityX, velocityY)<0.2) {
                    velocityX = normal[0] * 0.5;
                    velocityY = normal[1] * 0.5;    
                }
                else {
                    //otherwise just reflect
                    double[] reflect = Vector2DMath.reflect(normal, velocityX, velocityY);
                    velocityX = reflect[0] * 0.6;
                    velocityY = reflect[1] * 0.6;
                }
        }
    }

    /**
     * sets velocity of particle
     * @param vX new x-velocity
     * @param vY new y-velocity
     */
    public void setVelocity(double vX, double vY) {
        velocityX = vX;
        velocityY = vY;
    }
}