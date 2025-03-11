/**
Simulation class manages particles and obstacles
 */

public class Simulation {
    private Particle[] particles;
    private int numParticles;

    private ObstacleNode obstacleHead;
    private int numObstacles;
    private ColorTheme currentTheme;

    /**
    construct a Simulation with default settings
     */
    public Simulation() {
        particles = new Particle[100]; // initialize capacity
        numParticles = 0;
        obstacleHead = null;

        numObstacles = 0;

        this.currentTheme = ColorTheme.LIGHT;
    }

    /**
    * getter setter ColorTheme
    @return the current color theme
     */

    public ColorTheme getCurrentTheme() {
        return this.currentTheme; }

    /**
    @param theme: The new color theme
     */
    public void setColorTheme(ColorTheme theme) { 
        this.currentTheme = theme; }

    /**
     * Getter particles
      * @return an array of particles
     */
    public Particle[] getParticles() {
        return particles;
    }

    /**
     * Getter the number of particle
     * @return the number of particles
     */
    public int getNumParticles() {
        return numParticles;
    }

    /**
     * add particles. if array doesn't have enough space, replace with a new one double the size
     * @param n the number of particles to add
     */
    public void addParticles(int n) {
        for (int i = 0; i < n; i++) {
            if (numParticles >= particles.length) {
                // double array soze
                Particle[] newParticles = new Particle[particles.length * 2];
                // copy existing particles to new array
                for (int j = 0; j < particles.length; j++) {
                    newParticles[j] = particles[j];
                }
                particles = newParticles;
            }
            // new random particle
            Particle p = new Particle(Math.random(), Math.random() + 0.5, 0.02);
            p.setVelocity(Math.random() * 2 - 1, Math.random() * 2 - 1);
            particles[numParticles] = p;
            numParticles++;
        }
    }

    /**
     * remove particles, not array size
     * @param n the number of particles to remove
     */
    public void removeParticles(int n) {
        numParticles -= n;
        if (numParticles < 0) {
            numParticles = 0;
        }
        // set the removed particles to null
        for (int i = numParticles; i < numParticles + n && i < particles.length; i++) {
            particles[i] = null;
        }
    }

    // obstacle methods

    /**
     * adds an obstacle to the simulation
     * @param obstacle obstacle to add
     */
    public void addObstacle(Obstacle obstacle) {
        ObstacleNode newNode = new ObstacleNode(obstacle);
        if (obstacleHead == null) {
            obstacleHead = newNode;
        } else {
            ObstacleNode current = obstacleHead;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        numObstacles++;
    }

    /**
     * Removes an obstacle from the simulation.
     * @param obstacle The obstacle to remove.
     */
    public void removeObstacle(Obstacle obstacle) {
        ObstacleNode current = obstacleHead;
        ObstacleNode previous = null;

        while (current != null) {
            if (current.getObstacle() == obstacle) {
                if (previous == null) {
                    obstacleHead = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                numObstacles--;
                break;
            }
            previous = current;
            current = current.getNext();
        }
    }

    /**
     * gets the head of the obstacle linked list
     * @return the head ObstacleNode
     */
    public ObstacleNode getObstacleHead() {
        return obstacleHead;
    }

    /**
     * gets number of osbtacles
     * @return number of obstacles
     */
    public int getObstacleCount(){
        return numObstacles;
    }
}


/* 
public class Simulation {
    public static void main(String[] args) {
        //initialize particld
        Particle particle = new Particle(0.0, 1.0, 0.05);
        // particle.setVelocity(1.0, 0.0);

        //Initialize obstacle
        Obstacle obstacle = new Obstacle(-0.02, 0.45, 0.1);

        for (int i = 0; i < 100; i++) {
            particle.handleCollision(obstacle);
            particle.update(0.1);

            System.out.println(particle.getX() + ", " + particle.getY());
        }
    }
} 
*/
