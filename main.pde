Simulation simulation;
ObstacleAPI api = new ObstacleAPI();

void setup() {
    size(800, 600);
    simulation = new Simulation();
    simulation.addParticles(50);
    ArrayList<Obstacle> obstacles = api.getObstacles(); 
    System.out.println(obstacles.size());
    for (Obstacle obstacle : obstacles) { 
        simulation.addObstacle(obstacle); }
}

void draw() {
    ColorTheme theme = simulation.getCurrentTheme();
    background(theme.backgroundColor.getRGB()); 

    // draw particles
    Particle[] particles = simulation.getParticles();
    int numParticles = simulation.getNumParticles();

    for (int i = 0; i < numParticles; i++) {
        Particle p = particles[i];
        p.update(0.1);

        // handle collision
        ObstacleNode obstacleNode = simulation.getObstacleHead();
        while (obstacleNode != null) {
            Obstacle obstacle = obstacleNode.getObstacle();
            p.handleCollision(obstacle);
            obstacleNode = obstacleNode.getNext();
        }

        fill(theme.particleColor.getRGB()); 
        ellipse((float)(p.getX() * width), height - (float)(p.getY() * height), (float)(p.getRadius() * width * 2), (float)(p.getRadius() * height * 2));
    }

    ObstacleNode obstacleNode = simulation.getObstacleHead();
    while (obstacleNode != null) {
        Obstacle obstacle = obstacleNode.getObstacle();
        fill(theme.obstacleColor.getRGB());
        ellipse((float)(obstacle.getX() * width), height - (float)(obstacle.getY() * height), (float)(obstacle.getRadius() * width * 2), (float)(obstacle.getRadius() * height * 2));
        obstacleNode = obstacleNode.getNext();
    }
}

void keyPressed() { 
    if (key == '+') { 
        simulation.addParticles(50); } 
    else if (key == '-') { 
        simulation.removeParticles(50); } 
    else if (key == '1') { 
        simulation.setColorTheme(ColorTheme.LIGHT); }
    else if (key == '2') { 
        simulation.setColorTheme(ColorTheme.DARK); } 
    else if (key == '3') { 
        simulation.setColorTheme(ColorTheme.VIBRANT); } 
    else if (key == '4') { 
        simulation.setColorTheme(ColorTheme.CONTRAST); }}

void mousePressed() {
    double simX = (double)mouseX / width;
    double simY = 1.0 - (double)mouseY / height;

    if (mouseButton == LEFT) {
        //add
        String id = "obstacle_" + (simulation.getObstacleCount() + 1);
        Obstacle obstacle = new Obstacle(id, simX, simY, 0.05);
        simulation.addObstacle(obstacle);
        api.addObstacle(obstacle);
    } else if (mouseButton == RIGHT) {
        //remove
        ObstacleNode current = simulation.getObstacleHead();
        while (current != null) {
            Obstacle obstacle = current.getObstacle();
            double dx = simX - obstacle.getX();
            double dy = simY - obstacle.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance <= obstacle.getRadius()) {
                simulation.removeObstacle(obstacle);
                api.deleteObstacle(obstacle.getId());
                break;
            }
            current = current.getNext();
        }
    }
}
