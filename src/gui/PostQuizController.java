package gui;


import javafx.event.ActionEvent;

import javax.swing.*;

/**
 * Author: Yuliang Zhou 6/09/2016
 */
public class PostQuizController implements ControlledScreen{

    private MasterController _myParentController;

    public void returnToTitleButtonPressed(ActionEvent event){
        _myParentController.setScreen(Main.titleScreenID);
    }

    public void playVideoButtonPressed(ActionEvent e){
        //TODO: open video player
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }


}
