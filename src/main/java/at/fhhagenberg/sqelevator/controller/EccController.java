package at.fhhagenberg.sqelevator.controller;

import at.fhhagenberg.sqelevator.constants.Constants;
import at.fhhagenberg.sqelevator.model.ApplicationModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

public class EccController extends WindowAdapter implements ActionListener {

    private ApplicationModel model;

    private Timer timer;

    public EccController(ApplicationModel model) {
        this.model = model;
        this.timer = new Timer(Constants.UPDATE_RATE_MS, this);
        timer.setActionCommand(Constants.ACTION_COMMAND_TIMER);
    }

    public void initApplication() {
        model.initApplication();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constants.ACTION_COMMAND_TIMER: {
                model.update();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        timer.stop();
        e.getWindow().dispose();
    }

    // Autogenerated shit

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public void setSelectedFloor(int elevatorIndex ,int i){
        try {
            model.setManualElevatorTarget(elevatorIndex,i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setAutomaticMode(int elevatorIndex, Boolean bool){
        model.setElevatorAutomaticMode(elevatorIndex, bool);
    }

    public void setSelectedElevator(int elevatorIndex){
        model.setSelectedElevator(elevatorIndex);
    }

}
