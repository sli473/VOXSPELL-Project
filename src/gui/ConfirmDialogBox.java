package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pop-up dialog box which blocks all other input to the main window until user clicks
 * 'yes' or 'no' button.
 * Created by Yuliang on 7/09/2016.
 */
public class ConfirmDialogBox {

    private static boolean _answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        //Add message and yes and no buttons
        Label label = new Label();
        label.setText(message);
        Button noButton = new Button("No");
        Button yesButton = new Button("Yes");

        //set up action handlers for each button
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _answer = false;
                window.close();
            }
        });
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _answer = true;
                window.close();
            }
        });

        VBox layout = new VBox();
        HBox buttons = new HBox(40);
        buttons.getChildren().addAll(yesButton, noButton);
        layout.getChildren().addAll(label, buttons);
        buttons.setPadding(new Insets(10,10,10,10));
        layout.setPadding(new Insets(10,10,10,10));
        buttons.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return _answer;
    }

}
