import java.util.ArrayList;
import javafx.scene.image.*;

public class Hole {

    int row;
    int col;
    Image spriteImage;
    ImageView sprite;
    boolean isPlantable = true;
    boolean isVisible = false;
    ArrayList<String> neighbouring = new ArrayList<String>();

    public Hole(int row, int col) {
        this.row = row;
        this.col = col;
        spriteImage = new Image(getClass().getResource("/plantSprites/plot.png").toExternalForm());
        sprite = new ImageView(spriteImage);
        sprite.setTranslateX(-920 + 90 * row);
        sprite.setTranslateY(-740 + 80 * col);
        sprite.setFitWidth(80);
        sprite.setFitHeight(80);
    }
}