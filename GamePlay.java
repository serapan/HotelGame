import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;



public class GamePlay {
    private boolean goneThroughTownHall = false;
    private boolean goneThroughBank = false;
    private boolean stoppedAtPayNode = false;
    private boolean stoppedAtBuyHNode = false;
    private boolean stoppedAtReBuyHNode = false;
    private HashMap<String, Hotel> hotels = new HashMap<String, Hotel>();
    private ArrayList<MapNode> board = new ArrayList<>();
    private HashMap<String, HashMap<String, MapNode>> townHallMapNodes = new HashMap<String, HashMap<String, MapNode>>();
    private Player player1;
    private Player player2;
    private Player player3;
    private Player bank;
    private Player currPlayer;
    private ArrayList<Player> turns;
    private int who = 0;
    private boolean hasRolledTurnDice = false;
    private boolean hasTakenMoney = false;
    private boolean hasRequestOrEntrance = false;



    public GamePlay() {
        this.player1 = new Player(12000, 1, new ArrayList<Hotel>(), new ArrayList<MapNode>(), new ArrayList<MapNode>(), null, 12000);
        this.player2 = new Player(12000, 2, new ArrayList<Hotel>(), new ArrayList<MapNode>(), new ArrayList<MapNode>(), null, 12000);
        this.player3 = new Player(12000, 3, new ArrayList<Hotel>(), new ArrayList<MapNode>(), new ArrayList<MapNode>(), null, 12000);
        this.bank = new Player(0, 4, new ArrayList<Hotel>(), new ArrayList<MapNode>(), new ArrayList<MapNode>(), null, 0);
        this.currPlayer = null;
        this.turns = new ArrayList<>();
    }


    public boolean isGoneThroughTownHall() {
        return this.goneThroughTownHall;
    }

    public boolean getGoneThroughTownHall() {
        return this.goneThroughTownHall;
    }

    public void setGoneThroughTownHall(boolean goneThroughTownHall) {
        this.goneThroughTownHall = goneThroughTownHall;
    }

    public boolean isGoneThroughBank() {
        return this.goneThroughBank;
    }

    public boolean getGoneThroughBank() {
        return this.goneThroughBank;
    }

    public void setGoneThroughBank(boolean goneThroughBank) {
        this.goneThroughBank = goneThroughBank;
    }

    public boolean isStoppedAtPayNode() {
        return this.stoppedAtPayNode;
    }

    public boolean getStoppedAtPayNode() {
        return this.stoppedAtPayNode;
    }

    public void setStoppedAtPayNode(boolean stoppedAtPayNode) {
        this.stoppedAtPayNode = stoppedAtPayNode;
    }

    public boolean isStoppedAtBuyHNode() {
        return this.stoppedAtBuyHNode;
    }

    public boolean getStoppedAtBuyHNode() {
        return this.stoppedAtBuyHNode;
    }

    public void setStoppedAtBuyHNode(boolean stoppedAtBuyHNode) {
        this.stoppedAtBuyHNode = stoppedAtBuyHNode;
    }

    public boolean isStoppedAtReBuyHNode() {
        return this.stoppedAtReBuyHNode;
    }

    public boolean getStoppedAtReBuyHNode() {
        return this.stoppedAtReBuyHNode;
    }

    public void setStoppedAtReBuyHNode(boolean stoppedAtReBuyHNode) {
        this.stoppedAtReBuyHNode = stoppedAtReBuyHNode;
    }

    

    public HashMap<String,Hotel> getHotels() {
        return this.hotels;
    }

    public void setHotels(HashMap<String,Hotel> hotels) {
        this.hotels = hotels;
    }

    public ArrayList<MapNode> getBoard() {
        return this.board;
    }

    public void setBoard(ArrayList<MapNode> board) {
        this.board = board;
    }

    public HashMap<String,HashMap<String,MapNode>> getTownHallMapNodes() {
        return this.townHallMapNodes;
    }

    public void setTownHallMapNodes(HashMap<String,HashMap<String,MapNode>> townHallMapNodes) {
        this.townHallMapNodes = townHallMapNodes;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return this.player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    public Player getCurrPlayer() {
        return this.currPlayer;
    }

    public void setCurrPlayer(Player currPlayer) {
        this.currPlayer = currPlayer;
    }

    public ArrayList<Player> getTurns() {
        return this.turns;
    }

    public void setTurns(ArrayList<Player> turns) {
        this.turns = turns;
    }


    public int getWho() {
        return this.who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public boolean isHasRolledTurnDice() {
        return this.hasRolledTurnDice;
    }

    public boolean getHasRolledTurnDice() {
        return this.hasRolledTurnDice;
    }

    public void setHasRolledTurnDice(boolean hasRolledTurnDice) {
        this.hasRolledTurnDice = hasRolledTurnDice;
    }

    public boolean isHasTakenMoney() {
        return this.hasTakenMoney;
    }

    public boolean getHasTakenMoney() {
        return this.hasTakenMoney;
    }

    public void setHasTakenMoney(boolean hasTakenMoney) {
        this.hasTakenMoney = hasTakenMoney;
    }


    public boolean isHasRequestOrEntrance() {
        return this.hasRequestOrEntrance;
    }

    public boolean getHasRequestOrEntrance() {
        return this.hasRequestOrEntrance;
    }

    public void setHasRequestOrEntrance(boolean hasRequestOrEntrance) {
        this.hasRequestOrEntrance = hasRequestOrEntrance;
    }

    public Hotel readHotel(String file, Integer id) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        String name = scanner.nextLine();
        String[] temp1 = scanner.nextLine().split(",");
        Integer toBuyCost = Integer.parseInt(temp1[0]);
        Integer toSellAfterBuyCost = Integer.parseInt(temp1[1]);
        Integer entranceCost = Integer.parseInt(scanner.nextLine());
        String[] temp2 = scanner.nextLine().split(",");
        Integer basicBuildCost = Integer.parseInt(temp2[0]);
        Integer dailyCost = Integer.parseInt(temp2[1]);
        ArrayList<ArrayList<Integer>> expansions = new ArrayList<>();
        while(scanner.hasNext()){
            String[] line = scanner.nextLine().split(",");
            ArrayList<Integer> expansion = new ArrayList<Integer>();
            expansion.add(Integer.parseInt(line[0]));
            expansion.add(Integer.parseInt(line[1]));
            expansion.add(0);
            expansions.add(expansion);
        }
        scanner.close();  
        return new Hotel(id, name, toBuyCost, toSellAfterBuyCost, entranceCost, basicBuildCost, dailyCost, expansions, false, null, false, false);
    }

    public void readBoard() throws FileNotFoundException {
        Random rand = new Random();
        int x = rand.nextInt(2) + 1;
        String path = null;
        if(x == 1){
            path = "default/";
        }
        else if(x == 2){
            path = "simple/";
        }
        Scanner scanner = new Scanner(new File(path + "board.txt"));
        String [][] boardArray = new String[12][15];
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 15; j++){
                boardArray[i][j] = "NAS";
            }
        }
        int pos_i = 0;
        int pos_j = 0;
        for(int i = 0; i < 12; i++){
            String[] line = scanner.nextLine().split(",");
            for(int j = 0; j < line.length; j++){
                if(!hotels.containsKey(line[j]) && !(line[j].equals("E") || line[j].equals("H") || line[j].equals("B") || line[j].equals("C") || line[j].equals("S") || line[j].equals("F"))){
                    Hotel hotel = readHotel(path + line[j] + ".txt", Integer.parseInt(line[j]));
                    hotels.put(line[j], hotel);
                }
                boardArray[i][j] = line[j];
                if(line[j].equals("S")){
                    pos_i = i;
                    pos_j = j;
                }
            }
        }
        /*for(int i = 0; i < 12; i++){
            for(int j = 0; j < 15; j++){
                System.out.print(boardArray[i][j] + ", ");
            }
            System.out.println();
        }*/
        scanner.close(); 
        MapNode start = new MapNode(pos_i, pos_j, "S", null, null, null, null, false);
        MapNode curr = start;
        int i = pos_i;
        int j = pos_j;
        while(true){
            ArrayList<String> borders = new ArrayList<>();
            if(j+1 < 15 && (boardArray[i][j+1].equals("E") || boardArray[i][j+1].equals("H") || boardArray[i][j+1].equals("B") || boardArray[i][j+1].equals("C") || boardArray[i][j+1].equals("S"))){
                borders.add(boardArray[i+1][j]);
                borders.add(boardArray[i-1][j]);
                borders.add(boardArray[i][j-1]);
                j = j + 1;
            }
            else if(i+1 < 12 && (boardArray[i+1][j].equals("E") || boardArray[i+1][j].equals("H") || boardArray[i+1][j].equals("B") || boardArray[i+1][j].equals("C") || boardArray[i+1][j].equals("S"))){
                borders.add(boardArray[i][j+1]);
                borders.add(boardArray[i-1][j]);
                borders.add(boardArray[i][j-1]);
                i = i + 1;
            }
            else if(j-1 > -1 && (boardArray[i][j-1].equals("E") || boardArray[i][j-1].equals("H") || boardArray[i][j-1].equals("B") || boardArray[i][j-1].equals("C") || boardArray[i][j-1].equals("S"))){
                borders.add(boardArray[i+1][j]);
                borders.add(boardArray[i-1][j]);
                borders.add(boardArray[i][j+1]);
                j = j - 1;
            }
            else if(i-1 > -1 && (boardArray[i-1][j].equals("E") || boardArray[i-1][j].equals("H") || boardArray[i-1][j].equals("B") || boardArray[i-1][j].equals("C") || boardArray[i-1][j].equals("S"))){
                borders.add(boardArray[i+1][j]);
                borders.add(boardArray[i][j+1]);
                borders.add(boardArray[i][j-1]);
                i = i - 1;
            }
            if(curr.getId().equals("H") || curr.getId().equals("E")){
                while(!borders.isEmpty()){
                    String id = borders.remove(0);
                    if(!id.equals("E") && !id.equals("H") && !id.equals("B") && !id.equals("C") && !id.equals("F") && !id.equals("S") && !id.equals("NAS")){
                        Hotel hotel = hotels.get(id);
                        //Hotel temp = new Hotel(hotel.getId(), hotel.getName(), hotel.getToBuyCost(), hotel.getToSellAfterBuyCost(), hotel.getEntranceCost(), hotel.getBasicBuildCost(), hotel.getDailyCost(), hotel.getExpansions());
                        curr.addToCanBuildHotels(hotel);
                        if(curr.getId().equals("E")){
                            if(!townHallMapNodes.containsKey(id)){
                                townHallMapNodes.put(id, new HashMap<String, MapNode>());
                            }
                            HashMap<String, MapNode> hm = townHallMapNodes.get(id);
                            if(!hm.containsKey(Integer.toString(curr.getX()) + ", " + Integer.toString(curr.getY()))){
                                hm.put(Integer.toString(curr.getX()) + ", " + Integer.toString(curr.getY()), curr);
                            }
                        }
                    }
                }
            }
            MapNode next = new MapNode(i, j, boardArray[i][j], null, null, null, null, false);
            curr.setCanGoNode(next);
            board.add(curr);
            curr = next;
            boardArray[i][j] = "NAS";
            if(i == pos_i && j == pos_j){
                break;
            }
        }
        curr.setCanGoNode(start); 
    }

    public void defineTurns(){
        this.player1.setIsAt(board.get(0));
        this.player2.setIsAt(board.get(0));
        this.player3.setIsAt(board.get(0));
        Random rand1 = new Random();
        Random rand2 = new Random();
        int first = rand1.nextInt(3) + 1;
        int second = rand2.nextInt(2);
        if(first == 1){
            this.turns.add(this.player1);
            if(second == 0){
                this.turns.add(this.player2);
                this.turns.add(this.player3);
            }  
            else{
                this.turns.add(this.player3);
                this.turns.add(this.player2);
            }
        }
        else if(first == 2){
            this.turns.add(this.player2);
            if(second == 0){
                this.turns.add(this.player1);
                this.turns.add(this.player3);
            }  
            else{
                this.turns.add(this.player3);
                this.turns.add(this.player1);
            }

        }
        else if(first == 3){
            this.turns.add(this.player3);
            if(second == 0){
                this.turns.add(this.player2);
                this.turns.add(this.player1);
            }  
            else{
                this.turns.add(this.player1);
                this.turns.add(this.player2);
            }
        }
        this.currPlayer = this.turns.get(0);
    }

    public Integer rollDice(){
        this.hasRolledTurnDice = true;
        Random rand1 = new Random();
        return rand1.nextInt(6) + 1;
    }

    public Integer findNewMapNode(){
        this.goneThroughTownHall = false;
        this.goneThroughBank = false;
        this.stoppedAtPayNode = false;
        this.stoppedAtBuyHNode = false;
        this.stoppedAtReBuyHNode = false;
        Integer x = this.rollDice();
        for(int i = 0; i < x; i++){
            this.currPlayer.setIsAt(this.currPlayer.getIsAt().getCanGoNode());       
            if(this.currPlayer.getIsAt().getId().equals("B")){
                /*if(i == 0){
                    continue;
                }*/
                this.goneThroughBank = true;
            }
            if(this.currPlayer.getIsAt().getId().equals("C")){
               /* if(i == 0){
                    continue;
                }*/
                this.goneThroughTownHall = true;
            }
        }
        int count = 0;
        if(this.currPlayer.getIsAt() == this.player1.getIsAt()){
            count++;
        }
        if(this.currPlayer.getIsAt() == this.player2.getIsAt()){
            count++;
        }
        if(this.currPlayer.getIsAt() == this.player3.getIsAt()){
            count++;
        }
        while(count != 1){
            this.currPlayer.setIsAt(this.currPlayer.getIsAt().getCanGoNode());       
            if(this.currPlayer.getIsAt().getId().equals("B")){
                this.goneThroughBank = true;
            }
            if(this.currPlayer.getIsAt().getId().equals("C")){
                this.goneThroughTownHall = true;
            }
            count--;
        }
        return x;
    }

    public void whatHappens(){
        if(this.currPlayer.getIsAt().getId().equals("B")){
            this.goneThroughBank = true;
            return;
        }
        else if(this.currPlayer.getIsAt().getId().equals("C")){
            this.goneThroughTownHall = true;
            return;
        }
        else if(this.currPlayer.getIsAt().getId().equals("H")){
            if(this.getCurrPlayer().getIsAt().getOwner()==null){
                for(Hotel h : this.getCurrPlayer().getIsAt().getCanBuildHotels()){
                    if(h.isBought() == false){
                        this.stoppedAtBuyHNode = true;
                    }
                }
            }
            else{
                Hotel h = this.getCurrPlayer().getIsAt().getBuiltHotel();
                if(h.isBuilt() == false && this.getCurrPlayer()!= this.getCurrPlayer().getIsAt().getOwner()){
                    this.stoppedAtReBuyHNode = true;
                }
            }
        }
        else if(this.currPlayer.getIsAt().getId().equals("E")){
            if(this.currPlayer.getIsAt().getOwner() != null){
                if(!this.currPlayer.getIsAt().getOwner().getId().equals(this.currPlayer.getId())){
                    if(this.currPlayer.getIsAt().getBuiltHotel() != null || this.currPlayer.getIsAt().isIsEntrance()==true){
                        this.stoppedAtPayNode = true;
                    }
                }
            }
        }
    }

    public void cleanUp(){
        this.board = new ArrayList<>();
        this.townHallMapNodes = new HashMap<String, HashMap<String, MapNode>>();
        this.hotels = new HashMap<String, Hotel>();
        this.player1.setHasENodes(new ArrayList<>());
        this.player1.setHasHNodes(new ArrayList<>());
        this.player1.setHasHotels(new ArrayList<>());
        this.player1.setMoney(12000);
        this.player1.setMaxMoney(12000);
        this.player1.setLost(false);
        this.player2.setHasENodes(new ArrayList<>());
        this.player2.setHasHNodes(new ArrayList<>());
        this.player2.setHasHotels(new ArrayList<>());
        this.player2.setMoney(12000);
        this.player2.setMaxMoney(12000);
        this.player2.setLost(false);
        this.player3.setHasENodes(new ArrayList<>());
        this.player3.setHasHNodes(new ArrayList<>());
        this.player3.setHasHotels(new ArrayList<>());
        this.player3.setMoney(12000);
        this.player3.setMaxMoney(12000);
        this.player3.setLost(false);
        this.bank.setHasENodes(new ArrayList<>());
        this.bank.setHasHNodes(new ArrayList<>());
        this.bank.setHasHotels(new ArrayList<>());
        this.bank.setMoney(12000);
        this.bank.setMaxMoney(12000);
        this.goneThroughTownHall = false;
        this.goneThroughBank = false;
        this.stoppedAtBuyHNode = false;
        this.stoppedAtPayNode = false;
        this.stoppedAtReBuyHNode = false;
        this.hasRolledTurnDice = false;
        this.hasTakenMoney = false;
        this.hasRequestOrEntrance = false;
        this.who = 0;
        try{
            this.readBoard();
        }
        catch (FileNotFoundException f){
            ;
        }
        this.player1.setIsAt(board.get(0));
        this.player2.setIsAt(board.get(0));
        this.player3.setIsAt(board.get(0));
        this.currPlayer = this.turns.get(0);
    }

    public void playNext(){
        who++;
        if(who > this.getTurns().size()-1){
            who = 0;
        }
        this.currPlayer = this.turns.get(who);
        this.goneThroughTownHall = false;
        this.goneThroughBank = false;
        this.stoppedAtBuyHNode = false;
        this.stoppedAtPayNode = false;
        this.stoppedAtReBuyHNode = false;
        this.hasRolledTurnDice = false;
        this.hasTakenMoney = false;
        this.hasRequestOrEntrance = false;
    }

    public void takeMoneyFromBank(){
        this.currPlayer.earns(1000);
        this.hasTakenMoney = true;
    }

    public int requestBuildingDice(){
        Random rand1 = new Random();
        return rand1.nextInt(100) + 1;
    }

    public MapNode requestBuildingRandomMapNode(String id){
        ArrayList<MapNode> list = new ArrayList(this.townHallMapNodes.get(id).values());
        Random rand1 = new Random();
        if(!(list.size() > 0)){
            return null;
        }
        int x = rand1.nextInt(list.size());
        MapNode mn = list.get(x);
        townHallMapNodes.get(id).remove(Integer.toString(mn.getX()) + ", " + Integer.toString(mn.getY()));
        return mn;
    }

    public boolean loses(){
        this.turns.remove(this.currPlayer);
        this.currPlayer.setIsAt(null);
        for(Hotel h : this.currPlayer.getHasHotels()){
            this.bank.getHasHotels().add(h);
            h.setOwnedByBank(true);
            h.setOwner(this.bank);
        }
        this.currPlayer.getHasHotels().clear();
        for(MapNode mn : this.currPlayer.getHasHNodes()){
            this.bank.getHasHNodes().add(mn);
            mn.setOwner(this.bank);
        }
        this.currPlayer.getHasHNodes().clear();
        for(MapNode mn : this.currPlayer.getHasENodes()){
            this.bank.getHasENodes().add(mn);
            mn.setOwner(this.bank);
        }
        this.currPlayer.getHasENodes().clear();;
        if(this.turns.size() != 1){
            this.playNext();
            return false;
        }
        else{
            return true;
        }
    }
}