import java.awt.Color;

/**
ColorTheme class defines color themes for the simulation
 */
public class ColorTheme {
    public Color backgroundColor;
    public Color particleColor;
    public Color obstacleColor;

    /**
    Construct ColorTheme with 4 presets
    @param backgroundColor: the background color
    @param particleColor: the particle color
    @param obstacleColor: the obstacle color
     */

    public ColorTheme(Color backgroundColor,Color particleColor, Color obstacleColor) {
        this.backgroundColor = backgroundColor;
        this.particleColor = particleColor;
        this.obstacleColor = obstacleColor;
    }

    public static final ColorTheme LIGHT = new ColorTheme(Color.WHITE, Color.BLACK, Color.GRAY);
    public static final ColorTheme DARK = new ColorTheme(Color.BLACK, Color.WHITE, Color.LIGHT_GRAY);
    public static final ColorTheme VIBRANT = new ColorTheme(Color.ORANGE, Color.RED, Color.BLUE);
    public static final ColorTheme CONTRAST = new ColorTheme(Color.BLACK, Color.YELLOW, Color.CYAN);
}