package gui;


/**
 * Author: Yuliang Zhou 6/09/2016
 */
public class PostQuizController implements ControlledScreen{

    private MasterController _myParentController;


    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }


}
