import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.lang.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javax.swing.text.Position;

public class Main extends Application {

    public String[] seeds = {"sillygrass", "wackywheat", "crazycorn", "zenrice", "banalweed",
    "oddmold", "evenspore", "myelichen", "aloevera", "dukeroot",
    "cortecactus", "ionlemon", "necrose", "forget-me-so", "sachembeet",
    "gammaviolet", "krisprmoss", "red-tonguedtulip", "heaven-defyingfruit", "maharajatater",
    "angelcress", "oblivionlily", "jabberpoppy", "tianhuangyam", "all-purposeapotheolia"};
    //jlquvxy
    //csmao
    public int currentSeed;
    public Plant[][] plants = new Plant[7][7];
    public int tianhuangsacrifice;
    public static Map<String, double[]> stats = Map.ofEntries(
        //description id, cost, maturation, elderation, death, current number, atp/year, thought/year, friendliness
        Map.entry("sillygrass", new double[]{0, 10, 1, -1, 4, 0, 5, 0, 1}),
        Map.entry("wackywheat",  new double[]{1, 20, 2, -1, 6, 0, 10, 0, 1}),
        Map.entry("crazycorn", new double[]{2, 65, 2, -1, 7, 0, 25, 0, 3}),
        Map.entry("zenrice", new double[]{3, 125, 3, 7, 12, 0, 15, 0, 3}),
        Map.entry("banalweed", new double[]{4, 10, 1, -1, 4, 0, -10, 0, -3}),
        Map.entry("oddmold", new double[]{5, 15, 1, -1, 5, 0, -15, 0, -15}),
        Map.entry("evenspore", new double[]{6, 15, 1, -1, 5, 0, 15, 0, -3}),
        Map.entry("myelichen", new double[]{7, 150, 2, -1, 7, 0, 0, 5, -3}),
        Map.entry("aloevera", new double[]{8, 60, 2, -1, 8, 0, 0, 0, 15}),
        Map.entry("dukeroot", new double[]{9, 350, 4, -1, 12, 0, 100, 0, -20}),
        Map.entry("cortecactus", new double[]{10, 260, 3, -1, 8, 0, 0, 10, -10}),
        Map.entry("ionlemon", new double[]{11, 250, 3, 5, 7, 0, 0, 0, 5}),
        Map.entry("necrose", new double[]{12, 400, 3, -1, 9, 0, 0, 0, -25}),
        Map.entry("forget-me-so", new double[]{13, 170, 2, 5, 9, 0, 0, 0, 10}),
        Map.entry("sachembeet", new double[]{14, 900, 3, 6, 14, 0, 160, 8, 0, 25}),
        Map.entry("gammaviolet", new double[]{15, 450, 3, -1, 7, 0, 0, 0, -7}),
        Map.entry("krisprmoss", new double[]{16, 600, 2, 5, 14, 0, 0, 12, 25}),
        Map.entry("red-tonguedtulip", new double[]{17, 500, 3, 7, 10, 0, 0, 0, -40}),
        Map.entry("heaven-defyingfruit", new double[]{18, 1000, 2, 5, 7, 0, 0, 0, 10}),
        Map.entry("maharajatater", new double[]{19, 2500, 4, 7, 15, 0, 400, 0, -35}),
        Map.entry("angelcress", new double[]{20, 3400, 3, 7, -1, 0, 0, 20, 100}),
        Map.entry("oblivionlily", new double[]{21, 3600, 3, 7, 18, 0, 0, 0, -10}),
        Map.entry("jabberpoppy", new double[]{22, 2400, 2, 5, 16, 0, 0, 10, -120}),
        Map.entry("tianhuangyam", new double[]{23, 8000, 5, 10, 25, 0, 0, 50, -50}),
        Map.entry("all-purposeapotheolia", new double[]{24, 10000, 2, 6, -1, 0, 0, 0, 300})
    );

    public Map<String, String[]> description = Map.ofEntries(
            //name, taxonomy, tags, flavour
        Map.entry("sillygrass", new String[]{"Sillygrass", "⋅ Graminaea sillia ⋅", ">> Producer ", "Generates a bit of ATP.", "A ubiquitous grass. Touching it on\noccassion may provide health benefits. \n\nMatures in 1 year, lives for 4 years. \n+5 ATP/year"}),
        Map.entry("wackywheat", new String[]{"Wackywheat", "⋅ Triticum durum ⋅", ">> Producer", "Generates a bit more ATP.", "A common wheat. Its hardiness lets\nit weather winters unharmed. \n\nMatures in 2 years, lives for 6 years. \n+10 ATP/year"}),
        Map.entry("crazycorn", new String[]{"Crazycorn", "⋅ Maize manicaea ⋅", ">> Producer", "Generates a bunch of ATP.", "Strangely nutritious, this corn is the\nfoundation of many stock pharmaceuticals. \n\nMatures in 2 years, lives for 7 years. \n+25 ATP/year"}),
        Map.entry("zenrice", new String[]{"Zenrice", "⋅ Oryza kongzi ⋅", ">> Producer", "Generates more ATP the longer it lives.", "A rare, mint-scented rice. Often added to herbal teas.\nIn stronger doses, may function as a sedative. \n\nMatures in 3 years, elderescent in 7 years, lives for 12 years. \n+15 ATP/year", ""}),
        Map.entry("banalweed", new String[]{"Banalweed", "⋅ Graminaea banalia ⋅", "> Parasite", "Steals a bit of ATP.", "A quickly-spreading weed bringing\nrot and mold to your garden. \n\nMatures in 1 year, lives for 4 years. \n-10 ATP/year"}),
        Map.entry("oddmold", new String[]{"Oddmold", "⋅ Rhizopus onemodtwo ⋅", ">> Parasite", "Steals a bit more ATP. Alternates with Evenspore.", "A foul-smelling mold whose spores poison the mind.\nSpreads as the fair Evenspore. \n\nMatures in 1 year, lives for 5 years. \n-15 ATP/year"}),
        Map.entry("evenspore", new String[]{"Evenspore", "⋅ Cladosporium zeromodtwo ⋅", ">> Producer", "Generates a bit more ATP. Alternates with Oddmold.", "A fair-scented spore sprouting alien stalks.\nSpreads as the foul Oddmold. \n\nMatures in 1 year, lives for 5 years. \n+15 ATP/year"}),
        Map.entry("myelichen", new String[]{"Myelichen", "⋅ Letharia axon ⋅", ">> Sage", "Primitive neurons allow for thought.", "Rubbery fungi-flesh insulates this plant's\nsensitive nerves. Capable of primitive thought. \n\nMatures in 2 years, lives for 7 years. \n+5 thought/year"}),
        Map.entry("aloevera", new String[]{"Aloevera", "⋅ Aloe vera ⋅", ">> Protector", "May extend the lifespan of nearby plants.", "A generous plant whose secretions\nattract visitors and heal its neighbours. \n\nMatures in 2 years, lives for 8 years. \n50% chance to save neighbouring plants from natural death."}),
        Map.entry("dukeroot", new String[]{"Dukeroot", "⋅ Raphanus herzog ⋅", ">> Aristocrat ⋅ Producer", "Generates a huge amount of ATP.", "Lord of all lesser plants, this long-lived root is native to western Europe. \n\nMatures in 4 years, lives for 12 years. \n+100 ATP/year"}),
        Map.entry("cortecactus", new String[]{"Cortecactus", "⋅ Cactaceae cerebrum ⋅", ">> Sage", "Capable of producing a lot of thought.", "A peculiar squishy cactus. WHen you press on it,\nit wobbles like jelly and makes your head hurt. \n\nMatures in 3 years, lives for 8 years. \n+10 ATP/year"}),
        Map.entry("ionlemon", new String[]{"Ionlemon", "⋅ Citrus limion ⋅", ">> Evolutionary", "Ionizing radiation slightly increases mutation rate.", "This plant's fruits are bleached phosphorescent\nand suspiciously crispy with ionizing radiation. \n\nMatures in 3 years, lives for 7 years. \nMutation rate +20%"}),
        Map.entry("necrose", new String[]{"Necrose", "⋅ Rosa mortis ⋅", ">> Esoteric ⋅ Parasite", "Reaps the energy of plants upon natural death.", "A cursed and wilted rose possessed\nby decomposing fungi. Empowered by decay. \n\nMatures in 3 years, lives for 9 years. \nNaturally-dying plants are reaped for 50% of their ATP."}),
        Map.entry("forget-me-so", new String[]{"Forget-me-so", "⋅ Myosotis amnestica ⋅", ">> Esoteric", "Slightly reduces blessing decay.", "Blessings, as bestowed by some visitors, are\nmemories caught in this flower's little dreamcatcher petals. \n\nMatures in 2 years, elderescent in 5 years, lives for 9 years. \nBlessing decay reduced 30%."}),
        Map.entry("sachembeet", new String[]{"Sachembeet", "⋅ Beta ogimaa ⋅", ">> Aristocrat ⋅ Sage ⋅ Producer", "Generates thought and an enormous amount of ATP.", "A wizened root found anywhere\n from the Great Lakes to the Yucatan Peninsula. \n\nMatures in 3 years, elderescent in 6 years, lives for 14 years. \n+8 thought/year, +160 ATP/year"}),
        Map.entry("gammaviolet", new String[]{"Gammaviolet", "⋅ Viola radionuclide ⋅", ">> Evolutionary", "Gamma radiation massively increases mutation rate.", "Suspended within its petals is a microquasar\nbursting with volatile gamma rays. \n\nMatures in 3 years, lives for 7 years. Mutation rate +70%"}),
        Map.entry("krisprmoss", new String[]{"Krisprmoss", "⋅ Setaphyta kluiyu ⋅", ">> Sage ⋅ Evolutionary", "Focuses mutation effect on rare plants.", "An extraordinarily pretty and intelligent sea moss\nwoven from strands of DNA . \n\nMatures in 2 years, elderescent in 5 years, lives for 14 years. \nMutation rate effect for rare plants raised 1 degree."}),
        Map.entry("red-tonguedtulip", new String[]{"Red-tongued Tulip", "⋅ Liliceae vorae ⋅", ">> Aggresor", "Attracts and then takes bites out of visitors for ATP.", "A ferocious flower with a long and wet tongue-like stamen.\nToo small to hurt a fly, but will nip at their heels nontheless. \n\nMatures in 3 years, elderescent in 7 years, lives for 10 years. \n+1000 ATP per visitor."}),
        Map.entry("heaven-defyingfruit", new String[]{"Heaven-defying Fruit", "⋅ Malus satanus", ">> Esoteric", "Harvest when fully grown to grant immortality to another plant.", "Finger-like stems offer to your lips\na taste of immortality. Will you take it? \n\nMatures in 2 years, fully grown in 5 years, lives for 7 years. \nHarvest when fully grown to grant the next plant you click immortality."}),
        Map.entry("maharajatater", new String[]{"Maharajatater", "⋅ Solanum ashoka ⋅", ">> Aristocrat ⋅ Producer", "Sacrifices plants to generate an unimaginable amount of ATP.", "Spanning across the Indian subcontinent, this roots's\ninexorable grip crushes its neighbours into pure energy. \n\nMatures in 4 years, elderescent in 7 years, lives for 15 years. \nRite of elderescence: sacrifices neighbouring plants for ATP. +400 ATP/year"}),
        Map.entry("angelcress", new String[]{"Angelcress", "⋅ Cruciferae imayn ⋅", ">> Divine ⋅ Protector ⋅ Sage", "Saves neighbouring plants and attracts friendly visitors.", "Soft and saintlike, adorned with feather-like leaves\nand glowing halos. This plant represents all that is right in the world. \n\nMatures in 3 years, elderescent in 7 years, lives forever. \n90% chance to save neighbouring plants from natural death."}),
        Map.entry("oblivionlily", new String[]{"Oblivionlily", "⋅ Lycoris mnere ⋅", ">> Esoteric ⋅ Sage-slayer", "Decay halted entirely. Thought halted entirely.", "In your periphery, its leaves contort, twisting in directions that\nnever existed. Maybe. You were never meant to remember. \n\nMatures in 3 years, elderescent in 7 years, lives for 18 years. \nBlessings will never decay."}),
        Map.entry("jabberpoppy", new String[]{"Jabberpoppy", "⋅ Papaveraceae borogoveae ⋅", ">> Aggressor ⋅ Sage", "The jaws that bite, the claws that catch! The frumious bandersnatch!", "'Twas brillig, and the slithy toves did gyre and gimble in the wabe.\nAll mimsy were the borogoves, and the mome wraths outgrabe. \n\nMatures in 2 years, elderescent in 5 years, lives for 10 years. \n+6666 ATP per visitor."}),
        Map.entry("tianhuangyam", new String[]{"Tianhuangyam","⋅ Dioscoreaceceae tianzi ⋅", ">> Divine ⋅ Aristocrat ⋅ Producer", "Through the sacrifice of plants, generates ATP to rival Heaven.", "From the sunken Yangtze riverbed to the Himalayas, this\ntranscendent yam cultivates its power from the souls of its subjects. \n\nMatures in 5 years, elderescent in 10 years, lives for 25 years. \nThe cost of plants sacrificed upon maturity shall be repaid yearly."}),
        Map.entry("all-purposeapotheolia", new String[]{"All-Purpose Apotheolia", "⋅ Asteroideae arcturus ⋅", ">> Divine", "Worldly creatures flock to witness this newborn sun shine.", "A flower like the sun itself. All sorts of\ncreatures shall be drawn in to feel its warmth. \n\nMatures in 2 years, elderescent in 6 years, lives forever. \nCertain rare and powerful creatures are far more likely to appear."})
        );

    public String[] hieroglyphics = {"𓋴𓇋*𓃭𓃭𓇌𓐍𓋴𓇋𓃭𓃭@@@𓇌𓋴𓇋𓃭𓃭𓇌𓎼𓂋##𓄿𓋴𓋴𓐍𓄿𓐍𓃀𓇋𓏏𓐍!\n!!!𓅱𓆑𓐍𓄿𓏏𓊪SIL𓐍𓅂LY𓆯𓅂𓂋𓇌𓐍𓏏𓅲𓂋𓈖",
    "𓅃𓉔𓅂𓈖𓐍𓏏𓉔𓅂𓐍𓅃𓉔𓅂@𓄿\n𓏏𓐍𓇋𓋴𓐍𓅃𓄿𓎢^^𓈎𓇌!!!!𓐍𓅓𓄿𓇌𓃀𓅂\n𓐍𓇋𓏏𓐍𓅓𓅂𓄿𓈖𓋴𓐍𓃭𓇋𓈎𓅂𓏞𓐍𓇋𓏏𓐍𓅃𓄿𓎢𓈎𓋴𓐍𓇌𓅱𓅲","𓎢𓂋𓄿𓊃𓇌?𓇋𓐍𓅃𓄿𓋴𓎢\n𓂋𓄿𓊃𓇌𓅱𓈖𓎢𓅂𓏞@𓏏𓉔𓅂\n𓇌𓊪𓅲𓏏𓅓𓅂𓇋𓈖𓄿𓂋@@@𓅱𓅱\n𓅓𓏞𓄿𓂋𓅲𓃀𓃀𓅂𓂋𓂋𓅱𓅱𓅓",
    "𓈎𓈖𓈖𓈖𓈎@𓅱𓈖##𓎼𓊃𓇋\n*𓅓𓅱𓂋𓅂𓃭𓇋𓈎𓅂@@@@𓆓𓅱𓉔&&𓈖𓎢\n𓅱𓈖𓆑𓅲𓎢𓇋𓅲𓋴𓉔𓄿𓉔𓄿", "𓏏𓉔𓅱𓋴𓅂𓅃𓉔𓅱𓈎𓈖𓅱𓅃𓋴𓅱𓋴𓄿\n𓎢𓄿𓈖𓇌𓅱𓅲𓋴𓅂𓅂𓃀𓇌𓏏𓉔𓅂𓂧\n𓄿𓅃𓈖'𓋴𓅂𓄿𓂋𓃭𓇌𓃭𓇋𓎼𓉔𓏏"
    };

    public Hole[][] holes = new Hole[7][7]; 
    public int holeRowLevel = 3;
    public int holeColLevel = 3;

    public StackPane descIcon;
    public Text descName;
    public Text descSpecies;
    public Text descActual;

    public Icon costIcon;

    public static int atp = 100;
    public static int thought = 0;
    public static Icon atpIcon;
    public static double goodwill;
    public static Icon thoughtIcon;
    public ImageView goodwillIndicator;

    public int year = 0;

    public double atpMultiplier = 1;
    public double thoughtMultiplier = 1;
    public double mutationMultiplier = 1;
    public double mutationRate = 1;

    public String currentMenu = "garden";
    public String action = "harvest"; //actions: harvest, immortalize
    public TechIcon[][] seedIcons = new TechIcon[5][5];
    public TechIcon[][] theoreticalSeedIcons = new TechIcon[5][5];
    public boolean[] seedsObtained = new boolean[25];

    public Icon endYearIcon;

    public StackPane root;
    public ScrollPane gardenPane;
    public StackPane garden;

    public int mersquirrelStory = 0;
    public boolean crystalysis = false;
    public boolean symbolysis = false;
    public int catnap = 0;
    public ImageView sleepycat;

    @Override
    public void start(Stage primaryStage) {

        root = new StackPane();
        Scene scene = new Scene(root, 1200, 600);

        garden = new StackPane();
        ImageView gardenBackground = new ImageView(loadImage("quickgardenbackground"));
        gardenBackground.setFitWidth(2400);
        gardenBackground.setFitHeight(1600);
        garden.getChildren().add(gardenBackground);

        gardenPane = new ScrollPane(garden);
        gardenPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gardenPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gardenPane.setMaxSize(2400, 1600);
        gardenPane.setFitToWidth(true);
        root.getChildren().add(gardenPane);

        //CSS stuff
        Font.loadFont(getClass().getResource("/plantSprites/NotoSansEgyptianHieroglyphs-Regular.ttf").toExternalForm(), 24);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        garden.getStyleClass().add("garden");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Silly Plant Simulator");
        primaryStage.show();

        atpIcon = new Icon(390, -250, 120, 120, "bigbutton", "ATP: " + atp);
        atpIcon.iconText.getStyleClass().add("textMajor");
        thoughtIcon = new Icon(520, -250, 120, 120, "bigbutton", "Thought: " + thought);
        thoughtIcon.iconText.getStyleClass().add("textMajor");
        ImageView goodwillBar = new ImageView(loadImage("goodwillmeter"));
        goodwillBar.setFitWidth(250);
        goodwillBar.setFitHeight(30);
        goodwillBar.setTranslateX(455);
        goodwillBar.setTranslateY(-185);
        goodwillIndicator = new ImageView(loadImage("bigbutton"));
        goodwillIndicator.setFitWidth(10);
        goodwillIndicator.setFitHeight(40);
        goodwillIndicator.setTranslateX(455);
        goodwillIndicator.setTranslateY(-185);
        root.getChildren().addAll(atpIcon.display, thoughtIcon.display, goodwillBar, goodwillIndicator);

        sleepycat = new ImageView(loadImage("sleepycat"));
        sleepycat.setFitWidth(390);
        sleepycat.setFitHeight(260);
        sleepycat.setTranslateX(-630);
        sleepycat.setTranslateY(-500);
        garden.getChildren().add(sleepycat);
        sleepycat.setVisible(false);
        sleepycat.setOnMouseEntered(event -> {
            descIcon.setVisible(true);
            descIcon.setTranslateX(-630);
            descIcon.setTranslateY(-290);
            descName.setText("You wouldn't disturb a sleeping cat.");
            descSpecies.setText("⋅ Felis kerchae ⋅");
            descActual.setText("A very very sleepy cat... her nap will last another " + catnap + " year(s).");
            descIcon.toFront();
        });
        sleepycat.setOnMouseExited(event -> {
            descIcon.setVisible(false);
        });

        for (int i = 0; i < 49; i++) {
            holes[i/7][i%7] = new Hole(i/7, i%7);
        }
        for (int i = 3 - holeRowLevel; i <= 3 + holeRowLevel; i++) {
            for (int j = 3 - holeColLevel; j <= 3 + holeColLevel; j++) {
                Hole thisHole = holes[i][j];
                thisHole.isVisible = true;
                garden.getChildren().add(thisHole.sprite);
                thisHole.sprite.setOnMouseClicked(event -> {
                    if (atp >= stats.get(seeds[currentSeed])[1] && thisHole.isPlantable && seedsObtained[currentSeed]) {
                        makePlant(seeds[currentSeed], thisHole.row, thisHole.col);
                        atp -= stats.get(seeds[currentSeed])[1];
                        atpIcon.iconText.setText("ATP: " + Integer.toString(atp));
                    } else {
                        System.out.println("you're broke buddy / that's full buddy / you haven't discovered that buddy");
                    }
                });
            }
        }
        descName = new Text("");
        descSpecies = new Text("");
        descActual = new Text("");
        descIcon = new StackPane(descName, descSpecies, descActual);
        descActual.setTextAlignment(TextAlignment.LEFT);
        descIcon.setPadding(new Insets(15, 50, 15, 50));
        Background descBackground = new Background(new BackgroundImage(
            loadImage("descbutton"), 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT,
            null, 
            new BackgroundSize(1, 1, true, true, false, false)));
        descIcon.setBackground(descBackground);
        descName.getStyleClass().add("textMajor");
        descSpecies.getStyleClass().add("textMini");
        descActual.getStyleClass().add("textMinor");
        descIcon.setVisible(false);
        descIcon.setMaxSize(250, 140);
        descName.setTranslateY(-30);
        descSpecies.setTranslateY(-5);
        descActual.setTranslateX(-25);
        descActual.setTranslateY(20);
        garden.getChildren().add(descIcon);
        costIcon = new Icon(0, 0, 120, 90, "bigbutton", "3125");
        costIcon.iconText.getStyleClass().add("textMajor");
        costIcon.display.setMaxSize(120, 5);
        root.getChildren().add(costIcon.display);
        costIcon.display.setVisible(false);

        Icon seedSelector = new Icon(450, 0, 280, 300, "seedbutton", "Seeds");
        seedSelector.iconText.setTranslateY(-100);
        seedSelector.iconText.getStyleClass().add("textMajor");
        root.getChildren().addAll(seedSelector.display);
        seedsObtained[0] = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int m = i;
                int n = j;
                String seedUrl;
                if (i == 0 & j == 0) {
                    seedUrl = seeds[5 * i + j] + "0";
                } else {
                    seedUrl = "seedbackground";
                }
                TechIcon seedIcon = new TechIcon(380 + 40 * j, -40 + 40 * i, 40, 40, seedUrl);
                seedIcons[i][j] = seedIcon;
                root.getChildren().add(seedIcon.display);
                seedIcon.display.setOnMouseEntered(event -> {
                    if (seedsObtained[5 * m + n]) {
                        costIcon.display.toFront();
                        costIcon.display.setVisible(true);
                        costIcon.display.setTranslateX(300 + 40 * n);
                        costIcon.display.setTranslateY(-40 + 40 * m);
                        costIcon.iconText.setText(Integer.toString((int)stats.get(seeds[5 * m + n])[1]) + " ATP");
                    }
                });
                seedIcon.display.setOnMouseExited(event -> {
                    costIcon.display.setVisible(false);
                });
                seedIcon.display.setOnMouseClicked(event -> {
                    currentSeed = 5 * m + n;
                    for (int k = 0; k < 25; k++) {
                        seedIcons[k / 5][k % 5].display.setFitWidth(40);
                        seedIcons[k / 5][k % 5].display.setFitHeight(40);
                    }
                    seedIcon.display.setFitWidth(50);
                    seedIcon.display.setFitHeight(50);
                });
            }
        }

        Icon yearIcon = new Icon(380, 250, 70, 140, "bigbutton", "0");
        yearIcon.iconText.getStyleClass().add("textMajor");
        root.getChildren().add(yearIcon.display);
        endYearIcon = new Icon(500, 250, 160, 140, "bigbutton", "End year");
        endYearIcon.iconText.getStyleClass().add("textMajor");
        root.getChildren().add(endYearIcon.display);
        endYearIcon.display.setOnMouseClicked(event -> {
            year ++;
            thought ++;
            if (catnap == 1) {
                sleepycat.setVisible(false);
                for (int k = 0; k < 9; k++) {
                    holes[2 + k / 3][2 + k % 3].isPlantable = true;
                }
            }
            if (catnap > 0) {
                catnap -= 1;
            }
            yearIcon.iconText.setText(Integer.toString(year));
            System.out.println("New year: " + year);
            proposeVisitor();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    //plant part
                    Plant thisPlant = plants[i][j];
                    Hole thisHole = holes[i][j];
                    if (thisPlant != null) {
                    if (thisPlant.willDie) {
                        atp += stats.get("necrose")[5] * 0.5 * stats.get(thisPlant.species)[1];
                        //System.out.println("a " + thisPlant.species + " died, " + stats.get("necrose")[5] + " necroses generated " + stats.get("necrose")[5] * stats.get(thisPlant.species)[1] + " atp.");
                        double deathChance = 1;
                        if (thisHole.neighbouring.contains("aloevera")) {
                            deathChance = 0.5;
                        }
                        if (thisHole.neighbouring.contains("angelcress")) {
                            deathChance = 0.1;
                        }
                        if (Math.random() < deathChance) {
                            killPlant(i, j);
                        } else {
                            ColorAdjust brighten = new ColorAdjust();
                            brighten.setBrightness(0.4);
                            thisPlant.sprite.setEffect(new ColorAdjust(0, 0, 0.3, 0.1));
                        }
                    }
                    }
                    
                    thisPlant = plants[i][j];
                    if (thisPlant != null) {
                    thisPlant.tick();

                    //hole part
                    if (thisPlant.age == stats.get(thisPlant.species)[2]) { //maturity check
                        processMaturity(thisPlant, i, j);
                    }
                    }
                }
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (holes[i][j].isVisible && holes[i][j].isPlantable) {
                        proposeMutation(holes[i][j]);
                    }
                    if (plants[i][j] != null && plants[i][j].isElder) {
                        atp += stats.get(plants[i][j].species)[6];
                    }
                }
            }
            for (double[] value : stats.values()) {
                atp += atpMultiplier * value[5] * value[6];
                thought += thoughtMultiplier * value[5] * value[7];
                goodwill += value[5] * value[8];
            }
            atp += stats.get("tianhuangyam")[5] * tianhuangsacrifice * 0.5;

            mutationRate = mutationMultiplier * (1 + 0.2 * stats.get("ionlemon")[5] + 0.7 * stats.get("gammaviolet")[5] + 0.1 * stats.get("krisprmoss")[5]);
            //System.out.println("current mutation rate: " + mutationRate);

            double decay = 0.5;
            if (stats.get("oblivionlily")[5] != 0) {
                decay = 0;
            } else {
                decay *= Math.pow(0.7, stats.get("forget-me-so")[5]);
            }
            if (atpMultiplier != 1) {
                atpMultiplier -= Math.signum(atpMultiplier - 1) * decay;
            }
            if (thoughtMultiplier > 1) {
                thoughtMultiplier -= Math.signum(thoughtMultiplier - 1) * decay;
            }
            if (mutationMultiplier > 1) {
                mutationMultiplier -= Math.signum(mutationMultiplier - 1) * decay;
            }
            updateMetrics();
        });

        TechIcon mutationsBackground = new TechIcon(0, 0, 700, 3000, "fish");
        StackPane mutationsScreen = new StackPane(mutationsBackground.display);
        ScrollPane mutationsPane = new ScrollPane(mutationsScreen);
        mutationsPane.setTranslateX(-50);
        mutationsPane.setMaxWidth(700);
        mutationsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mutationsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mutationsPane.setFitToWidth(true);
        root.getChildren().add(mutationsPane);
        mutationsPane.setVisible(false);

        TechIcon technologyBackground = new TechIcon(0, 0, 700, 1000, "technologybutton");
        StackPane technologyScreen = new StackPane(technologyBackground.display);
        ScrollPane technologyPane = new ScrollPane(technologyScreen);
        technologyPane.setTranslateX(-50);
        technologyPane.setMaxWidth(700);
        technologyPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        technologyPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        technologyPane.setFitToWidth(true);
        root.getChildren().add(technologyPane);
        technologyPane.setVisible(false);
        
        String[] menuTexts = {"Garden", "Mutations", "Technology"};
        Icon gardenTab = new Icon(-500, -200, 160, 140, "gardenbutton", "Garden");
        gardenTab.iconText.getStyleClass().add("textMajor");
        gardenTab.display.setOnMouseClicked(event -> {
            if (!currentMenu.equals("garden")) {
                currentMenu = "garden";
                System.out.println("Current menu: garden");
                endYearIcon.display.setDisable(false);
                mutationsPane.setVisible(false);
                garden.getChildren().add(descIcon);
                descIcon.setMaxSize(250, 140);
                descName.setTranslateY(-30);
                descSpecies.setTranslateY(-5);
                descActual.setTranslateX(-25);
                descActual.setTranslateY(20);
                technologyPane.setVisible(false);
            }
        });
        Icon mutationsTab = new Icon(-500, -100, 160, 140, "mutationsbutton", "Mutations");
        mutationsTab.iconText.getStyleClass().add("textMajor");
        mutationsTab.display.setOnMouseClicked(event -> {
            if (!currentMenu.equals("mutations")) {
                currentMenu = "mutations";
                System.out.println("Current menu: mutations");
                endYearIcon.display.setDisable(true);
                mutationsPane.setVisible(true);
                mutationsScreen.getChildren().add(descIcon);
                descIcon.setMaxSize(250, 200);
                descName.setTranslateY(-60);
                descSpecies.setTranslateY(-35);
                descActual.setTranslateX(0);
                descActual.setTranslateY(15);
                mutationsPane.toFront();
                technologyPane.setVisible(false);
            }
        });
        Icon technologyTab = new Icon(-500, 0, 160, 140, "technologybutton", "Technology");
        technologyTab.iconText.getStyleClass().add("textMajor");
        technologyTab.display.setOnMouseClicked(event -> {
            if (!currentMenu.equals("technology")) {
                currentMenu = "technology";
                System.out.println("Current menu: technology");
                endYearIcon.display.setDisable(true);
                mutationsPane.setVisible(false);
                technologyPane.setVisible(true);
                technologyScreen.getChildren().add(descIcon);
                descIcon.setMaxSize(250, 140);
                descName.setTranslateY(-30);
                descSpecies.setTranslateY(-5);
                descActual.setTranslateX(0);
                descActual.setTranslateY(20);
                technologyPane.toFront();
            }
        });
        root.getChildren().addAll(gardenTab.display, mutationsTab.display, technologyTab.display);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int m = i;
                int n = j;
                String seedUrl;
                if (i == 0 & j == 0) {
                    seedUrl = seeds[5 * i + j] + "0";
                } else {
                    seedUrl = "seedbackground";
                }
                TechIcon seedIcon = new TechIcon(-140 + 70 * j, -1400 + 70 * i, 70, 70, seedUrl);
                theoreticalSeedIcons[i][j] = seedIcon;
                mutationsScreen.getChildren().add(seedIcon.display);
                seedIcon.display.setOnMouseEntered(event -> {
                    descIcon.setVisible(true);
                    if (seedsObtained[5 * m + n]) {
                        descName.setText(description.get(seeds[5 * m + n])[0]);
                        descSpecies.setText(description.get(seeds[5 * m + n])[1]);
                        if (!symbolysis) {
                            descActual.setText(description.get(seeds[5 * m + n])[4]);
                        } else {
                            descActual.setText(hieroglyphics[(int)(Math.random() * 5)]);
                        }
                    } else {
                        descName.setText("???");
                        descSpecies.setText("???");
                        descActual.setText("You have not encountered this seed yet!");
                    }
                    descIcon.setTranslateX(seedIcon.x);
                    descIcon.setTranslateY(seedIcon.y + 150);
                    descIcon.toFront();
                });
                seedIcon.display.setOnMouseExited(event -> {
                    descIcon.setVisible(false);
                });
            }
        }
        ArrayList<Equation> equations = new ArrayList<>();
        equations.addAll(Arrays.asList(
            new Equation("sillygrass", "wackywheat", 0),
            new Equation("sillygrass", "wackywheat", "crazycorn", 1),
            new Equation("sillygrass", "crazycorn", "zenrice", 1),
            new Equation("hole", "banalweed", 1),
            new Equation("banalweed", "oddmold", 1),
            new Equation("oddmold", "evenspore", 1),
            new Equation("evenspore", "zenrice", "myelichen", 1),
            new Equation("zenrice", "wackywheat", "aloevera", 2),
            new Equation("sillygrass", "wackywheat", "banalweed", "dukeroot", 2),
            new Equation("myelichen", "cortecactus", 1),
            new Equation("crazycorn", "ionlemon", 2),
            new Equation("ionlemon", "banalweed", "necrose", 2),
            new Equation("cortecactus", "oddmold", "forget-me-so", 3),
            new Equation("dukeroot", "cortecactus", "sachembeet", 3),
            new Equation("ionlemon", "necrose", "gammaviolet", 3),
            new Equation("gammaviolet", "myelichen", "krisprmoss", 4),
            new Equation("necrose", "aloevera", "red-tonguedtulip", 3),
            new Equation("sachembeet", "krisprmoss", "aloevera", "heaven-defyingfruit", 4),
            new Equation("sachembeet", "necrose", "maharajatater", 4),
            new Equation("heaven-defyingfruit", "aloevera", "angelcress", 4),
            new Equation("krisprmoss", "forget-me-so", "oblivionlily", 5),
            new Equation("red-tonguedtulip", "gammaviolet", "cortecactus", "jabberpoppy", 4),
            new Equation("maharajatater", "heaven-defyingfruit", "tianhuangyam", 5),
            new Equation("angelcress", "oblivionlily", "all-purposeapotheolia", 5)
        ));
        for (int i = 0; i < equations.size(); i++) {
            Equation thisEquation = equations.get(i);
            thisEquation.display.setTranslateY(-1000 + 100 * i);
            mutationsScreen.getChildren().add(thisEquation.display);
        }

        ArrayList<TechIcon> techIcons = new ArrayList<>();
        techIcons.addAll(Arrays.asList(
            new TechIcon(-100, -400, "angelcress0", () -> {
                for (int i = 0; i < 25; i++) {
                    seedsObtained[i] = true;
                    seedIcons[i / 5][i % 5].display.setImage(loadImage(seeds[i] + "0"));
                    theoreticalSeedIcons[i / 5][i % 5].display.setImage(loadImage(seeds[i] + "0"));
                }
            }, "Fruit of knowledge", "Forbidden", "Instantly unlocks all seeds"),
            new TechIcon(0, -400, "angelcress0", () -> {
                atp += 100000;
                atpIcon.iconText.setText("ATP: " + atp);
            }, "Fruit of life", "Forbidden", "Gains 100000 ATP")
        ));
        ArrayList<Runnable> techActions = new ArrayList<>();
        for (int i = 0; i < techIcons.size(); i++) {
            TechIcon thisIcon = techIcons.get(i);
            technologyScreen.getChildren().add(thisIcon.display);
            thisIcon.display.setOnMouseEntered(event -> {
                descIcon.setVisible(true);
                descName.setText(thisIcon.name);
                descSpecies.setText(thisIcon.subtitle);
                if (!symbolysis) {
                    descActual.setText(thisIcon.actual);
                } else {
                    descActual.setText(hieroglyphics[(int)(Math.random() * 5)]);
                }
                descIcon.setTranslateX(thisIcon.x);
                descIcon.setTranslateY(thisIcon.y + 110);
            descIcon.toFront();
            });
            thisIcon.display.setOnMouseExited(event -> {
                descIcon.setVisible(false);
            });
            
        }
    }

    public void makePlant(String species, int row, int col) {
        //plant part
        Plant newPlant = new Plant(row, col, species);
        plants[row][col] = newPlant;
        //System.out.println("plant created at " + row + ", " + col);
        garden.getChildren().add(newPlant.plantIcon.display);
    
        newPlant.plantIcon.display.setOnMouseEntered(event -> {
            descIcon.setVisible(true);
            descName.setText(description.get(species)[0]);
            descSpecies.setText(description.get(species)[1]);
            if (!symbolysis) {
                descActual.setText("\n" + description.get(species)[2] + "\n" + description.get(species)[3]);
            } else {
                descActual.setText(hieroglyphics[(int)(Math.random() * 5)]);
            }
            descIcon.setTranslateX(-920 + 90 * row);
            descIcon.setTranslateY(-610 + 80 * col);
            descIcon.toFront();
        });
        newPlant.plantIcon.display.setOnMouseExited(event -> {
            descIcon.setVisible(false);
        });

        newPlant.plantIcon.display.setOnMouseClicked(event -> {
            if (action.equals("immortalize")) {
                action = "harvest";
                System.out.println("erm that's not the will of heaven");
                newPlant.isImmortal = true;
            } else {
                if (species.equals("heaven-defyingfruit") && newPlant.isElder) {
                    action = "immortalize";
                }
                if (!seedsObtained[Arrays.asList(seeds).indexOf(species)] && newPlant.isMature) {
                    int k = Arrays.asList(seeds).indexOf(species);
                    seedsObtained[k] = true;
                    seedIcons[k / 5][k % 5].display.setImage(loadImage(seeds[k] + "0"));
                    theoreticalSeedIcons[k / 5][k % 5].display.setImage(loadImage(seeds[k] + "0"));
                    descIcon.setVisible(true);
                    descName.setText(description.get(species)[0] + " unlocked!");
                    descSpecies.setText(description.get(species)[1]);
                    if (!symbolysis) {
                        descActual.setText("\n" + description.get(species)[2] + "\n" + description.get(species)[3]);
                    } else {
                        descActual.setText(hieroglyphics[(int)(Math.random() * 5)]);
                    }
                    descIcon.setTranslateX(-920 + 90 * row);
                    descIcon.setTranslateY(-610 + 80 * col);
                    descIcon.toFront();
                }
                killPlant(row, col);
            }
        });
        //hole part
        holes[row][col].isPlantable = false;
    }

    public void killPlant(int row, int col) {
        Plant thisPlant = plants[row][col];
        //plant part
        garden.getChildren().remove(thisPlant.plantIcon.display);
        if (thisPlant.isMature) {
           revertMaturity(thisPlant, row, col);
        }
        //hole part
        holes[row][col].isPlantable = true;
        //data killed
        plants[row][col] = null;

    }

    public void processMaturity(Plant thisPlant, int i, int j) {
        stats.get(thisPlant.species)[5] += 1;
        //System.out.println("grew up:" + stats.get(thisPlant.species)[5] + " living " + thisPlant.species + "s.");
        for (int k = 0; k < 9; k++) {
            if (k != 4 && (i - 1 + k/3) >= 0 && (i - 1 + k/3) <= 6 && (j - 1 + k%3) >= 0 && (j - 1 + k%3) <= 6) {
                holes[i - 1 + k/3][j - 1 + k%3].neighbouring.add(thisPlant.species);
                if (thisPlant.species.equals("maharajatater") && plants[i - 1 + k/3][j - 1 + k%3] != null && !plants[i - 1 + k/3][j - 1 + k%3].isImmortal) {
                    atp += stats.get(plants[i - 1 + k/3][j - 1 + k%3].species)[1];
                    killPlant(i - 1 + k/3, j - 1 + k%3);
                }
            }
        }
        if (thisPlant.species.equals("tianhuangyam")) {
            for (int k = 0; k < 49; k++) {
                if (plants[k / 7][k % 7] != null && !plants[k / 7][k % 7].isImmortal && !plants[k / 7][k % 7].equals(thisPlant)) {
                    tianhuangsacrifice += stats.get(plants[k / 7][k % 7].species)[1];
                    killPlant(k / 7, k % 7);
                }
            }
        }
    }

    public void revertMaturity(Plant thisPlant, int row, int col) {
        stats.get(thisPlant.species)[5] -= 1;
        //System.out.println("now only " + stats.get(thisPlant.species)[5] + thisPlant.species + "s living.");
        thisPlant.isMature = false;
        for (int k = 0; k < 9; k++) {
            if (k != 4 && (row - 1 + k/3) >= 0 && (row - 1 + k/3) <= 6 && (col - 1 + k%3) >= 0 && (col - 1 + k%3) <= 6) {
                holes[row - 1 + k/3][col - 1 + k%3].neighbouring.remove(thisPlant.species);
                //System.out.println("hole at " + (i - 1 + k/3) + ", " + (j - 1 + k%3) + " no longer neighbouring " + thisPlant.species);
            }
        }
    }

    public void proposeMutation(Hole thisHole) {
        HashSet<String> neighbours = new HashSet<>(thisHole.neighbouring);
        ManualDistribution mutations = new ManualDistribution();
        int krisprMultiplier = (int)stats.get("krisprmoss")[5] + 1;
        mutations.add("", 1);
        mutations.add("banalweed", 0.01 + 0.1 * Math.exp(-Math.pow(year - 10, 2)));
        if (neighbours.contains("sillygrass")) {
            mutations.add("sillygrass", 0.1);
            mutations.add("wackywheat", 0.09 * mutationRate);
        }
        if (neighbours.contains("wackywheat")) {
            mutations.add("sillygrass", 0.15);
            mutations.add("wackywheat", 0.08);
            if (neighbours.contains("sillygrass")) {
                mutations.add("crazycorn", 0.15 * mutationRate);
            }
        }
        if (neighbours.contains("crazycorn")) {
            mutations.add("ionlemon", 0.08 * mutationRate);
            if (neighbours.contains("sillygrass")) {
                mutations.add("zenrice", 0.22 * mutationRate);
            }
        }
        if (neighbours.contains("zenrice")) {
            if (neighbours.contains("wackywheat")) {
                mutations.add("aloevera", 0.22 * mutationRate);
            }
        }
        if (neighbours.contains("banalweed")) {
            mutations.add("oddmold", 0.1 * mutationRate);
            mutations.add("banalweed", 0.2);
            if (neighbours.contains("sillygrass") && neighbours.contains("wackywheat")) {
                mutations.add("dukeroot", 0.4 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("oddmold")) {
            mutations.add("evenspore", 0.3 * mutationRate);
        }
        if (neighbours.contains("evenspore")) {
            mutations.add("oddmold", 0.3);
            if(neighbours.contains("zenrice")) {
                mutations.add("myelichen", 0.6 * mutationRate);
            }
        }
        if (neighbours.contains("myelichen")) {
            mutations.add("cortecactus", 0.2 * mutationRate);
            if (neighbours.contains("oddmold")) {
                mutations.add("forget-me-so", 0.05 * mutationRate);
            }
        }
        if (neighbours.contains("aloevera")) {
            if (neighbours.contains("myelichen")) {
                mutations.add("cortecactus", 0.3 * mutationRate);
            }
            if (neighbours.contains("oddmold")) {
                mutations.add("necrose", 0.2 * mutationRate);
            }
        }
        if (neighbours.contains("dukeroot")) {
            mutations.add("dukeroot", 0.03);
            if (neighbours.contains("myelichen") || neighbours.contains("cortecactus")) {
                mutations.add("sachembeet", 0.1 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("cortecactus")) {
            if (neighbours.contains("zenrice")) {
                mutations.add("aloevera", 0.4 * mutationRate);
            }
            if (neighbours.contains("oddmold")) {
                mutations.add("forget-me-so", 0.2 * mutationRate);
            }
        }
        if (neighbours.contains("ionlemon")) {
            mutations.add("ionlemon", 0.1);
            if (neighbours.contains("banalweed") || neighbours.contains("oddmold")) {
                mutations.add("necrose", 0.15 * mutationRate);
            }
        }
        if (neighbours.contains("necrose")) {
            mutations.add("ionlemon", 0.1 * mutationRate);
            if (neighbours.contains("cortecactus") || neighbours.contains("myelichen")) {
                mutations.add("forget-me-so", 0.4 * Math.pow(mutationRate, krisprMultiplier));
            }
            if (neighbours.contains("ionlemon")) {
                mutations.add("gammaviolet", 0.15 * Math.pow(mutationRate, krisprMultiplier));
            }
            if (neighbours.contains("aloevera") || neighbours.contains("gammaviolet")) {
                mutations.add("red-tonguedtulip", 0.15 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("forget-me-so")) {
            if (neighbours.contains("gammaviolet") || neighbours.contains("heaven-defyingfruit") || neighbours.contains("krisprmoss")) {
                mutations.add("oblivionlily", 0.05 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("sachembeet")) {
            mutations.add("sachembeet", 0.02);
            if (neighbours.contains("red-tonguedtulip") || neighbours.contains("necrose") || neighbours.contains("heaven-defyingfruit")) {
                mutations.add("maharajatater", 0.1 * Math.pow(mutationRate, krisprMultiplier));
            }
            if (neighbours.contains("krisprmoss") && neighbours.contains("aloevera")) {
                mutations.add("heaven-defyingfruit", 0.5 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("gammaviolet")) {
            if (neighbours.contains("cortecactus") || neighbours.contains("myelichen")) {
                mutations.add("krisprmoss", 0.05 * mutationRate);
            }
        }
        if (neighbours.contains("red-tonguedtulip")) {
            if (neighbours.contains("myelichen") || neighbours.contains("cortecactus") || neighbours.contains("ionlemon")) {
                mutations.add("jabberpoppy", 0.07 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("heaven-defyingfruit")) {
            if (neighbours.contains("aloevera") || neighbours.contains("red-tonguedtulip") || neighbours.contains("sachembeet")) {
                mutations.add("angelcress", 0.04 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("maharajatater")) {
            mutations.add("maharajatater", 0.01);
            if (neighbours.contains("heaven-defyingfruit") || neighbours.contains("sachembeet") || neighbours.contains("zenrice")) {
                mutations.add("tianhuangyam", 0.05 * Math.pow(mutationRate, krisprMultiplier));
            }
        }
        if (neighbours.contains("angelcress") && neighbours.contains("oblivionlily") || neighbours.contains("jabberpoppy") && neighbours.contains("tianhuangyam")) {
            mutations.add("all-purposeapotheolia", 0.03 * Math.pow(mutationRate, krisprMultiplier));
        }

        String decidedMutation = mutations.sample();
        if (decidedMutation.length() != 0) {
            makePlant(decidedMutation, thisHole.row, thisHole.col);
        }
    }

    public void proposeVisitor() {
        ManualDistribution visitors = new ManualDistribution();
        visitors.add("", 0.3);
        if (year % 10 != 0) {visitors.add("", 1.7);}
        if (year == 10) {
            visitors.add("timekeeperant", 100);
        }
        if (year > 10) {
            if (goodwill > 0) {visitors.add("timekeeperant", 0.01);}
            if (goodwill < 0) {visitors.add("timebreakerant", 0.01);}
            if (goodwill > 1600) {visitors.add("constellationserpentes", 0.02);}
            if (goodwill > -1000) {visitors.add("melliferousbumblebee", 0.04);}
            if (goodwill < -5000) {visitors.add("crystallinedragonfly", 0.05);}
            if (mersquirrelStory == 0) {
                visitors.add("mersquirrel", 0.02);
                if (year == 80) {
                    visitors.add("mersquirrel", 100);
                }
            }
            if (goodwill > -2000 && mersquirrelStory == 0) {visitors.add("mersquirrel", 0.01);}
            if (mersquirrelStory == 1) {visitors.add("mersquirrelagain", 1);}
            if (mersquirrelStory == 2) {visitors.add("mersquirrelagainagain", 1);}
            if (mersquirrelStory == 3) {visitors.add("mersquirrelfinal", 1);}
            if (goodwill < -3125) {visitors.add("pigeonofsymbols", 0.04);}
            if (goodwill > -500) {visitors.add("symphognome", 0.03);}
            if (goodwill < 2000 && catnap == 0) {visitors.add("sleepycat", 0.02);}
            if (goodwill > -3000) {visitors.add("cubekeeperraccoon", 0.03);}
            if (goodwill > 1000) {visitors.add("lemonweaverspider", 0.02);}
            if (goodwill > -2000) {visitors.add("sugarhopper", 0.015);}
        }
        
        String decidedVisitor = visitors.sample();
        if (decidedVisitor.length() != 0) {
            inviteVisitor(decidedVisitor);
        }
    }

    public void inviteVisitor(String decidedVisitor) {
        boolean rejectable = false;
        Runnable acceptEffect = () -> {};
        Runnable rejectEffect = () -> {};
        switch (decidedVisitor) {
            case "timekeeperant": 
                rejectable = true;
                acceptEffect = () -> {
                    for (int k = 0; k < 49; k++) {
                        Plant thisPlant = plants[k / 7][k % 7];
                        if (thisPlant != null) {
                            if (thisPlant.isMature) {
                                revertMaturity(thisPlant, k / 7, k % 7);
                            }
                            thisPlant.age = 0;
                            thisPlant.sprite.setImage(loadImage(thisPlant.species + "1"));
                        }
                    }
                };
                rejectEffect = () -> {goodwill -= 50;};
                break;
            case "timebreakerant":
                rejectable = true;
                acceptEffect = () -> {
                    for (int k = 0; k < 49; k++) {
                        Plant thisPlant = plants[k / 7][k % 7];
                        if (thisPlant != null) {
                            if (thisPlant.isMature) {
                                revertMaturity(thisPlant, k / 7, k % 7);
                            }
                            thisPlant.age = (int)(Math.random() * 3);
                        }
                    }
                };
                break;
            case "constellationserpentes":
                rejectable = true;
                acceptEffect = () -> {
                    atpMultiplier += 7;
                    atp += 1600;
                    thoughtMultiplier += 7;
                };
                rejectEffect = () -> {atp --; goodwill -= 200;};
                break;
            case "melliferousbumblebee":
                rejectable = true;
                acceptEffect = () -> {
                    atp -= 900;
                    for (int k = 0; k < 49; k++) {
                        Plant thisPlant = plants[k / 7][k % 7];
                        if (thisPlant != null && thisPlant.age < stats.get(thisPlant.species)[2]) {
                            thisPlant.age = (int)stats.get(thisPlant.species)[2];
                            thisPlant.sprite.setImage(loadImage(thisPlant.species + "2"));
                            thisPlant.isMature = true;
                            processMaturity(thisPlant, k / 7, k % 7);
                        }
                    }
                    atpMultiplier += 3;
                };
                rejectEffect = () -> {goodwill -= 20;};
                break;
            case "crystallinedragonfly":
                rejectable = true;
                acceptEffect = () -> {
                    crystalysis = true;
                    root.setScaleY(-1);
                    root.setEffect(new ColorAdjust(-0.1, 0.05, 0, 0));
                };
                rejectEffect = () -> {};
                break;
            case "mersquirrel":
                rejectable = true;
                acceptEffect = () -> {atp -= 500; mersquirrelStory = 1;};
                rejectEffect = () -> {goodwill -= 100;};
                break;
            case "mersquirrelagain":
                rejectable = true;
                acceptEffect = () -> {atp -= 2000; mersquirrelStory = 2;};
                rejectEffect = () -> {goodwill -= 100; mersquirrelStory = 0;};
                break;
            case "mersquirrelagainagain":
                rejectable = true;
                acceptEffect = () -> {atp -= 5000; mersquirrelStory = 3;};
                rejectEffect = () -> {goodwill -= 100; mersquirrelStory = 0;};
                break;
            case "mersquirrelfinal":
                rejectable = true;
                acceptEffect = () -> {atp -= 19900; mersquirrelStory = 4; goodwill += 500;};
                rejectEffect = () -> {goodwill -= 100; mersquirrelStory = 4;};
                break;
            case "pigeonofsymbols":
                rejectable = true;
                acceptEffect = () -> {
                    symbolysis = true;
                };
                rejectEffect = () -> {};
                break;
            case "symphognome":
                rejectable = true;
                acceptEffect = () -> {
                    for (int k = 0; k < 49; k++) {
                        Plant thisPlant = plants[k / 7][k % 7];
                        if (thisPlant != null) {
                            atp += stats.get(thisPlant.species)[1] * 3;
                            killPlant(k / 7, k % 7);
                        }
                    }
                };
                rejectEffect = () -> {goodwill -= 200;};
                break;
            case "sleepycat":
                rejectable = true;
                acceptEffect = () -> {
                    catnap = 5;
                    for (int k = 0; k < 9; k++) {
                        if (plants[2 + k / 3][2 + k % 3] != null) {
                            killPlant(2 + k / 3, 2 + k % 3);
                            sleepycat.setVisible(true);
                            sleepycat.toFront();
                        }
                        holes[2 + k / 3][2 + k % 3].isPlantable = false;
                    }
                };
                rejectEffect = acceptEffect;
                break;
            case "cubekeeperraccoon":
                rejectable = true;
                acceptEffect = () -> {

                };
                rejectEffect = () -> {

                };
                break;
            case "lemonweaverspider":
                rejectable = true;
                acceptEffect = () -> {
                    for (int k = 0; k < 49; k++) {
                        if (holes[k / 7][k % 7].isPlantable) {
                            makePlant("ionlemon", k / 7, k % 7);
                        }
                        mutationMultiplier += 3;
                    }
                };
                rejectEffect = () -> {
                    goodwill -= 300;
                };
                break;
            case "sugarhopper":
                rejectable = true;
                acceptEffect = () -> {
                    atp -= 1000;
                    for (int k = 0; k < 49; k++) {
                        if (plants[k / 7][k % 7] != null && stats.get(plants[k / 7][k % 7].species)[1] > 500) {
                            killPlant(k / 7, k % 7);
                            makePlant("banalweed", k / 7, k % 7);
                        }
                    }                    
                };
                rejectEffect = () -> {atp -= 300; goodwill -= 100;};
        }

        Visitor newVisitor = new Visitor(decidedVisitor, rejectable, acceptEffect, rejectEffect);
        endYearIcon.display.setDisable(true);
        root.getChildren().add(newVisitor.display);
        newVisitor.descEntrance.getStyleClass().add("textMajor");
        newVisitor.descSpecies.getStyleClass().add("textMini");
        newVisitor.descActual.getStyleClass().add("textMinor");
        newVisitor.acceptButton.iconText.getStyleClass().add("textMajor");
        newVisitor.rejectButton.iconText.getStyleClass().add("textMajor");
        newVisitor.summaryButton.iconText.getStyleClass().add("textMajor");
        newVisitor.summaryButton.display.setOnMouseClicked(event -> {
            endYearIcon.display.setDisable(false);
            root.getChildren().remove(newVisitor.display);
            updateMetrics();
        });
    }

    public void updateMetrics() {
        atpIcon.iconText.setText("ATP: " + atp);
        thoughtIcon.iconText.setText("Thought: " + thought);
        if (Math.abs(goodwill) > 3125) {
            goodwillIndicator.setTranslateX(455 + Math.signum(goodwill) * 125);
        } else {
            goodwillIndicator.setTranslateX(455 + 5 * Math.signum(goodwill) * Math.pow(Math.abs(goodwill), 0.4));
        }
    }

    public Image loadImage(String name) {
        return new Image(getClass().getResource("/plantSprites/" + name + ".png").toExternalForm());
    }

    public static void main(String[] args) {
        try{
            launch(args);
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
    }
}