import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.effect.ColorAdjust;

public class Icon {
    ImageView iconBox;
    Text iconText;
    StackPane display;

    public Icon(int x, int y, int width, int height, String url) {
        this(x, y, width, height, url, "");
    }
    public Icon(int x, int y, int width, int height, String url, String words) {
        iconBox = new ImageView(new Image(getClass().getResource("/plantSprites/" + url + ".png").toExternalForm()));
        iconBox.setFitWidth(width);
        iconBox.setFitHeight(height);
        iconText = new Text(words);
        display = new StackPane(iconBox, iconText);
        display.setTranslateX(x);
        display.setTranslateY(y);
        display.setMaxSize(width, height);
        //display.setStyle("-fx-border-color: red");
    }

}
