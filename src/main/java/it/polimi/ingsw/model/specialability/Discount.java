package it.polimi.ingsw.model.specialability;

import it.polimi.ingsw.model.enumclasses.AbilityType;

public class Discount implements SpecialAbility {
    @Override
    public void activate() {

    }

    @Override
    public AbilityType getType() {
        return AbilityType.DISCOUNT;
    }
}
