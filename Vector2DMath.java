/**
 * Vector2DMath -> methods for 2D vector operations
 */
public class Vector2DMath {
    /**
     *
     * @param x x-coordinate of the vector
     * @param y y-coordinate of the vector
     * @return magnitude of the vector
     */
    public static double magnitude(double x, double y) {
        return Math.sqrt(x*x + y*y);
  }

    /**
     *
     * @param x x-coordinate of the vector
     * @param y y-coordinate of the vector
     * @return double array contains normalized vector components
     */
    public static double[] normal(double x, double y) {
        double length = magnitude(x, y);
        return new double[] {x * 1.0/length, y * 1.0/length};
  }

    /**
     *
     * @param N normal vector to reflect over
     * @param x x-coordinate of the vector
     * @param y y-coor of the vector
     * @return double array contains reflected vector components
     */
  public static double[] reflect(double[] N, double x, double y) {
      double rx = x - 2.0 * (N[0]*x + N[1]*y)*N[0];
      double ry = y - 2.0 * (N[0]*x + N[1]*y)*N[1];
      return new double[] {rx, ry};
  }
}
