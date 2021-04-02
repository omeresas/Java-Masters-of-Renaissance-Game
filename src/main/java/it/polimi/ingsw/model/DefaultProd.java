package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumclasses.ResType;

public class DefaultProd {
    private Resources LHS;
    private Resources RHS;

    public DefaultProd(){
        this.LHS = new Resources();
        this.RHS = new Resources();
    }

    public void putTopLeftResource(ResType L1){
        // code for GUI must be different since for now the whole LHS is represented by one Resources object
        this.LHS.add(L1, 1);
    }

    public void putBottomLeftResource(ResType L2){
        // code for GUI must be different since for now the whole LHS is represented by one Resources object
        this.LHS.add(L2, 1);
    }

    public void putRightResource(ResType R){
        this.RHS.add(R,1);
    }

    public void produce(){
        this.LHS.resetToZero();
        this.RHS.resetToZero();
    }
}