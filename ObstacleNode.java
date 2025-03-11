/**
 ObstacleNode builds a node in a linked list of obstacles
 */

public class ObstacleNode {
    private Obstacle obstacle;
    private ObstacleNode next;

    /**
     * Constructs an Obstacle with specified obstacle
     * @param obstacle the obstacle to be stored in this node
     */
    public ObstacleNode(Obstacle obstacle) {
        this.obstacle = obstacle;
        this.next = null;
    }

    /**
     *getter & setter
     * @return The obstacle stored in this node
      */
    public Obstacle getObstacle() {
        return obstacle;
    }

    /**
     *
     * @return The next ObstacleNode
     */
    public ObstacleNode getNext() {
        return next;
    }

    /**
     *
     * @param next the next ObstacleNode
     */
    public void setNext(ObstacleNode next) {
        this.next = next;
    }
}
