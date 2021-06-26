package it.polimi.ingsw.model;

import java.util.*;

public class PersonalBoard {

    public enum PopeArea {
        FIRST,
        SECOND,
        THIRD
    }

    private Integer userID;
    private Game game;
    private int victoryPoints = 0;
    private DevSlot[] devSlots = new DevSlot[3];
    private Shelf[] warehouse = new Shelf[3];
    private Resources strongbox;

    private int faithPoints = 0;
    private List<LeaderCard> inactiveLeaderCards;
    private List<LeaderCard> activeLeaderCards;
    private Map<PopeArea, Boolean> popeAreaMap;

    private List<DevCard> ownedDevCards = new ArrayList<>();

    public PersonalBoard(Integer userID) {
        this.userID = userID;
        devSlots[0] = new DevSlot(DevSlot.slotPlace.LEFT);
        devSlots[1] = new DevSlot(DevSlot.slotPlace.CENTER);
        devSlots[2] = new DevSlot(DevSlot.slotPlace.RIGHT);
        warehouse[0] = new Shelf(Shelf.shelfPlace.TOP);
        warehouse[1] = new Shelf(Shelf.shelfPlace.MIDDLE);
        warehouse[2] = new Shelf(Shelf.shelfPlace.BOTTOM);
        strongbox = new Resources();
        popeAreaMap = new HashMap<>();
        inactiveLeaderCards = new ArrayList<>();
        activeLeaderCards = new ArrayList<>();
        popeAreaMap.put(PopeArea.FIRST, false);
        popeAreaMap.put(PopeArea.SECOND, false);
        popeAreaMap.put(PopeArea.THIRD, false);
    }

    public PersonalBoard(Integer userID, boolean solo) {
        this(userID);
        if (solo) {
            // TODO Place Black CROSS

        }
    }

    //     for now, this considers strongbox only, the code will need to be improved.
//     in case there is not enough resource, some error/exception must be included,
//     or assertions before execution/during testing
    public void putToWarehouseWithoutCheck(Resources res) {
        for (Resources.ResType resType : res.getResTypes()) {
            putFromTop(resType, res.getNumberOfType(resType));
        }
    }

    public int putToWarehouse(Shelf.shelfPlace place, Resources res) {
        return warehouse[place.getIndexInWarehouse()].putResource(res);
    }
    //Todo what does this method do
    private boolean putFromTop(Resources.ResType resType, int size) {
        if ((checkEnoughSize(0, size) && (checkSameType(0, resType)) || warehouse[0].isEmpty())) {
            warehouse[0].putResource(resType, size);
        } else if ((checkEnoughSize(1, size) && checkSameType(1, resType)) || warehouse[1].isEmpty()) {
            warehouse[1].putResource(resType, size);
        } else if ((checkEnoughSize(2, size) && checkSameType(2, resType)) || warehouse[2].isEmpty()) {
            warehouse[2].putResource(resType, size);
        } else return false;
        return true;
    }

    private boolean checkEnoughSize(int index, int size) {
        return ((warehouse[index].getNumberOfElements()) + size <= warehouse[index].getShelfSize());
    }

    private boolean checkSameType(int index, Resources.ResType resType) {
        try {
            return (warehouse[index].getShelfResType() == resType);
        } catch (Exception e) {
            return true;
        }


    }
    public  List<DevCard> getOwnedCards() {
        return this.ownedDevCards;
    }

    public void countVictoryPoints(int victoryPoints) {
        this.victoryPoints+=victoryPoints;
    }

    public  int getVictoryPoints() {
        return this.victoryPoints;
    }

    public void increaseFaitPoint(int toAdd) {
        if (faithPoints < 8 && (faithPoints+toAdd)>=8){
            game.triggerVaticanReport(PopeArea.FIRST);
        } else if (faithPoints < 16 && (faithPoints+toAdd)>=16){
            game.triggerVaticanReport(PopeArea.SECOND);
        } else if (faithPoints < 24 && (faithPoints+toAdd)>=24){
            game.triggerVaticanReport(PopeArea.THIRD);
        }
        faithPoints += toAdd;
    }
    //methods only for testing
    public void setFaitPoint(int toAdd) {
        faithPoints += toAdd;
    }

    public Map<PopeArea, Boolean> getPopeAreaMap(){
        return popeAreaMap;
    }

    public int getFaithPoints(){
        return faithPoints;
    }

    public void giveVaticanReport(PopeArea area) {
        if (area == PopeArea.FIRST && faithPoints >= 5 && faithPoints <= 8) {
            turnPopeFavorTile(PopeArea.FIRST);
        } else if (area == PopeArea.SECOND && faithPoints >= 12 && faithPoints <= 16) {
            turnPopeFavorTile(PopeArea.SECOND);
        } else if (area == PopeArea.THIRD && faithPoints >= 19 && faithPoints <= 24) {
            turnPopeFavorTile(PopeArea.THIRD);
        }
    }

    private void turnPopeFavorTile(PopeArea area) {
        popeAreaMap.replace(area, Boolean.TRUE);
        // create a MV message inside Game
    }

    public List<LeaderCard> getInactiveLeaderCards() {
        return this.inactiveLeaderCards;
    }


    public List<LeaderCard> getActiveLeaderCards() {
        return this.activeLeaderCards;
    }


//todo Omer why is this method never used?
    public void setInactiveLeaderCards(List<LeaderCard> inactiveLeaderCards) {
        this.inactiveLeaderCards = inactiveLeaderCards;
    }

    //    public void setInactiveLeaderCards(List<LeaderCard> inactiveLeaderCards) {
//        this.inactiveLeaderCards = inactiveLeaderCards;
//    }

    public void setActiveLeaderCards(List<LeaderCard> activeLeaderCards) {
        this.activeLeaderCards = activeLeaderCards;
    }

    public Resources getTotalResources() {
        Resources res = new Resources();
        res.add(getWarehouseResources());
        res.add(getStrongboxResources());
        res.add(getExtraSlotResources());
        return res;
    }

    public Resources getStrongboxResources() {
        Resources res = new Resources();
        res.add(strongbox);
        return res;
    }

    public Resources getWarehouseResources() {
        Resources res = new Resources();
        for (Shelf shelf : warehouse) {
            res.add(shelf.getResource());
        }
        return res;
    }

    public Resources getExtraSlotResources(){
        Resources res = new Resources();
        for(LeaderCard card:activeLeaderCards){
            if(card.getAbility().getAbilityType() == SpecialAbility.AbilityType.EXTRASLOT){
                res.add(card.getAbility().getResourcesAtSlot());
            }
        }
        return res;
    }
//todo Omer you have to check that the discount is on that res type,
// i mean if the discount is related stone and the card doesn't need of coin you cannot apply a disconut,
// there isn't a check either here and inside res class in the subtract method
    public boolean isThereEnoughRes(DevCard card) {
        Resources totalRes = getTotalResources();
        Resources cost = new Resources();
        cost.add(card.getCost());
        for(LeaderCard leaderCard: activeLeaderCards){
           //this how should be
            // if(leaderCard.getAbility().getAbilityType() == SpecialAbility.AbilityType.DISCOUNT && card.getCost().isThereType(leaderCard.getAbility().getResType()))
            if(leaderCard.getAbility().getAbilityType() == SpecialAbility.AbilityType.DISCOUNT){
                Resources.ResType discountType = leaderCard.getAbility().getResType();
                cost.subtract(discountType,1);
            }
        }
        if (cost.smallerOrEqual(totalRes)) return true;
        else return false;
    }

    public boolean isCardSuitableForSlots(DevCard card) {
        int level = card.getLevel();
        for (DevSlot devSlot : devSlots) {
            if (level == devSlot.getLevelOfTopCard() + 1) return true;
        }
        return false;
    }

    public List<DevSlot.slotPlace> getSuitablePlaces(DevCard card) {
        int level = card.getLevel();
        List<DevSlot.slotPlace> places = new ArrayList<>();
        for (DevSlot devSlot : devSlots) {
            if (level == devSlot.getLevelOfTopCard() + 1) places.add(devSlot.getPlace());
        }
        return places;
    }

    public void putDevCardOnSlot(DevCard card, DevSlot.slotPlace place) {
        int index = place.getIndexInBoard();
        devSlots[index].putDevCard(card);
        this.ownedDevCards.add(card);
    }

    public DevCard getDevCardOnSlot(DevSlot.slotPlace place) {
        int index = place.getIndexInBoard();
        if(devSlots[index].isEmpty()){
            return null;
        }
        return devSlots[index].getTopDevCard();
    }

    public int clearShelf(Shelf.shelfPlace place) {
        return warehouse[place.getIndexInWarehouse()].clearShelf();
    }

    public int swapShelves(Shelf.shelfPlace[] places) {
        return warehouse[places[0].getIndexInWarehouse()].swapShelf(warehouse[places[1].getIndexInWarehouse()]);
    }

    public List<Shelf> getShelves(){
        List<Shelf> shelves = new ArrayList<>();
        shelves.add(warehouse[0]);
        shelves.add(warehouse[1]);
        shelves.add(warehouse[2]);
        return shelves;
    }

    public List<DevSlot> getDevSlots(){
        List<DevSlot> slots = new ArrayList<>();
        slots.add(devSlots[0]);
        slots.add(devSlots[1]);
        slots.add(devSlots[2]);
        return slots;
    }

    public void subtractFromWarehouse(Resources res) {
        List<Resources.ResType> resTypeList = new ArrayList<>();
        resTypeList.addAll(res.getResTypes());
        for (int i = 0; i < 3; i++) {
            for (Resources.ResType resType : resTypeList) {
                if (warehouse[i].getShelfResType().equals(resType)) {
                    int number = res.getNumberOfType(resType);
                    warehouse[i].removeFromShelf(number);
                }
            }
        }
    }

    public void addToStrongBox(Resources res) {
        this.strongbox.add(res);
    }

    public void subtractFromStrongbox(Resources res) {
        this.strongbox.subtract(res);
    }

    public void subtractFromExtraSlot(Resources res){
        for(LeaderCard card: activeLeaderCards){
            if(card.getAbility().getAbilityType() == SpecialAbility.AbilityType.EXTRASLOT){
                card.getAbility().subtractFromExtraSlot(res);
            }
        }
    }

    public void setStrongbox(Resources strongbox) {
        this.strongbox = strongbox;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public int getVP(){
        int VP = 0;
        VP += getVPFromDevCards();
        VP += getVPfromFaithTrack();
        VP += getVPfromActiveLeaders();
        VP += getVPfromAllResources();
        return VP;
    }
    private int getVPFromDevCards(){
        int VP = 0;
        for(DevCard card: ownedDevCards){
            VP += card.getVictoryPoints();
        }
        return VP;
    }

    private int getVPfromFaithTrack(){
        int VP = 0;
        if ((0 <= faithPoints) && (faithPoints < 3))
            VP =  0;
        else if ((3 <= faithPoints) && (faithPoints < 6))
            VP = 1;
        else if ((6 <= faithPoints) && (faithPoints < 9))
            VP = 2;
        else if ((9 <= faithPoints) && (faithPoints < 12))
            VP = 4;
        else if ((12 <= faithPoints) && (faithPoints < 15))
            VP = 6;
        else if ((15 <= faithPoints) && (faithPoints < 18))
            VP = 9;
        else if ((18 <= faithPoints) && (faithPoints < 21))
            VP = 12;
        else if ((21 <= faithPoints) && (faithPoints < 24))
            VP = 16;
        else if ((24 <= faithPoints))
            VP = 20;

        if(popeAreaMap.get(PopeArea.FIRST)) VP += 2;
        if(popeAreaMap.get(PopeArea.SECOND)) VP += 3;
        if(popeAreaMap.get(PopeArea.THIRD)) VP += 4;

        return VP;
    }

    private int getVPfromActiveLeaders(){
        int VP = 0;
        for(LeaderCard card: activeLeaderCards){
            VP += card.getVictoryPoints();
        }
        return VP;
    }

    private int getVPfromAllResources(){
        Resources res = getTotalResources();
        int VP = 0;
        VP += Integer.valueOf(res.sumOfValues()/5);
        return VP;
    }
}
