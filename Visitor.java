import java.util.Map;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.layout.StackPane;

public class Visitor {

    StackPane display;
    boolean rejectable;
    int rejectCost;
    Runnable acceptEffect;
    Runnable rejectEffect;
    Text descEntrance;
    Text descSpecies;
    Text descActual;
    Icon acceptButton;
    Icon rejectButton;
    Icon summaryButton;
    ImageView denial;

    Map<String, String[]> description = Map.ofEntries(
        //entrance, taxonomy, description, accepttext, rejecttext
        Map.entry("timekeeperant", new String[]{"Your first visitor!", "⋅ Formicidae prochrona ⋅", "The Timekeeper Ant offers to reset the ages of all your plants.\nAccept his blessing of youth, or reject him and face the consequences.", "All plants restored to infancy. It was a pleasure doing business with you.", "Oh.................\n(He leaves, brokenhearted. Goodwill - 100.)"}),
        Map.entry("timebreakerant", new String[]{"The Timebreaker Ant invades!", "⋅ Formicidae lysochrono ⋅", "The Timekeeper Ant's cousin. He threatens to throw your\nplants' ages into chaos, wildly extending or cutting short their lifespans.", "Who knows what he's done to your plants? (He doesn't either. This is how it should be.)", "Damn................\n(He stomps away on six feet. Goodwill -100.)"}),
        Map.entry("constellationserpentes", new String[]{"A vast pressence ssslithers acrosss the ssskiess...", "⋅ Thamnophis eridanus ⋅", "The appearance of Conssstellation Sserpentes may be a sign\nof great fortune... or of calamitous ruin. Consider carefully your deal with the sstars.", "The stars ssshine in approval -- ATP and Thought multiplier increased sevenfold!! +1600 ATP.", "Do you believe it so simple to reject a Constellation? ATP - 1. Consider thissss a warning."}),
        Map.entry("melliferousbumblebee", new String[]{"The Melliferous Honeybee lands!", "⋅ Apis dʒɒʃus ⋅", "This helpful bee offers to pollinate your plants in exchange for a little bit of nectar.", "For the cost of 900 ATP, all your plants have been instantly brought to maturity!\nATP multiplier increased threefold!", "I see how it is."}),
        Map.entry("crystallinedragonfly", new String[]{"The sky fractures. It all ends.", "⋅ Synthemistidae kluiyu ⋅", "ཐི༏ཋྀ breaks, a camera shutter blinking in and out of reality. ཐི༏ཋྀ is a shout. \nOr a whisper. ཐི༏ཋྀ is a leaf taken from the only plant in your garden. ཐི༏ཋྀ crowns\nmy fragile heart. ཐི༏ཋྀ was the last shimmering crystaltail dragonfly.", "∀ʇonǝɯǝnʇ.", "how did you get to screen this isn't supposed to happen"}),
        Map.entry("mersquirrel", new String[]{"A Mer-Squirrel?!", "⋅ Hesperosciurus carassius megaa ⋅", "This fishy squirrel motions frantically for you to help.\nSounds like she's locked all her acorns in a clam\nand can't get it open! Help her out with 500 ATP?", "THANK YOU!! She promises to repay your generosity when she gets her acorns back.", "The squirrel looks at you in disbelief. You feel terrible."}),
        Map.entry("mersquirrelagain", new String[]{"The Mer-Squirrel's back?!", "⋅ Hesperosciurus carassius megaa ⋅", "This squirrel comes running up to you with tears in her eyes.\nSounds like she pried the clam open, but then her acorns were snatched by\na passing octopus! Help her retrieve them with 2000 ATP?", "THANK YOU SO SO MUCH!! You'll get the biggest acorn gift when she gets them back.", "The squirrel bursts into tears anew. You feel terrible."}),
        Map.entry("mersquirrelagainagain", new String[]{"The Mer-Squirrel for a third time?!", "⋅ Hesperosciurus carassius megaa ⋅", "This squirrel flings herself at you. Sounds like she fought \noff the octopus, but her acorns were swallowed\nby a giant whale! Help her out with 5000 ATP?", "A MILLION THANKS!!!! She'll chase down the whale and throw an acorn party in your name!!", "The squirrel pleads and pleads but you won't budge. You feel terrible."}),
        Map.entry("mersquirrelfinal", new String[]{"The Mer-Squirrel can't possibly be back again?!", "⋅ Hesperosciurus carassius megaa ⋅", "You find this squirrel in a small pool of her own tears. Sounds like\nshe wrestled the whale and reclaimed her acorns, but then\nan Old One cast a ritual to steal th--what's that?\nYou don't believe her? Won't you help her out with 20000 ATP?", "..You really believed that? Now she feels kinda bad for you.\nShe passes a clawful of acorns to you--nowhere\nnear what you gave her, but it's something. +100 ATP.", "That's okay, none of that really happened. With an \nevil grin she dives into the ocean, never to be seen again."}),
        Map.entry("pigeonofsymbols", new String[]{"𓊪𓃭PIGEON𓎼𓅂𓅱OF𓅱𓃭𓋴SYMBOLS𓉔来了", "⋅ Columbidae hierosymbolica ⋅", "𓊪𓅲𓈖𓇋𓋴н𓉔𓅓ак𓅂𓈖𓏏𓐍!𓐍𓎼𓅱аз𓅱ани\n𓂧𓃭𓅲𓎢𓈎@#𓐍𓈎𓈖е𓅱𓅃𓇋𓈖𓎼𓄿𓏏\n𓏏𓅱&𓂧𓅱𓆑𓂋@𓅱𓅓𓈖𓅱𓅃𓅱𓈖", "𓃭哈哈𓅱𓃭", "𓉔𓅱𓅃𓐍𓂧𓇋𓂧𓐍𓇌𓅱𓅲𓐍𓎼𓅂\n𓏏𓐍𓏏𓅱𓐍𓏏𓉔𓇋𓋴𓊪𓄿𓂋𓏏"}),    
        Map.entry("symphognome", new String[]{"Underneath a mushroom, you hear piano...", "⋅ Paracelsus chillechille ⋅", "The Symphognome's magic song will wither every plant,\nGild every stone and charm every ant.\nHave you the courage to accept his enchant?", "Well chosen! To your garden, go and see:\nEvery plant uprooted for thirce its ATP!", "Shoo now, go home. You count not among those who gnome."}),
        Map.entry("sleepycat", new String[]{"Meow!", "⋅ Felis kerchae ⋅", "Meowmeowmew,,,\n(you can't make out the words, but she seems to be asking a question.)", "Mrrrw!", "You wouldn't say no to a cat."}),
        Map.entry("cubekeeperraccoon", new String[]{"This world, yours to shape...", "⋅ Procyon dwardius ⋅", "cube", "be blended", "be not blended"}),
        Map.entry("lemonweaverspider", new String[]{"You turn and spot the Lemon-Weaver Spider!", "⋅ Eupalaestrus crispi ⋅", "This spider claims to be capable of weaving\nradioactive silk(??) Let her spin her craft?", "Her web is spun of ionlemons sprouts. Mutation rate +3!!", "Oh.........."}),
        Map.entry("sugarhopper", new String[]{"woa is that a sugarhopper", "⋅ Romaleidae meya ⋅", "sugarsnap", "yay", "aw"})
    );

    public Visitor(String name, boolean rejectable, Runnable acceptEffect, Runnable rejectEffect) {
        this.rejectable = rejectable;
        this.acceptEffect = acceptEffect;
        this.rejectEffect = rejectEffect;
        ImageView visitorBackground = new ImageView(loadImage("bigbutton"));
        visitorBackground.setFitWidth(600);
        visitorBackground.setFitHeight(400);
        visitorBackground.setTranslateY(100);
        descEntrance = new Text(description.get(name)[0]);
        descEntrance.setTranslateY(50);
        descSpecies = new Text(description.get(name)[1]);
        descSpecies.setTranslateY(75);
        descActual = new Text(description.get(name)[2]);
        descActual.setTranslateY(120);
        ImageView visitorPortrait;
        try {
            if (name.equals("mersquirrelagain") || name.equals("mersquirrelagainagain") || name.equals("mersquirrelfinal")) {
                visitorPortrait = new ImageView(loadImage("mersquirrel"));
            } else {
            visitorPortrait = new ImageView(loadImage(name));
            }
        } catch (Exception e) {
            System.out.println("erm " + name + ".png doesn't exist");
            visitorPortrait = new ImageView(loadImage("sugarhopper"));
        }
        visitorPortrait.setFitWidth(450);
        visitorPortrait.setFitHeight(300);
        visitorPortrait.setTranslateY(-130);
        acceptButton = new Icon(-120, 200, 160, 140, "bigbutton", "Accept");
        rejectButton = new Icon(120, 200, 160, 140, "bigbutton", "Reject");
        summaryButton = new Icon(0, 200, 160, 140, "bigbutton", "Accept");
        denial = new ImageView(loadImage("denialbutterfly"));
        denial.setTranslateX(120);
        denial.setTranslateY(200);
        denial.setFitWidth(90);
        denial.setFitHeight(60);
        summaryButton.display.setVisible(false);
        display = new StackPane(visitorBackground, visitorPortrait, descEntrance, descSpecies, descActual, acceptButton.display, rejectButton.display, summaryButton.display);
        display.setTranslateX(-50);
        display.setMaxSize(600, 400);
        acceptButton.display.setOnMouseClicked(event -> {
            acceptEffect.run();
            descActual.setText(description.get(name)[3]);
            displayConsequences();
        });
        rejectButton.display.setOnMouseClicked(event -> {
            rejectEffect.run();
            descActual.setText(description.get(name)[4]);
            displayConsequences();
        });
        if (!rejectable) {
            rejectButton.display.setDisable(true);
            display.getChildren().add(denial);
        }
    }

    public void displayConsequences() {
        acceptButton.display.setVisible(false);
        rejectButton.display.setVisible(false);
        summaryButton.display.setVisible(true);
        denial.setVisible(false);
    }

    public Image loadImage(String name) {
        return new Image(getClass().getResource("/plantSprites/" + name + ".png").toExternalForm());
    }
}