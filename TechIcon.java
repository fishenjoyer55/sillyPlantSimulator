import javafx.scene.image.*;
import javafx.scene.effect.ColorAdjust;

public class TechIcon {
    int x;
    int y;
    ImageView display;
    String name;
    String subtitle;
    String actual;

    public TechIcon(int x, int y, String url, Runnable effect, String name, String subtitle, String actual) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.subtitle = subtitle;
        this.actual = actual;
        this(x, y, 60, 60, url);
        display.setOnMouseClicked(event -> {
            effect.run();
        });
    }

    public TechIcon(int x, int y, int width, int height, String url) {
        this.x = x;
        this.y = y;
        display = new ImageView(new Image(getClass().getResource("/plantSprites/" + url + ".png").toExternalForm()));
        display.setFitWidth(width);
        display.setFitHeight(height);
        display.setTranslateX(x);
        display.setTranslateY(y);
    }

    public TechIcon(int x, int y, String url) {
        this(x, y, 60, 60, url);
    }

}
