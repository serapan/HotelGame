import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Region;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class HotelApp extends Application{
    private static GamePlay gp;
    private static Stage primaryStage = new Stage();
    private static double WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static double HEIGHT = Screen.getPrimary().getBounds().getHeight();

    public void display(){
        Scene scene = loadScene();
        primaryStage.setTitle("MediaLab Hotel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene loadScene(){
        AnchorPane root = new AnchorPane();
        MenuBar menuBar = new MenuBar();
        GridPane board = new GridPane();
        HBox info = new HBox();
        VBox options = new VBox();


        /* -------- Board -------- */

        //board.setGridLinesVisible(true);
        for(MapNode box : gp.getBoard()){
            StackPane sp = new StackPane();
            Rectangle rec = new Rectangle();
            rec.setWidth((WIDTH/23.1325)); 
            rec.setHeight((WIDTH/23.1325));
            if(box.getId().equals("B")){
                rec.setFill(Color.LIGHTPINK);
                rec.setStroke(Color.BLACK);
                Text txt = new Text("B");
                txt.setStyle("-fx-font: normal bold 13px 'serif' ");
                sp.getChildren().addAll(rec, txt);   
            }
            else if(box.getId().equals("C")){
                rec.setFill(Color.LIGHTPINK);
                rec.setStroke(Color.BLACK);
                Text txt = new Text("C");
                txt.setStyle("-fx-font: normal bold 13px 'serif' ");
                sp.getChildren().addAll(rec, txt);   
            }
            else if(box.getId().equals("H")){
                rec.setFill(Color.LIGHTCORAL);
                rec.setStroke(Color.BLACK);
                Text txt = new Text("H");
                txt.setStyle("-fx-font: normal bold 13px 'serif' ");
                sp.getChildren().addAll(rec, txt);   
            }
            else if(box.getId().equals("E")){
                rec.setFill(Color.LIGHTSEAGREEN);
                rec.setStroke(Color.BLACK);
                Text txt = new Text("E");
                txt.setStyle("-fx-font: normal bold 13px 'serif' ");
                sp.getChildren().addAll(rec, txt);   
            }
            else if(box.getId().equals("S")){
                rec.setFill(Color.LIGHTPINK);
                rec.setStroke(Color.BLACK);
                Text txt = new Text("S");
                txt.setStyle("-fx-font: normal bold 13px 'serif' ");
                sp.getChildren().addAll(rec, txt);   
            }
            if(box == gp.getPlayer1().getIsAt() && gp.getPlayer1().isLost() == false){
                Circle pl1 = new Circle();
                pl1.setCenterX(WIDTH/192); 
                pl1.setCenterY(WIDTH/192); 
                pl1.setRadius(WIDTH/192);
                pl1.setFill(Color.BLUE);
                sp.getChildren().add(pl1);
                sp.setAlignment(pl1, Pos.TOP_LEFT); 
            }
            if(box == gp.getPlayer2().getIsAt() && gp.getPlayer2().isLost() == false){
                Circle pl2 = new Circle();
                pl2.setCenterX(WIDTH/48); 
                pl2.setCenterY(WIDTH/192); 
                pl2.setRadius(WIDTH/192);
                pl2.setFill(Color.RED);
                sp.getChildren().add(pl2);
                sp.setAlignment(pl2, Pos.TOP_CENTER);
            }
            if(box == gp.getPlayer3().getIsAt() && gp.getPlayer3().isLost() == false){
                Circle pl3 = new Circle();
                pl3.setCenterX(WIDTH/27.4285); 
                pl3.setCenterY(WIDTH/192); 
                pl3.setRadius(WIDTH/192);
                pl3.setFill(Color.GREEN);
                sp.getChildren().add(pl3);
                sp.setAlignment(pl3, Pos.TOP_RIGHT); 
            }
            if(box.getId().equals("E")){
                if(box.getOwner()!=null){
                    if(box.getOwner().getId() != 4){
                        Text text1 = new Text("PL" + Integer.toString(box.getOwner().getId()));
                        sp.getChildren().add(text1);
                        sp.setAlignment(text1, Pos.BOTTOM_RIGHT);
                    }
                    else{
                        Text text1 = new Text("BANK");
                        sp.getChildren().add(text1);
                        sp.setAlignment(text1, Pos.BOTTOM_RIGHT);
                    }
                    if(box.isIsEntrance()){
                        Text text2 = new Text("ENT," + Integer.toString(box.getBuiltHotel().getId()));
                        sp.getChildren().add(text2);
                        sp.setAlignment(text2, Pos.BOTTOM_LEFT);
                    }
                    else{
                        Text text3 = new Text("BUILD," + Integer.toString(box.getBuiltHotel().getId()));
                        sp.getChildren().add(text3);
                        sp.setAlignment(text3, Pos.BOTTOM_LEFT);
                    }
                }  
            }
            if(box.getId().equals("H")){
                if(box.getOwner()!=null){
                    if(box.getOwner().getId() != 4){
                        Text text1 = new Text("PL" + Integer.toString(box.getOwner().getId()));
                        sp.getChildren().add(text1);
                        sp.setAlignment(text1, Pos.BOTTOM_RIGHT);
                    }
                    else{
                        Text text1 = new Text("BANK");
                        sp.getChildren().add(text1);
                        sp.setAlignment(text1, Pos.BOTTOM_RIGHT);
                    }
                    Text text2 = new Text("HOTEL," + Integer.toString(box.getBuiltHotel().getId()));
                    sp.getChildren().add(text2);
                    sp.setAlignment(text2, Pos.BOTTOM_LEFT);
                }  
            }
            board.add(sp, box.getY(), box.getX());
        }



        /* -------- Menu -------- */

        menuBar.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Menu menuGame = new Menu("Game");
        menuGame.setStyle("-fx-font: normal bold 20px 'serif' ");
        Menu menuStatistics = new Menu("Statistics");
        menuStatistics.setStyle("-fx-font: normal bold 20px 'serif' ");
        menuBar.getMenus().addAll(menuGame, menuStatistics);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        MenuItem hotelsItem = new MenuItem("Hotels");
        menuStatistics.getItems().add(hotelsItem);
        MenuItem entrancesItem = new MenuItem("Entrances");
        menuStatistics.getItems().add(entrancesItem);
        MenuItem profitsItem = new MenuItem("Profits");
        menuStatistics.getItems().add(profitsItem);
        MenuItem startItem = new MenuItem("Start");
        MenuItem stopItem = new MenuItem("Stop");
        MenuItem cardsItem = new MenuItem("Cards");
        MenuItem exitItem = new MenuItem("Exit");
        menuGame.getItems().addAll(startItem, stopItem, cardsItem, exitItem);


        /* -------- /Games/Start -------- */

        startItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert startMsg = new Alert(AlertType.CONFIRMATION);
                startMsg.setTitle("Restart");
                startMsg.setHeaderText("Quit current game and start new");
                String out = "You are aboout to start a new Game.Every progress you 've made so far will be lost.\nAre you sure?";
                startMsg.setContentText(out);
                startMsg.setResizable(true);
                startMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                startMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //startMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                startMsg.getDialogPane().setPrefSize(650.0, 250.0);
                Optional<ButtonType> result = startMsg.showAndWait();
                if(result != null && result.get() == ButtonType.OK){
                    restartGame();
                }
            }
        });

        stopItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert stopMsg = new Alert(AlertType.INFORMATION);
                stopMsg.setTitle("Stop");
                stopMsg.setHeaderText("The game is paused");
                String out = "If you want to continue the game please click 'OK'";
                stopMsg.setContentText(out);
                stopMsg.setResizable(true);
                stopMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                stopMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //stopMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                stopMsg.getDialogPane().setPrefSize(650.0, 250.0);
                Optional<ButtonType> result = stopMsg.showAndWait();
            }
        });

        /* -------- /Games/Cards -------- */

        cardsItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                ArrayList<String> choices = new ArrayList<>();
                for(String id : gp.getHotels().keySet()){
                    Hotel h = gp.getHotels().get(id);
                    String s = Integer.toString(h.getId()) + " - " + h.getName();
                    choices.add(s);
                }
                ChoiceDialog<String> cardMsg = new ChoiceDialog(choices.get(0), choices);
                cardMsg.setTitle("Cards");
                cardMsg.setHeaderText("View Info About Hotels");
                String out1 = "Pick a Hotel : ";
                cardMsg.setContentText(out1);
                cardMsg.setResizable(true);
                cardMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                cardMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //cardMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                cardMsg.getDialogPane().setPrefSize(650.0, 250.0);
                Optional<String> result = cardMsg.showAndWait();
                if(result!= null && result.get() != null){
                    Hotel hotel = gp.getHotels().get(result.get().substring(0, 1));
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle(result.get());
                    msg.setHeaderText("Info about " + result.get());
                    String out = "Id : " + hotel.getId() + "\n\n\n" + "Name : " + hotel.getName() + "\n\n\n" + "Buy Cost : " + hotel.getToBuyCost();
                    out = out + "\t" + "Lower Bound Buy Cost : " + hotel.getToSellAfterBuyCost() + "\n\n\n" + "Entrance Cost : " + hotel.getEntranceCost() + "\n\n\n";
                    out = out + "Basic Build Cost : " + hotel.getBasicBuildCost() + "\t" + "Daily Cost : " + hotel.getDailyCost() + "\n\n\n" + "Expansions : \n\n\n";
                    for(int i = 0; i < hotel.getExpansions().size()-1; i++){
                        String s = "\t\tCost : " + hotel.getExpansions().get(i).get(0) + "\t" + "Daily Cost : " + hotel.getExpansions().get(i).get(1) + "\n\n\n";
                        out = out + s; 
                    }
                    out = out + "\t\tOutsideCost : " + hotel.getExpansions().get(hotel.getExpansions().size()-1).get(0) + "\t" + "Daily Cost : " + hotel.getExpansions().get(hotel.getExpansions().size()-1).get(1) + "\n";
                    msg.setContentText(out);
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    //msg.getDialogPane().setPrefSize(WIDTH/2.021, HEIGHT/1.44);
                    msg.getDialogPane().setPrefSize(650.0, 350.0);
                    Optional<ButtonType> result1 = msg.showAndWait();
                }
            }
        });


        /* -------- /Games/Exit -------- */

        exitItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert exitMsg = new Alert(AlertType.CONFIRMATION);
                exitMsg.setTitle("Quit");
                exitMsg.setHeaderText("Quit game");
                String out = "You are aboout to guit game and close app.Every progress you 've made so far will be lost.\nAre you sure?";
                exitMsg.setContentText(out);
                exitMsg.setResizable(true);
                exitMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                exitMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //exitMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                exitMsg.getDialogPane().setPrefSize(650.0, 250.0);
                Optional<ButtonType> result = exitMsg.showAndWait();
                if(result != null && result.get() == ButtonType.OK){
                    exitGame();
                }
            }
        });


        /* -------- /Statistics/Hotels -------- */

        hotelsItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert hotelsMsg = new Alert(AlertType.INFORMATION);
                hotelsMsg.setTitle("Hotels");
                hotelsMsg.setHeaderText("Statistics About Hotels");
                String out = "";
                for(String id : gp.getHotels().keySet()){
                    Hotel h = gp.getHotels().get(id);
                    out = out + "Hotel with id " + h.getId() + " ===> Name : " + h.getName() + ", is owned by : ";
                    if(h.isBought() == true){
                        if(h.getOwner().getId() != 4){
                            out = out + "Player" + Integer.toString(h.getOwner().getId());
                        }
                        else{
                            out = out + "Bank";
                        }
                    }
                    else{
                        out = out + "nobody";
                    }
                    out = out + ", Max Build Level : " + (h.getExpansions().size() + 1) + ", Current Build Level : ";
                    int count = 0;
                    if(h.isBuilt()){
                        count = 1;
                    }
                    for(ArrayList<Integer> arr : h.getExpansions()){
                        if(arr.get(2).equals(1)){
                            count++;
                        }
                        else{
                            break;
                        }
                    }
                    out = out + count + "\n\n\n";
                }
                hotelsMsg.setContentText(out);
                hotelsMsg.setResizable(true);
                hotelsMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                hotelsMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //hotelsMsg.getDialogPane().setPrefSize(WIDTH/2.2588, HEIGHT/1.9636);
                hotelsMsg.getDialogPane().setPrefSize(850.0, 550.0);
                Optional<ButtonType> result = hotelsMsg.showAndWait();
            }
        });



        /* -------- /Statistics/Entrances -------- */

        entrancesItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert entrancesMsg = new Alert(AlertType.INFORMATION);
                entrancesMsg.setTitle("Entrances");
                entrancesMsg.setHeaderText("Statistics About Entrances");
                String out1 = "Player1 has : ";
                int count = 0;
                for(MapNode mn : gp.getPlayer1().getHasENodes()){
                    if(mn.isIsEntrance() == true){
                        count++;
                    }
                }
                out1 = out1 + count + " entrances" + "\n\n\n\n\n";
                String out2 = "Player2 has : ";
                count = 0;
                for(MapNode mn : gp.getPlayer2().getHasENodes()){
                    if(mn.isIsEntrance() == true){
                        count++;
                    }
                }
                out2 = out2 + count + " entrances" + "\n\n\n\n\n";
                String out3 = "Player3 has : ";
                count = 0;
                for(MapNode mn : gp.getPlayer3().getHasENodes()){
                    if(mn.isIsEntrance() == true){
                        count++;
                    }
                }
                out3 = out3 + count + " entrances";
                entrancesMsg.setContentText(out1 + out2 + out3);
                entrancesMsg.setResizable(true);
                entrancesMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                entrancesMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //entrancesMsg.getDialogPane().setPrefSize(WIDTH/2.2588, HEIGHT/3.0857);
                entrancesMsg.getDialogPane().setPrefSize(850.0, 350.0);
                Optional<ButtonType> result = entrancesMsg.showAndWait();
            }
        });



        /* -------- /Statistics/Profits -------- */

        profitsItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert profitsMsg = new Alert(AlertType.INFORMATION);
                profitsMsg.setTitle("Profits");
                profitsMsg.setHeaderText("Statistics About Profits");
                String out4 = "Player1 Max Money Value : " + gp.getPlayer1().getMaxMoney() + "\n\n\n\n\n";
                String out5 = "Player2 Max Money Value : " + gp.getPlayer2().getMaxMoney() + "\n\n\n\n\n";
                String out6 = "Player3 Max Money Value : " + gp.getPlayer3().getMaxMoney();
                profitsMsg.setContentText(out4 + out5 + out6);
                profitsMsg.setResizable(true);
                profitsMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                profitsMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                //profitsMsg.getDialogPane().setPrefSize(WIDTH/2.2588, HEIGHT/3.0857);
                profitsMsg.getDialogPane().setPrefSize(850.0, 350.0);
                Optional<ButtonType> result = profitsMsg.showAndWait();
            }
        });

        

        /* -------- Info -------- */

        info.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        info.prefWidthProperty().bind(primaryStage.widthProperty());
        info.setSpacing(WIDTH/192);
        Text p1 = new Text("Player1 : " + gp.getPlayer1().getMoney());
        p1.setStyle("-fx-font: normal bold 20px 'serif'");
        p1.setFill(Color.BLUE);
        Text p2 = new Text("Player2 : " + gp.getPlayer2().getMoney());
        p2.setStyle("-fx-font: normal bold 20px 'serif' ");
        p2.setFill(Color.RED);
        Text p3 = new Text("Player3 : " + gp.getPlayer3().getMoney());
        p3.setStyle("-fx-font: normal bold 20px 'serif' ");
        p3.setFill((Color.GREEN));
        int count1 = 0;
        for(String id : gp.getHotels().keySet()){
            if(gp.getHotels().get(id).isBought() == false){
                count1++;
            }
        }
        Text ah = new Text("Available Hotels : " + count1);
        ah.setStyle("-fx-font: normal bold 20px 'serif' ");
        info.setMargin(p1, new Insets(35, 35, 10, 10));
        info.setMargin(p2, new Insets(35, 35, 10, 10));
        info.setMargin(p3, new Insets(35, 35, 10, 10));
        info.setMargin(ah, new Insets(35, 35, 10, 500));
        info.getChildren().addAll(p1, p2, p3, ah);

        
        /* -------- Options -------- */

        options.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        options.setSpacing(10);
        Text turn = new Text("Player to play : " + Integer.toString(gp.getCurrPlayer().getId()));
        turn.setStyle("-fx-font: normal bold 20px 'serif' ");
        Button rollDice = new Button("Roll Dice");
        rollDice.setStyle("-fx-font: normal bold 20px 'serif' ");
        rollDice.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        rollDice.setTextFill(Color.WHITE);
        rollDice.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        Button requestMoney = new Button("Request Money from Bank");
        requestMoney.setStyle("-fx-font: normal bold 20px 'serif' ");
        requestMoney.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        requestMoney.setTextFill(Color.WHITE);
        requestMoney.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        if(gp.isGoneThroughBank()){
            requestMoney.setVisible(true);
        }
        else{
            requestMoney.setVisible(false);
        }
        Button requestBuilding = new Button("Request Building from City Hall");
        requestBuilding.setStyle("-fx-font: normal bold 20px 'serif' ");
        requestBuilding.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        requestBuilding.setTextFill(Color.WHITE);
        requestBuilding.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        if(gp.isGoneThroughTownHall()){
            requestBuilding.setVisible(true);
        }
        else{
            requestBuilding.setVisible(false);
        }
        Button buyHotel = new Button("Build Hotel");
        buyHotel.setStyle("-fx-font: normal bold 20px 'serif' ");
        buyHotel.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        buyHotel.setTextFill(Color.WHITE);
        buyHotel.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        if(gp.isStoppedAtBuyHNode()){
            buyHotel.setVisible(true);
        }
        else{
            buyHotel.setVisible(false);
        }
        Button buyEntrance = new Button("Buy Entrance");
        buyEntrance.setStyle("-fx-font: normal bold 20px 'serif' ");
        buyEntrance.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        buyEntrance.setTextFill(Color.WHITE);
        buyEntrance.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        if(gp.isGoneThroughTownHall()){
            buyEntrance.setVisible(true);
        }
        else{
            buyEntrance.setVisible(false);
        }
        Button stealHotel = new Button("Buy Hotel from Player");
        stealHotel.setStyle("-fx-font: normal bold 20px 'serif' ");
        stealHotel.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        stealHotel.setTextFill(Color.WHITE);
        stealHotel.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        if(gp.isStoppedAtReBuyHNode()){
            stealHotel.setVisible(true);
        }
        else{
            stealHotel.setVisible(false);
        }
        Button done = new Button("Done");
        done.setStyle("-fx-font: normal bold 20px 'serif' ");
        done.setPrefSize(WIDTH/3.84, HEIGHT/15.4285);
        done.setTextFill(Color.WHITE);
        done.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        
        options.setAlignment(Pos.TOP_CENTER);
        options.setMargin(turn, new Insets(0, 0, HEIGHT/10.8, 0));
        options.setMargin(rollDice, new Insets(0, 0, HEIGHT/54, 0));
        options.setMargin(requestMoney, new Insets(0, 0, HEIGHT/54, 0));
        options.setMargin(requestBuilding, new Insets(0, 0, HEIGHT/54, 0));
        options.setMargin(buyHotel, new Insets(0, 0, HEIGHT/54, 0));
        options.setMargin(buyEntrance, new Insets(0, 0, HEIGHT/54, 0));
        options.setMargin(stealHotel, new Insets(0, 0, HEIGHT/54, 0));
        options.setMargin(done, new Insets(0, 0, HEIGHT/54, 0));
        options.getChildren().addAll(turn, rollDice, requestMoney, requestBuilding, buyHotel, buyEntrance, stealHotel, done);

        /* -------- Roll Dice -------- */

        rollDice.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                if(gp.isHasRolledTurnDice()){
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("You have already rolled the dice.\nPlease perform another action or press \"DONE\", so another player can play");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                else{
                    Integer res = gp.findNewMapNode();
                    gp.whatHappens();
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Dice Result");
                    msg.setHeaderText("Your Result is : " + res);
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                    if(gp.isStoppedAtPayNode()){
                        ButtonType butt = new ButtonType("Roll");
                        Alert msg1 = new Alert(AlertType.CONFIRMATION);
                        msg1.getButtonTypes().setAll(butt);
                        msg1.setTitle("Ooops");
                        msg1.setHeaderText("Bad News");
                        msg1.setContentText("You seem to have stepped at another living thing's property.\nRoll the dice to see how many days you 'll stay");
                        msg1.setResizable(true);
                        msg1.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg1.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg1.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result1 = msg1.showAndWait();
                        gp.setHasRolledTurnDice(false);
                        Integer x = gp.rollDice();
                        Alert msg2 = new Alert(AlertType.INFORMATION);
                        msg2.setTitle("Ooops");
                        msg2.setHeaderText("Bad News");
                        int cost = gp.getCurrPlayer().getIsAt().getBuiltHotel().getDailyCost();
                        for(ArrayList<Integer> exp : gp.getCurrPlayer().getIsAt().getBuiltHotel().getExpansions()){
                            if(exp.get(2).equals(1)){
                                cost = exp.get(1);
                            }
                            else{
                                break;
                            }
                        }
                        msg2.setContentText("You stayed for " + x + " days.\nYou have to pay " + x*cost);
                        msg2.setResizable(true);
                        msg2.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg2.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg2.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result2 = msg2.showAndWait();
                        if(gp.getCurrPlayer().spends(x*cost)){
                            Alert lose = new Alert(AlertType.INFORMATION);
                            lose.setTitle("Attention");
                            lose.setHeaderText("Player " + gp.getCurrPlayer().getId());
                            lose.setContentText("You seem to have lost all your money.\nSorry dude you cannot play anymore");
                            lose.setResizable(true);
                            lose.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            lose.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                            lose.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                            Optional<ButtonType> re = lose.showAndWait();
                            if(gp.loses()){
                                endGame();
                                return;
                            }
                        }
                        gp.getCurrPlayer().getIsAt().getBuiltHotel().getOwner().earns(x*cost);
                        gp.setHasRolledTurnDice(true);
                    }
                    display();
                } 
            }
        });


        /* -------- Request Money -------- */

        requestMoney.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                if(gp.isHasTakenMoney()){
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("You have already taken money from the bank.\nPlease perform another action or press \"DONE\", so another player can play");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                else{
                    gp.takeMoneyFromBank();
                    display();
                }
            }
        });


        /* -------- Request Building -------- */

        requestBuilding.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                if(gp.isHasRequestOrEntrance()){
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("You can only issue one request or buy one entrance per round.\nPlease perform another action or press \"DONE\", so another player can play");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                else{
                    if(gp.getCurrPlayer().getHasHotels().size() == 0){
                        Alert msg = new Alert(AlertType.INFORMATION);
                        msg.setTitle("Attention");
                        msg.setHeaderText("Sorry you don't have any hotels.\nPlease perform another action or press \"DONE\", so another player can play");
                        msg.setResizable(true);
                        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result = msg.showAndWait();
                    }
                    else{
                        ArrayList<String> choices = new ArrayList<>();
                        for(Hotel h : gp.getCurrPlayer().getHasHotels()){
                            int cost = 0;
                            boolean canRequest = true;
                            if(!h.isBuilt()){
                                cost = h.getBasicBuildCost();
                            }
                            else if(h.getExpansions().get(h.getExpansions().size()-1).get(2) == 1){
                                canRequest = false;
                            }
                            else{
                                for(ArrayList<Integer> arr : h.getExpansions()){
                                    if(arr.get(2) == 0){
                                        cost = arr.get(0);
                                        break;
                                    }
                                }
                            }
                            if(gp.getCurrPlayer().getMoney() > cost && canRequest == true){
                                String s = Integer.toString(h.getId()) + " - " + h.getName() + " - " + cost;
                                choices.add(s);
                            }
                        }
                        if(choices.size() > 0){
                            ChoiceDialog<String> cardMsg = new ChoiceDialog(choices.get(0), choices);
                            cardMsg.setTitle("Request Building");
                            cardMsg.setHeaderText("Choose one");
                            String out1 = "Pick a Hotel : ";
                            cardMsg.setContentText(out1);
                            cardMsg.setResizable(true);
                            cardMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            cardMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                            cardMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                            Optional<String> result = cardMsg.showAndWait();
                            if(result != null && result.get() != null){
                                //gp.setHasRequestOrEntrance(true);
                                Hotel hotel = gp.getHotels().get(result.get().substring(0, 1));
                                Integer x = gp.requestBuildingDice();
                                Alert msg = new Alert(AlertType.INFORMATION);
                                msg.setTitle("Request Bulding");
                                String s1 = null;
                                int cost = 0;
                                int pos = -1;
                                if(!hotel.isBuilt()){
                                    cost = hotel.getBasicBuildCost();
                                }
                                else{
                                    for(int i = 0; i < hotel.getExpansions().size(); i++){
                                        ArrayList<Integer> arr = hotel.getExpansions().get(i);
                                        if(arr.get(2) == 0){
                                            pos = i;
                                            cost = arr.get(0);
                                            break;
                                        }
                                    }
                                }
                                if(x <= 50){
                                    MapNode mn = gp.requestBuildingRandomMapNode(result.get().substring(0, 1));
                                    if(mn!=null){
                                        mn.setOwner(gp.getCurrPlayer());
                                        mn.setBuiltHotel(hotel);
                                        if(pos >= 0){
                                            hotel.getExpansions().get(pos).set(2, 1);
                                        }
                                        else{
                                            hotel.setBuilt(true);
                                        }
                                        if(gp.getCurrPlayer().spends(cost)){
                                            Alert lose = new Alert(AlertType.INFORMATION);
                                            lose.setTitle("Attention");
                                            lose.setHeaderText("Player " + gp.getCurrPlayer().getId());
                                            lose.setContentText("You seem to have lost all your money.\nSorry dude you cannot play anymore");
                                            lose.setResizable(true);
                                            lose.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                            lose.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                            lose.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                                            Optional<ButtonType> re = lose.showAndWait();
                                            if(gp.loses()){
                                                endGame();
                                                return;
                                            }
                                        }
                                        gp.getCurrPlayer().getHasENodes().add(mn);
                                        s1 = "Your request has been handled successfully at normal cost";
                                    }
                                    else{
                                        s1 = "Sorry there is not free space for this kind of hotel";
                                    }
                                }
                                else if(x <= 70){
                                    s1 = "Your request was denied";
                                }
                                else if(x <= 85){
                                    MapNode mn = gp.requestBuildingRandomMapNode(result.get().substring(0, 1));
                                    if(mn!=null){
                                        mn.setOwner(gp.getCurrPlayer());
                                        mn.setBuiltHotel(hotel);
                                        if(pos >= 0){
                                            hotel.getExpansions().get(pos).set(2, 1);
                                        }
                                        else{
                                            hotel.setBuilt(true);
                                        }
                                        gp.getCurrPlayer().getHasENodes().add(mn);
                                        s1 = "Your request has been handled successfully for free";
                                    }
                                    else{
                                        s1 = "Sorry there is not free space for this kind of hotel";
                                    }
                                }
                                else{
                                    MapNode mn = gp.requestBuildingRandomMapNode(result.get().substring(0, 1));
                                    if(mn!=null){
                                        mn.setOwner(gp.getCurrPlayer());
                                        mn.setBuiltHotel(hotel);
                                        if(pos >= 0){
                                            hotel.getExpansions().get(pos).set(2, 1);
                                        }
                                        else{
                                            hotel.setBuilt(true);
                                        }
                                        if(gp.getCurrPlayer().spends(cost*2)){
                                            Alert lose = new Alert(AlertType.INFORMATION);
                                            lose.setTitle("Attention");
                                            lose.setHeaderText("Player " + gp.getCurrPlayer().getId());
                                            lose.setContentText("You seem to have lost all your money.\nSorry dude you cannot play anymore");
                                            lose.setResizable(true);
                                            lose.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                            lose.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                            lose.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                                            Optional<ButtonType> re = lose.showAndWait();
                                            if(gp.loses()){
                                                endGame();
                                                return;
                                            }
                                        }
                                        gp.getCurrPlayer().getHasENodes().add(mn);
                                        s1 = "Your request has been handled successfully at double cost";
                                    }
                                    else{
                                        s1 = "Sorry there is not free space for this kind of hotel";
                                    }
                                }
                                msg.setContentText(s1);
                                msg.setResizable(true);
                                msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                msg.getDialogPane().setPrefSize(WIDTH/3.84, HEIGHT/4.32);
                                Optional<ButtonType> result1 = msg.showAndWait();
                            }
                        }
                        else{
                            Alert msg = new Alert(AlertType.INFORMATION);
                            msg.setTitle("Attention");
                            msg.setHeaderText("Either all your hotels are build at maximum level either you don't have the money.\nPlease perform another action or press \"DONE\", so another player can play");
                            msg.setResizable(true);
                            msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                            msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                            Optional<ButtonType> result = msg.showAndWait();
                        }
                    }
                }
                
                display();
            }
        });


        /* -------- Buy Hotel -------- */

        buyHotel.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                ArrayList<String> choices = new ArrayList<>();
                if(gp.getCurrPlayer().getIsAt().getOwner()==null){
                    for(Hotel h : gp.getCurrPlayer().getIsAt().getCanBuildHotels()){
                        if(!h.isBought()){
                            if(h.getToBuyCost() < gp.getCurrPlayer().getMoney()){
                                String s = Integer.toString(h.getId()) + " - " + h.getName() + " - " + h.getToBuyCost();
                                choices.add(s);
                            }  
                        }
                    }
                    if(choices.size() > 0){
                        ChoiceDialog<String> cardMsg = new ChoiceDialog(choices.get(0), choices);
                        cardMsg.setTitle("Buy hotel");
                        cardMsg.setHeaderText("Choose one");
                        String out1 = "Pick a Hotel : ";
                        cardMsg.setContentText(out1);
                        cardMsg.setResizable(true);
                        cardMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        cardMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        cardMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<String> result = cardMsg.showAndWait();
                        if(result.get() != null){
                            Hotel hotel = gp.getHotels().get(result.get().substring(0, 1));
                            hotel.setBought(true);
                            hotel.setOwner(gp.getCurrPlayer());
                            gp.getCurrPlayer().getIsAt().setOwner(gp.getCurrPlayer());
                            gp.getCurrPlayer().getIsAt().setBuiltHotel(hotel);
                            gp.getCurrPlayer().getHasHotels().add(hotel);
                            if(gp.getCurrPlayer().spends(hotel.getToBuyCost())){
                                Alert lose = new Alert(AlertType.INFORMATION);
                                lose.setTitle("Attention");
                                lose.setHeaderText("Player " + gp.getCurrPlayer().getId());
                                lose.setContentText("You seem to have lost all your money.\nSorry dude you cannot play anymore");
                                lose.setResizable(true);
                                lose.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                lose.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                lose.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                                Optional<ButtonType> re = lose.showAndWait();
                                if(gp.loses()){
                                    endGame();
                                    return;
                                }
                            }
                            gp.getCurrPlayer().getHasHNodes().add(gp.getCurrPlayer().getIsAt());
                        }
                    }
                    else{
                        Alert msg = new Alert(AlertType.INFORMATION);
                        msg.setTitle("Attention");
                        msg.setHeaderText("You don't have enough money to buy one of the hotels offered here.\nPlease perform another action or press \"DONE\", so another player can play");
                        msg.setResizable(true);
                        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result = msg.showAndWait();
                    }
                }
                else{
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("This node is already bought.\nPlease perform another action or press \"DONE\", so another player can play");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                display();
            }
        });

        /* -------- Buy Entrance -------- */

        buyEntrance.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                if(gp.isHasRequestOrEntrance()){
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("You can only issue one request or buy one entrance per round.\nPlease perform another action or press \"DONE\", so another player can play");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                else{
                    if(gp.getCurrPlayer().getHasHotels().size() == 0){
                        Alert msg = new Alert(AlertType.INFORMATION);
                        msg.setTitle("Attention");
                        msg.setHeaderText("Sorry you don't have any hotels.\nPlease perform another action or press \"DONE\", so another player can play");
                        msg.setResizable(true);
                        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result = msg.showAndWait();
                    }
                    else{
                        ArrayList<String> choices = new ArrayList<>();
                        for(Hotel h : gp.getCurrPlayer().getHasHotels()){
                            int cost = h.getEntranceCost();
                            if(h.isBuilt() && gp.getCurrPlayer().getMoney() > cost){
                                String s = Integer.toString(h.getId()) + " - " + h.getName() + " - " + cost;
                                choices.add(s);
                            }
                        }
                        if(choices.size() > 0){
                            ChoiceDialog<String> cardMsg = new ChoiceDialog(choices.get(0), choices);
                            cardMsg.setTitle("Buy entrance");
                            cardMsg.setHeaderText("Choose one");
                            String out1 = "Pick a Hotel : ";
                            cardMsg.setContentText(out1);
                            cardMsg.setResizable(true);
                            cardMsg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            cardMsg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                            cardMsg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                            Optional<String> result = cardMsg.showAndWait();
                            if(result != null && result.get() != null){
                                //gp.setHasRequestOrEntrance(true);
                                Hotel hotel = gp.getHotels().get(result.get().substring(0, 1));
                                Integer x = gp.requestBuildingDice();
                                Alert msg = new Alert(AlertType.INFORMATION);
                                msg.setTitle("Buy entrance");
                                String s1 = null;
                                int cost = hotel.getEntranceCost();
                                MapNode mn = gp.requestBuildingRandomMapNode(result.get().substring(0, 1));
                                if(mn!=null){
                                    mn.setOwner(gp.getCurrPlayer());
                                    mn.setBuiltHotel(hotel);
                                    mn.setIsEntrance(true);
                                    gp.getCurrPlayer().getHasENodes().add(mn);
                                    s1 = "Your request has been handled successfully";
                                    if(gp.getCurrPlayer().spends(cost)){
                                        Alert lose = new Alert(AlertType.INFORMATION);
                                        lose.setTitle("Attention");
                                        lose.setHeaderText("Player " + gp.getCurrPlayer().getId());
                                        lose.setContentText("You seem to have lost all your money.\nSorry dude you cannot play anymore");
                                        lose.setResizable(true);
                                        lose.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                        lose.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                        lose.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                                        Optional<ButtonType> re = lose.showAndWait();
                                        if(gp.loses()){
                                            endGame();
                                            return;
                                        }
                                    }
                                }
                                else{
                                    s1 = "Sorry there is not free space for this kind of hotel"; 
                                }
                                msg.setContentText(s1);
                                msg.setResizable(true);
                                msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                msg.getDialogPane().setPrefSize(WIDTH/3.84, HEIGHT/4.32);
                                Optional<ButtonType> result1 = msg.showAndWait();
                            }
                        }
                        else{
                            Alert msg = new Alert(AlertType.INFORMATION);
                            msg.setTitle("Attention");
                            msg.setHeaderText("To buy an entrance you must have built a hotel or own one.\nPlease perform another action or press \"DONE\", so another player can play");
                            msg.setResizable(true);
                            msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                            msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                            Optional<ButtonType> result = msg.showAndWait();
                        }
                    }
                }
                
                display();
            }
        });


        /* -------- Steal Hotel -------- */

        stealHotel.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                if(gp.getCurrPlayer().getIsAt().getBuiltHotel().isBuilt() == false && gp.getCurrPlayer().getIsAt().getOwner() != gp.getCurrPlayer()){
                    if(gp.getCurrPlayer().getMoney() > gp.getCurrPlayer().getIsAt().getBuiltHotel().getToSellAfterBuyCost()){
                        Alert msg = new Alert(AlertType.CONFIRMATION);
                        msg.setTitle("Re-Buy hotel");
                        msg.setHeaderText("Steal hotel from another player");
                        String s = "Do you wanna buy hotel " + gp.getCurrPlayer().getIsAt().getBuiltHotel().getName() + " with id " + Integer.toString(gp.getCurrPlayer().getIsAt().getBuiltHotel().getId()) + "\n from Player" + Integer.toString(gp.getCurrPlayer().getIsAt().getOwner().getId()) + " at cost " + gp.getCurrPlayer().getIsAt().getBuiltHotel().getToSellAfterBuyCost();
                        msg.setContentText(s);
                        msg.setResizable(true);
                        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result = msg.showAndWait();
                        if(result.get() == ButtonType.OK){
                            gp.getCurrPlayer().getIsAt().getOwner().getHasHotels().remove(gp.getCurrPlayer().getIsAt().getBuiltHotel());
                            gp.getCurrPlayer().getIsAt().getOwner().earns(gp.getCurrPlayer().getIsAt().getBuiltHotel().getToSellAfterBuyCost());
                            gp.getCurrPlayer().getIsAt().getOwner().getHasENodes().remove(gp.getCurrPlayer().getIsAt());
                            gp.getCurrPlayer().getIsAt().setOwner(gp.getCurrPlayer());
                            gp.getCurrPlayer().getIsAt().getBuiltHotel().setOwner(gp.getCurrPlayer());
                            gp.getCurrPlayer().getHasHNodes().add(gp.getCurrPlayer().getIsAt());
                            gp.getCurrPlayer().getHasHotels().add(gp.getCurrPlayer().getIsAt().getBuiltHotel());
                            if(gp.getCurrPlayer().spends(gp.getCurrPlayer().getIsAt().getBuiltHotel().getToSellAfterBuyCost())){
                                Alert lose = new Alert(AlertType.INFORMATION);
                                lose.setTitle("Attention");
                                lose.setHeaderText("Player " + gp.getCurrPlayer().getId());
                                lose.setContentText("You seem to have lost all your money.\nSorry dude you cannot play anymore");
                                lose.setResizable(true);
                                lose.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                lose.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                                lose.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                                Optional<ButtonType> re = lose.showAndWait();
                                if(gp.loses()){
                                    endGame();
                                    return;
                                }
                            }
                        }
                    }
                    else{
                        Alert msg = new Alert(AlertType.INFORMATION);
                        msg.setTitle("Attention");
                        msg.setHeaderText("You don't have enough money to perform this action.\nPlease perform another action or press \"DONE\", so another player can play");
                        msg.setResizable(true);
                        msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                        Optional<ButtonType> result = msg.showAndWait();
                    }
                    
                }
                else{
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("This hotel is bought buy you.\nPlease perform another action or press \"DONE\", so another player can play");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                display();
            }
        });


        /* -------- Done -------- */

        done.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                if(!gp.isHasRolledTurnDice()){
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("You have to roll the dice before another player takes turn.\nPlease press \"ROLL DICE\"");
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                else{
                    gp.playNext();
                    Alert msg = new Alert(AlertType.INFORMATION);
                    msg.setTitle("Attention");
                    msg.setHeaderText("Player to play : Player" + gp.getCurrPlayer().getId());
                    msg.setResizable(true);
                    msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    msg.getDialogPane().setPrefSize(WIDTH/2.9538, HEIGHT/4.32);
                    Optional<ButtonType> result = msg.showAndWait();
                }
                display();
            }
        });


        /* -------- General -------- */

        AnchorPane.setTopAnchor(menuBar, 0.0);
        AnchorPane.setBottomAnchor(menuBar, HEIGHT/1.1134);
        AnchorPane.setTopAnchor(info, 40.0);
        AnchorPane.setBottomAnchor(info, HEIGHT/1.161);
        AnchorPane.setTopAnchor(options, HEIGHT/7.2972);
        AnchorPane.setLeftAnchor(options, WIDTH/1.6);
        AnchorPane.setRightAnchor(options, 0.0);
        AnchorPane.setBottomAnchor(options, 0.0);
        AnchorPane.setTopAnchor(board, HEIGHT/6.3529);
        AnchorPane.setBottomAnchor(board, 0.0);
        
        root.getChildren().addAll(menuBar, info, options, board);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.WHITESMOKE);
        return scene;
    }

    public void restartGame(){
        gp.cleanUp();
        display();
    }

    public void exitGame(){
        Platform.exit();
    }

    public void endGame(){
        Alert win = new Alert(AlertType.INFORMATION);
        win.setTitle("Winner");
        win.setHeaderText("And the winner is : Player" + gp.getTurns().get(0).getId());
        win.setResizable(true);
        win.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        win.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        win.getDialogPane().setPrefSize(WIDTH/2.2588, HEIGHT/3.0857);
        Optional<ButtonType> result = win.showAndWait();
        primaryStage.hide();
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        Button play = new Button("Play Again");
        play.setStyle("-fx-font: normal bold 20px 'serif' ");
        play.setPrefSize(WIDTH/4.8, HEIGHT/10.8);
        play.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                gp.cleanUp();
                gp.setTurns(new ArrayList<>());
                gp.defineTurns();
                Alert msg = new Alert(AlertType.INFORMATION);
                msg.setTitle("Player Turns");
                msg.setHeaderText("The players will play like this");
                String out1 = "First : Player" + Integer.toString(gp.getTurns().get(0).getId()) + "\n\n\n\n\n";
                String out2 = "Second : Player" + Integer.toString(gp.getTurns().get(1).getId()) + "\n\n\n\n\n";
                String out3 = "Third : Player" + Integer.toString(gp.getTurns().get(2).getId());
                msg.setContentText(out1 + out2 + out3);
                msg.setResizable(true);
                msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                msg.getDialogPane().setPrefSize(WIDTH/3.84, HEIGHT/4.32);
                Optional<ButtonType> result = msg.showAndWait();
                //Stage stage = new Stage();
                stage.hide();
                display();
            }
        });
        Button exit = new Button("Exit");
        exit.setStyle("-fx-font: normal bold 20px 'serif' ");
        exit.setPrefSize(WIDTH/4.8, HEIGHT/10.8);
        exit.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                exitGame();
            }
        });
        AnchorPane.setTopAnchor(play, HEIGHT/3.6);
        AnchorPane.setLeftAnchor(play, WIDTH/2.7482);
        AnchorPane.setBottomAnchor(exit, HEIGHT/3.6);
        AnchorPane.setLeftAnchor(exit, WIDTH/2.7482);
        root.getChildren().addAll(play, exit);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLUE);
        stage.setTitle("MediaLab Hotel");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        Button play = new Button("Play");
        play.setStyle("-fx-font: normal bold 20px 'serif' ");
        play.setPrefSize(WIDTH/4.8, HEIGHT/10.8);
        play.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                Alert msg = new Alert(AlertType.INFORMATION);
                msg.setTitle("Player Turns");
                msg.setHeaderText("The players will play like this");
                String out1 = "First : Player" + Integer.toString(gp.getTurns().get(0).getId()) + "\n\n";
                String out2 = "Second : Player" + Integer.toString(gp.getTurns().get(1).getId()) + "\n\n";
                String out3 = "Third : Player" + Integer.toString(gp.getTurns().get(2).getId());
                msg.setContentText(out1 + out2 + out3);
                msg.setResizable(true);
                msg.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                msg.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                msg.getDialogPane().setPrefSize(650.0, 250.0);
                Optional<ButtonType> result = msg.showAndWait();
                //Stage stage = new Stage();
                primaryStage.hide();
                display();
            }
        });
        Button exit = new Button("Exit");
        exit.setStyle("-fx-font: normal bold 20px 'serif' ");
        exit.setPrefSize(WIDTH/4.8, HEIGHT/10.8);
        exit.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent me) {
                exitGame();
            }
        });
        AnchorPane.setTopAnchor(play, HEIGHT/3.6);
        AnchorPane.setLeftAnchor(play, WIDTH/2.7482);
        AnchorPane.setBottomAnchor(exit, HEIGHT/3.6);
        AnchorPane.setLeftAnchor(exit, WIDTH/2.7482);
        root.getChildren().addAll(play, exit);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLUE);
        primaryStage.setTitle("MediaLab Hotel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        gp = new GamePlay();
        gp.readBoard();
        gp.defineTurns();
        /*for(Player pl : gp.getTurns()){
            System.out.println(pl.getId());
        }
        for(String id : gp.getHotels().keySet()){
            Hotel hotel = gp.getHotels().get(id);
            System.out.println(hotel.getId());
            System.out.println(hotel.getName());
            System.out.println(hotel.getToBuyCost() + ", " + hotel.getToSellAfterBuyCost());
            System.out.println(hotel.getEntranceCost());
            System.out.println(hotel.getBasicBuildCost() + ", " + hotel.getDailyCost());
            for(ArrayList<Integer> expansion : hotel.getExpansions()){
                System.out.println(expansion.get(0) + ", " + expansion.get(1) + ", " + expansion.get(2));
            }
            System.out.println("===================================");
        }*/
        /*for(MapNode mn : gp.getBoard()){
            System.out.println(mn.getId() + " -- > " + mn.getCanGoNode().getId());
        }
        System.out.println("=====================================================");*/
        /*for(MapNode mn : gp.getBoard()){
            System.out.print(mn.getId() + " -- > ");
            if(mn.getCanBuildHotels().size() > 0){
                for(Hotel h : mn.getCanBuildHotels()){
                    System.out.print(h.getId() + ", ");
                }
            }
            System.out.println();
            System.out.println("====================================================");
        }
        for(MapNode mn : gp.getBoard()){
            System.out.println(mn.getId() + " : " + mn.getX() + ", " + mn.getY());
            System.out.println("====================================================");
        }
        for(String id : gp.getTownHallMapNodes().keySet()){
            HashMap<String, MapNode> hm = gp.getTownHallMapNodes().get(id);
            System.out.println(" -- " + id + " -- ");
            for(String s : hm.keySet()){
                MapNode mn = hm.get(s);
                System.out.print(mn.getX() + ", " + mn.getY() + " || ");
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------");
        }*/
        launch("HotelApp");
    }
}