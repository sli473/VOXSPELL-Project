package gui;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.animation.KeyFrame;
import javafx.animation.FadeTransition;

import javafx.beans.property.DoubleProperty;


import javafx.stage.Screen;
import javafx.util.Duration;


import java.util.HashMap;


/**
 * Created by Samule Li on 5/09/16.
 */
public class ScreenController extends StackPane {
    private HashMap<String, Node> screens = new HashMap<>();

    public ScreenController(){
        super();
    }
    //adding a screen to the hashMap
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }

    //returns the node with the appropriate name.
    public Node getScreen(String name){
        return screens.get(name);
    }

    //Loads the fxml file and injects the screenPane into the controller.
    public boolean loadScreen(String name, String resource) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            System.out.println (" loads");
            Parent root = loader.load();
            ControlledScreen myScreenController = loader.getController();
            myScreenController.setScreenParent(this);
            addScreen(name, root);
            return true;
        }
        catch (Exception e){
            System.out.println("nah g");
            System.out.println(e.getMessage());
            return false;

        }
    }

    //This method tries to display the selected screen. It first checks whether or not the screen has already been
    //loaded. If there is more than one screen, the new screen is added second, and the current screen is removed.
    public boolean setScreen(final String name) {
        if (screens.get(name) != null) { //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                Timeline transition = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                getChildren().remove(0);    //remove the displayed screens
                                getChildren().add(0, screens.get(name)); //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                transition.play();
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline transition = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                transition.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded");
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen doesn't exist");
            return false;
        } else {
            return true;
        }
    }

}
