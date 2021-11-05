import java.util.ArrayList;

public class Player {

    private Integer money;
    private Integer id;
    private ArrayList<Hotel> hasHotels;
    private ArrayList<MapNode> hasENodes;
    private ArrayList<MapNode> hasHNodes;
    private MapNode isAt;
    private Integer maxMoney;
    private boolean lost = false;

    public Player(Integer money, Integer id, ArrayList<Hotel> hasHotels, ArrayList<MapNode> hasENodes, ArrayList<MapNode> hasHNodes, MapNode isAt, Integer maxMoney) {
        this.money = money;
        this.id = id;
        this.hasHotels = hasHotels;
        this.hasENodes = hasENodes;
        this.hasHNodes = hasHNodes;
        this.isAt = isAt;
        this.maxMoney = maxMoney;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Hotel> getHasHotels() {
        return this.hasHotels;
    }

    public void setHasHotels(ArrayList<Hotel> hasHotels) {
        this.hasHotels = hasHotels;
    }

    public ArrayList<MapNode> getHasENodes() {
        return this.hasENodes;
    }

    public void setHasENodes(ArrayList<MapNode> hasENodes) {
        this.hasENodes = hasENodes;
    }

    public ArrayList<MapNode> getHasHNodes() {
        return this.hasHNodes;
    }

    public void setHasHNodes(ArrayList<MapNode> hasHNodes) {
        this.hasHNodes = hasHNodes;
    }

    public MapNode getIsAt() {
        return this.isAt;
    }

    public void setIsAt(MapNode isAt) {
        this.isAt = isAt;
    }

    public Integer getMaxMoney() {
        return this.maxMoney;
    }

    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }


    public boolean isLost() {
        return this.lost;
    }

    public boolean getLost() {
        return this.lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean spends(Integer x){
        this.money = this.money - x;
        if(this.money <= 0){
            this.money = 0;
            this.lost = true;
            return true;
        }
        return false;
    }

    public void earns(Integer x){
        this.money = this.money + x;
        if(this.money > this.maxMoney){
            this.maxMoney = this.money;
        }
    }
}