package it.polimi.ingsw.utility.messages;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivateProdActionContext {
    private boolean isRow;
    private int index;
    private boolean hasError;
    private List<LeaderCard> producerCard;
    private List<LeaderCard> activeProduceCard;
    private Resources resources;

    private List<DevSlot>  slotChosen;
    private List<DevCard>  selectedCard;
    private Resources payment;
    private Map<Shelf.shelfPlace, Resources.ResType> shelfPlaceResTypeMap = new HashMap<>();
    private Map<Shelf.shelfPlace, Boolean> shelfToResultMap = new HashMap<>();
    private Boolean baseProdPower;
    //flag for leader card
    public enum ActionStep{
        // from client to server
        DEV_SLOTS_CHOOSEN,


        // from server to client

        CHECK_ACTIVE_LEADER_PRODOCTION,
        CHOOSE_DEV_SLOTS,
        CHOOSE_ORDER,
        EMPTY_DEV_SLOTS_ERROR,
        NOT_ENOUGH_RES,
        CHOOSE_LEADER_TO_PRODUCE,
        CHECK_RES_FROM_SHELF,
        COST_PAID
    }
    private ActionStep lastStep;
    private List<DevSlot.slotPlace> placeList = new ArrayList<>();
    public void setBaseProdPower(boolean answer){
        this.baseProdPower = answer;
    }
    public ActionStep getLastStep(){
        return lastStep;
    }

    public void setLastStep(ActionStep step){
        lastStep = step;
    }

    public void setSlots(List<DevSlot> slotChosen){
        this.slotChosen = slotChosen;
    }
    public List<DevSlot> getSlots(){
        return this.slotChosen;
    }

    public void setErrorTrue(){
        this.hasError = true;
    }
    public List<DevCard> getSelectedCard() {
        return selectedCard;
    }
    public void setSelectedCard( List<DevCard> selectedCard) {
        this.selectedCard.addAll(selectedCard);
    }

    public List<LeaderCard> getProducerCard() {
        return producerCard;
    }

    public void setLeaderProd(List<LeaderCard> producerCard) {
        this.producerCard = new ArrayList<>();
        this.producerCard.addAll(producerCard);
    }
/*
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getWhiteMarbleNumber() {
        return whiteMarbleNumber;
    }

    public void setWhiteMarbleNumber(int whiteMarbleNumber) {
        this.whiteMarbleNumber = whiteMarbleNumber;
    }

    public List<LeaderCard> getWhiteConverters() {
        return producerCard;
    }

    public void setWhiteConverters(List<LeaderCard> producerCard) {
        this.producerCard = new ArrayList<>();
        this.whiteConverters.addAll(producerCard);
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = new Resources();
        this.resources.add(resources);
    }

    public void addOneConvertedRes(Resources.ResType resType){
        this.resources.add(resType,1);
    }

    public void convertResIntoFaith(){
        this.faithPoints = this.resources.getNumberOfType(Resources.ResType.FAITH);
        this.resources.subtract(Resources.ResType.FAITH, faithPoints);
    }

    public void addDiscardedRes(int number){
        this.discardedRes += number;
    }

    public Shelf.shelfPlace getShelf() {
        return place;
    }

    public void setShelf(Shelf.shelfPlace place) {
        this.place = place;
    }

    public Shelf.shelfPlace[] getShelves() {
        return places;
    }

    public void setShelves(Shelf.shelfPlace firstPlace, Shelf.shelfPlace secondPlace) {
        this.places[0] = firstPlace;
        this.places[1] = secondPlace;
    }

    public void setShelftoResTypeMap(Map<Shelf.shelfPlace, Resources.ResType> map){
        for (Map.Entry<Shelf.shelfPlace, Resources.ResType> entry : map.entrySet()) {
            this.shelfPlaceResTypeMap.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<Shelf.shelfPlace, Resources.ResType> getShelfPlaceResTypeMap() {
        return shelfPlaceResTypeMap;
    }

    public void setPutResultMap(Map<Shelf.shelfPlace, Boolean> map){
        for (Map.Entry<Shelf.shelfPlace, Boolean> entry : map.entrySet()) {
            this.shelfToResultMap.put(entry.getKey(), entry.getValue());
        }
    }

    public void removeResourcesPutToShelf(){
        for (Map.Entry<Shelf.shelfPlace, Boolean> entry : shelfToResultMap.entrySet()) {
            if (entry.getValue()){
                Resources.ResType resType = shelfPlaceResTypeMap.get(entry.getValue());
                this.resources.removeThisType(resType);
            }
        }
    }
*/
}
