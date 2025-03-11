/**
Obstacle class builds an obstacle
 */

public class Obstacle {
    private String id;
    private double x;
    private double y;
    private double radius;

    /**
    Constructs an Obstacle with position and radius
     @param x x-coordinate
     @param y y-coordinate
     @param r radius
     */
    public Obstacle(String id, double x, double y, double r) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = r;
    }

    /**
     getters & setters
     */
    public String getId() {return id;}
    public double getX() {return x;}
    public double getY() {return y;}
    public double getRadius() {return radius;}

    public void setX(double xPos) {this.x  = xPos;}
    public void setY(double yPos) {this.y  = yPos;}
    public void setRadius(double r) {
        if (r > 0) {
            this.radius  = r;}
        else {
            System.out.println("Radius must be positive.");
        }
    }

    /**
     * check if the obstacle collides with a particle
     * @param p particle to check collision with
     * @return true if the obstacle collides with the particles, false otherwise
     */
    public boolean checkCollision(Particle p) {
        double distance = Vector2DMath.magnitude(x - p.getX(),y-p.getY());
        return distance <= radius + p.getRadius();
    }

    /**
     * handles collision with a particle
     * @param p particle to collide with
     * @return normal vector after collision
     */
    public double[] collide(Particle p) {
        double[] normal = Vector2DMath.normal(p.getX() - this.x, p.getY() - this.y);
        double distance = radius + p.getRadius();
        p.setX(x + normal[0]*distance);
        p.setY(y + normal[1] * distance);
        return normal;
    }

}
