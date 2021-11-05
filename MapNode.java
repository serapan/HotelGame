import java.util.ArrayList;

public  class MapNode {
    private Integer x;
    private Integer y;
    private String id;
    private MapNode canGoNode;
    private Player owner;
    private ArrayList<Hotel> canBuildHotels;
    private Hotel builtHotel;
    private boolean isEntrance;


    public MapNode(Integer x, Integer y, String id, MapNode canGoNode, Player owner, ArrayList<Hotel> canBuildHotels, Hotel builtHotel, boolean isEntrance) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.canGoNode = canGoNode;
        this.owner = owner;
        this.canBuildHotels = new ArrayList<>();
        this.builtHotel = builtHotel;
        this.isEntrance = isEntrance;
    }


    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MapNode getCanGoNode() {
        return this.canGoNode;
    }

    public void setCanGoNode(MapNode canGoNode) {
        this.canGoNode = canGoNode;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public ArrayList<Hotel> getCanBuildHotels() {
        return this.canBuildHotels;
    }

    public void setCanBuildHotels(ArrayList<Hotel> canBuildHotels) {
        this.canBuildHotels = canBuildHotels;
    }

    public Hotel getBuiltHotel() {
        return this.builtHotel;
    }

    public void setBuiltHotel(Hotel builtHotel) {
        this.builtHotel = builtHotel;
    }
    
    public void addToCanBuildHotels(Hotel h){
        this.canBuildHotels.add(h);
    }

    public boolean isIsEntrance() {
        return this.isEntrance;
    }

    public boolean getIsEntrance() {
        return this.isEntrance;
    }

    public void setIsEntrance(boolean isEntrance) {
        this.isEntrance = isEntrance;
    }
    
}
