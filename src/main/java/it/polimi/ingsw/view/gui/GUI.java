package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.utility.messages.*;
import it.polimi.ingsw.view.IView;

public class GUI implements IView, Publisher<VCEvent>, Listener<Event> {
    public GUI(Client client) {
    }

    public GUI() {
    }
//the first gui have the role of greet and start dislay
    @Override
    public void displayGreet() {
        new FirstGui();
    }

    @Override
    public void displaySetup() {
        new ConnectionToServerGui();

    }

    @Override
    public void displayLogin() {
            new LoginGui();
    }

    @Override
    public void displayIdle() {

    }

    @Override
    public void displayLobby() {

    }

    @Override
    public boolean shouldStopDisplayIdle() {
        return false;
    }

    @Override
    public void stopDisplayIdle() {

    }

    @Override
    public void displayGeneralMsg() {

    }

    @Override
    public void setGeneralMsg(String string) {

    }

    @Override
    public void addNextDisplay(String displayA) {

    }

    @Override
    public void displayFirstLogin() {

    }

    public void startDisplay() {

    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void subscribe(Listener<VCEvent> listener) {

    }

    @Override
    public void unsubscribe(Listener<VCEvent> listener) {

    }

    @Override
    public void publish(VCEvent event) {

    }
}
