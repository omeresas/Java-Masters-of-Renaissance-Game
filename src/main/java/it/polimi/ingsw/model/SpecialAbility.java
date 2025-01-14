package it.polimi.ingsw.model;
/**
 * Class that represent the ability of the leader cards
 * */

public class SpecialAbility {

    /**
     * Class that represent the ability type of the leader cards
     * */
    public enum AbilityType {
        DISCOUNT,ADDPROD,EXTRASLOT,CONVERTWHITE
    }

    private AbilityType abilityType;
    private Resources.ResType oneResType;
    private Resources resourceHolder = new Resources();
    /**
     * Constructor -> initialize the Res type of the card and the ability
     * @param abilityType the ability of the card
     * @param resType the type of resources of the card
     * */
    public SpecialAbility(AbilityType abilityType, Resources.ResType resType){
        this.abilityType = abilityType;
        this.oneResType = resType;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public Resources.ResType getResType(){
        return oneResType;
    }
    /**
     * method that keep the resources in the leader card with extra slot ability
     * @param resourcesToBeAdded the res that has to be added on the card
     * */
    public int addToHolder(Resources resourcesToBeAdded){
        if (resourcesToBeAdded.isThisOneType() && oneResType == resourcesToBeAdded.getOnlyType()){
            int numberToAdd = resourcesToBeAdded.sumOfValues();
            int discarded = numberToAdd + this.resourceHolder.sumOfValues() - 2;
            resourceHolder.add(resourcesToBeAdded);
            if(discarded > 0){
                resourceHolder.subtract(this.oneResType, discarded);
            }
            if(discarded < 0) return 0;
            return discarded;
        }
        return 0;
    }

    public Resources getResourcesAtSlot(){
        return resourceHolder;
    }
    /**
     * method that remove res from extra slot leader card during activation or buying action
     * @param res the res has to be subtracted
     * */
    public void subtractFromExtraSlot(Resources res){
        resourceHolder.subtract(res);
    }

    @Override
    public String toString() {
        return "SpecialAbility{" +
                "abilityType=" + abilityType +
                ", oneResType=" + oneResType +
                ", resourceHolder=" + resourceHolder +
                '}';
    }

    public String describeSpecialAbility(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ability: ");
        switch (abilityType){
            case ADDPROD:
                stringBuilder.append(oneResType.getFirstAnsiPart() + "1 " + oneResType.getSecondAnsiPart());
                stringBuilder.append(" —> [1 ?] + ");
                stringBuilder.append(Resources.ResType.FAITH.getFirstAnsiPart() + "1 " + Resources.ResType.FAITH.getSecondAnsiPart());
                break;
            case DISCOUNT:
                stringBuilder.append(oneResType.getFirstAnsiPart() + "-1 " + oneResType.getSecondAnsiPart());
                stringBuilder.append(" for dev card costs");
                break;
            case EXTRASLOT:
                stringBuilder.append("extra slot for " + oneResType.getFirstAnsiPart() + "2 " + oneResType.getSecondAnsiPart());
                stringBuilder.append(", currently put " + oneResType.getFirstAnsiPart() + resourceHolder.sumOfValues() + " " + oneResType.getSecondAnsiPart());
                break;
            case CONVERTWHITE:
                stringBuilder.append("\u26aa" + " = " + oneResType.getFirstAnsiPart() + "1 " + oneResType.getSecondAnsiPart());
                break;
        }
        return stringBuilder.toString();
    }
}
