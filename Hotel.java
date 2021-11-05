import java.util.ArrayList;

public class Hotel {
    private Integer id;
    private String name;
    private Integer toBuyCost;
    private Integer toSellAfterBuyCost;
    private Integer entranceCost;
    private Integer basicBuildCost;
    private Integer dailyCost;
    private ArrayList<ArrayList<Integer>> expansions;
    private boolean bought;
    private boolean built;
    private boolean ownedByBank;
    private Player owner;


    public Hotel(Integer id, String name, Integer toBuyCost, Integer toSellAfterBuyCost, Integer entranceCost, Integer basicBuildCost, Integer dailyCost, ArrayList<ArrayList<Integer>> expansions, boolean bought, Player owner, boolean built, boolean ownedByBank) {
        this.id = id;
        this.name = name;
        this.toBuyCost = toBuyCost;
        this.toSellAfterBuyCost = toSellAfterBuyCost;
        this.entranceCost = entranceCost;
        this.basicBuildCost = basicBuildCost;
        this.dailyCost = dailyCost;
        this.expansions = expansions;
        this.bought = bought;
        this.owner = owner;
        this.built = built;
        this.ownedByBank = ownedByBank;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getToBuyCost() {
        return this.toBuyCost;
    }

    public void setToBuyCost(Integer toBuyCost) {
        this.toBuyCost = toBuyCost;
    }

    public Integer getToSellAfterBuyCost() {
        return this.toSellAfterBuyCost;
    }

    public void setToSellAfterBuyCost(Integer toSellAfterBuyCost) {
        this.toSellAfterBuyCost = toSellAfterBuyCost;
    }

    public Integer getEntranceCost() {
        return this.entranceCost;
    }

    public void setEntranceCost(Integer entranceCost) {
        this.entranceCost = entranceCost;
    }

    public Integer getBasicBuildCost() {
        return this.basicBuildCost;
    }

    public void setBasicBuildCost(Integer basicBuildCost) {
        this.basicBuildCost = basicBuildCost;
    }

    public Integer getDailyCost() {
        return this.dailyCost;
    }

    public void setDailyCost(Integer dailyCost) {
        this.dailyCost = dailyCost;
    }

    public ArrayList<ArrayList<Integer>> getExpansions() {
        return this.expansions;
    }

    public void setExpansions(ArrayList<ArrayList<Integer>> expansions) {
        this.expansions = expansions;
    }
    
    public boolean isBought() {
        return this.bought;
    }

    public boolean getBought() {
        return this.bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }


    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public boolean isBuilt() {
        return this.built;
    }

    public boolean getBuilt() {
        return this.built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }


    public boolean isOwnedByBank() {
        return this.ownedByBank;
    }

    public boolean getOwnedByBank() {
        return this.ownedByBank;
    }

    public void setOwnedByBank(boolean ownedByBank) {
        this.ownedByBank = ownedByBank;
    }


}