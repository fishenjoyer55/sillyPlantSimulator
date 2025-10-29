import java.util.Map;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.effect.ColorAdjust;

public class Plant {

    int row;
    int col;
    Icon plantIcon;
    ImageView sprite;
    String species;
    int age = 0;
    boolean isMature = false;
    boolean isElder = false;
    boolean willDie = false;
    boolean isImmortal;
    ColorAdjust darken = new ColorAdjust();

    public Plant(int row, int col, String species) {
        this.row = row;
        this.col = col;
        this.species = species;
        plantIcon = new Icon(-920 + 90 * row, -740 + 80 * col, 80, 80, species + "1");
        sprite = plantIcon.iconBox;
        darken.setBrightness(-0.4);
        if (species.equals("angelcress") || species.equals("all-purposeapotheolia")) {
            isImmortal = true;
        }
    }

    public void tick() {
        age += 1;
        if (age == Main.stats.get(species)[2]) {
            isMature = true;
            sprite.setImage(loadImage(species + "2"));
        } else if (age == Main.stats.get(species)[3]) {
            isElder = true;
            sprite.setImage(loadImage(species + "3"));
            if (species.equals("zenrice") || species.equals("ionlemon") || species.equals("forget-me-so")) {
                sprite.setFitWidth(90);
                sprite.setFitHeight(90);
            } else {
                sprite.setFitWidth(110);
                sprite.setFitHeight(110);
            }
        } else if (age == Main.stats.get(species)[4] - 1 && !isImmortal) {
            willDie = true;
            sprite.setEffect(darken);
        }
        //System.out.println("this " + species + " received a tick");
    }

    public Image loadImage(String name) {
        return new Image(getClass().getResource("/plantSprites/" + name + ".png").toExternalForm());
    }
}
