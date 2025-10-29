import javafx.scene.image.*;
import javafx.scene.layout.StackPane;

public class Equation {
    
    int secrecy;
    StackPane display;
    ImageView hidden;

    public Equation(String species1, String species2, String product, int secrecy) {
        this.secrecy = secrecy;
        ImageView image1 = new ImageView(loadImage(species1 + "2"));
        image1.setFitWidth(80);
        image1.setFitHeight(80);
        image1.setTranslateX(-200);
        ImageView cross1 = new ImageView(loadImage("crossproduct"));
        cross1.setFitWidth(60);
        cross1.setFitHeight(60);
        cross1.setTranslateX(-100);
        ImageView image2 = new ImageView(loadImage(species2 + "2"));
        image2.setFitWidth(80);
        image2.setFitHeight(80);
        ImageView equals = new ImageView(loadImage("equals"));
        equals.setFitWidth(60);
        equals.setFitHeight(60);
        equals.setTranslateX(100);
        ImageView image5 = new ImageView(loadImage(product + "1"));
        image5.setFitWidth(80);
        image5.setFitHeight(80);
        image5.setTranslateX(200);
        display = new StackPane(image1, cross1, image2, equals, image5);
        display.setMaxSize(700, 100);
        this.hide();
    }

    public Equation(String species1, String product, int secrecy) {
        String url;
        if (species1.equals("hole")) {
            url = "plot";
        } else {
            url = species1 + "2";
        }
        ImageView image1 = new ImageView(loadImage(url));
        image1.setFitWidth(80);
        image1.setFitHeight(80);
        image1.setTranslateX(-100);
        ImageView arrow = new ImageView(loadImage("arrow"));
        arrow.setFitWidth(60);
        arrow.setFitHeight(60);
        ImageView image5 = new ImageView(loadImage(product + "1"));
        image5.setFitWidth(80);
        image5.setFitHeight(80);
        image5.setTranslateX(100);
        display = new StackPane(image1, arrow, image5);
        display.setMaxSize(700, 100);
        this.hide();
    }

    public Equation(String species1, String species2, String species3, String product, int secrecy) {
        this.secrecy = secrecy;
        ImageView image1 = new ImageView(loadImage(species1 + "2"));
        image1.setFitWidth(80);
        image1.setFitHeight(80);
        image1.setTranslateX(-270);
        ImageView cross1 = new ImageView(loadImage("crossproduct"));
        cross1.setFitWidth(60);
        cross1.setFitHeight(60);
        cross1.setTranslateX(-180);
        ImageView image2 = new ImageView(loadImage(species2 + "2"));
        image2.setFitWidth(80);
        image2.setFitHeight(80);
        image2.setTranslateX(-90);
        ImageView cross2 = new ImageView(loadImage("crossproduct"));
        cross2.setFitWidth(60);
        cross2.setFitHeight(60);
        ImageView image3 = new ImageView(loadImage(species3 + "2"));
        image3.setFitWidth(80);
        image3.setFitHeight(80);
        image3.setTranslateX(90);
        ImageView equals = new ImageView(loadImage("equals"));
        equals.setFitWidth(60);
        equals.setFitHeight(60);
        equals.setTranslateX(180);
        ImageView image5 = new ImageView(loadImage(product + "1"));
        image5.setFitWidth(80);
        image5.setFitHeight(80);
        image5.setTranslateX(270);
        display = new StackPane(image1, cross1, image2, cross2, image3, equals, image5);
        display.setMaxSize(700, 100);
        this.hide();
    }

    public void hide() {
        hidden = new ImageView(loadImage("hidden" + secrecy));
        display.getChildren().add(hidden);
        hidden.setFitWidth(600);
        hidden.setFitHeight(90);
        hidden.setOnMouseClicked(event -> {
            if (Main.thought > Math.pow(4, secrecy)) {
                display.getChildren().remove(hidden);
                Main.thought -= Math.pow(4, secrecy);
                Main.thoughtIcon.iconText.setText("Thought: " + Main.thought);
            } else {
                System.out.println("bro can't think");
            }
        });
    }

    public Image loadImage(String name) {
        return new Image(getClass().getResource("/plantSprites/" + name + ".png").toExternalForm());
    }
}