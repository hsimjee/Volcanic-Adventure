import java.util.ArrayList; 

public class Adventure implements AdventureConstants 
{
    private static boolean endGame;
    private static String userInput, command, phrase;
    private static KeyboardReader reader = new KeyboardReader();
    private static int currentArea, score, count = 0, enter = 0, riddles = 0, num, items=1;
    private static ArrayList<String> backpack = new ArrayList<String>();
    private static boolean wearingJacket = false, unlocked = false;;
    private static Area[] world = new Area[23];
    static
    {
        endGame = false;
    }    

    public static void main (String [] args) 
    { 
        initializeWorld();  

        while (!endGame) 
        { 
            displayCurrentArea();
            getUserInput(); 
            parseUserInput();
        } 

        displayFarewellMessageAndScore(); 
    }

    private static void initializeWorld()
    {
        System.out.println("*****Welcome to Volcanic Adventure!!!*****\n-----------Got the Pot O' Gold------------");
        world[WET_FOREST] = new Area();
        world[LAKE] = new Area();
        world[NORTH_HALL] = new Area();
        world[MID_HALL] = new Area();
        world[SOUTH_HALL] = new Area();
        world[KITCHEN] = new Area();
        world[ROOM_1] = new Area();
        world[ROOM_2] = new Area();
        world[BATHROOM] = new Area();
        world[SEA] = new Area();
        world[WINTER_ISLAND] = new Area();
        world[SNOWY_MOUNTAIN] = new Area();
        world[CLIFF] = new Area();
        world[FOREST_ISLAND] = new Area();
        world[DRY_FOREST] = new Area();
        world[PRAIRIE_ISLAND] = new Area();
        world[HILL] = new Area();
        world[CAVE] = new Area();
        world[VOLCANIC_ISLAND] = new Area();
        world[VOLCANO] = new Area();
        world[HOME] = new Area();
        world[BEACH] = new Area();

        world[HOME].setNorth(WET_FOREST);
        world[WET_FOREST].setEast(LAKE);
        world[LAKE].setEast(NORTH_HALL);
        world[NORTH_HALL].setWest(LAKE);
        world[NORTH_HALL].setEast(KITCHEN);
        world[NORTH_HALL].setSouth(MID_HALL);
        world[NORTH_HALL].setNorth(BEACH);
        world[BEACH].setSouth(NORTH_HALL);
        world[MID_HALL].setWest(ROOM_1);
        world[MID_HALL].setEast(ROOM_2);
        world[MID_HALL].setNorth(NORTH_HALL);
        world[MID_HALL].setSouth(SOUTH_HALL);
        world[SOUTH_HALL].setNorth(MID_HALL);
        world[KITCHEN].setWest(NORTH_HALL);
        world[ROOM_1].setEast(MID_HALL);
        world[ROOM_2].setWest(MID_HALL);
        world[BATHROOM].setWest(SOUTH_HALL);
        world[SEA].setSouth(NORTH_HALL);
        world[SEA].setNorth(PRAIRIE_ISLAND);
        world[SEA].setEast(WINTER_ISLAND);
        world[SEA].setNortheast(FOREST_ISLAND);
        world[WINTER_ISLAND].setWest(SEA);
        world[SNOWY_MOUNTAIN].setNorth(WINTER_ISLAND);
        world[CLIFF].setWest(SNOWY_MOUNTAIN);
        world[FOREST_ISLAND].setSouthwest(SEA);
        world[FOREST_ISLAND].setNorth(DRY_FOREST);
        world[DRY_FOREST].setSouth(FOREST_ISLAND);
        world[PRAIRIE_ISLAND].setSouth(SEA);
        world[PRAIRIE_ISLAND].setNorth(HILL);
        world[HILL].setSouth(PRAIRIE_ISLAND);
        world[HILL].setNorth(CAVE);
        world[CAVE].setSouth(HILL);
        world[SNOWY_MOUNTAIN].setEast(CLIFF);
        world[WINTER_ISLAND].setSouth(SNOWY_MOUNTAIN);

        world[WET_FOREST].addItem("rusty knife");
        world[LAKE].addItem("crocodile tooth");
        world[LAKE].addItem("aquastaff");
        world[ROOM_1].addItem("black jacket");
        world[ROOM_1].addItem("skeleton key");
        world[ROOM_2].addItem("red wrench");
        world[CLIFF].addItem("water bottle");

        world[HOME].setShortDescription("Your Home\n");
        world[WET_FOREST].setShortDescription("The Dank Forest\n");
        world[LAKE].setShortDescription("The Aquater Lake\n");
        world[NORTH_HALL].setShortDescription("Northern Section of Abandoned House Hall\n");
        world[MID_HALL].setShortDescription("Middle Section of Abandoned House Hall\n");
        world[SOUTH_HALL].setShortDescription("Southern Section of Abandoned House Hall\n");
        world[KITCHEN].setShortDescription("Old Kitchen\n");
        world[ROOM_1].setShortDescription("Parents' Bedroom\n");
        world[ROOM_2].setShortDescription("Children's' Bedroom\n");
        world[SEA].setShortDescription("A bright blue sea\n");
        world[WINTER_ISLAND].setShortDescription("Snowy Island\n");
        world[SNOWY_MOUNTAIN].setShortDescription("A cold, snowy mountain\n");
        world[CLIFF].setShortDescription("An icy cliff\n");
        world[FOREST_ISLAND].setShortDescription("The Evergreen Forest Island\n");
        world[DRY_FOREST].setShortDescription("A Big, Dry Forest\n");
        world[PRAIRIE_ISLAND].setShortDescription("A seemingly flat island\n");
        world[HILL].setShortDescription("A rocky hill\n");
        world[CAVE].setShortDescription("A dark cave\n");
        world[VOLCANIC_ISLAND].setShortDescription("A dusty island\n");
        world[VOLCANO].setShortDescription("A glowing volcano\n");
        world[BEACH].setShortDescription("A Rocky Beach\n");

        world[HOME].setLongDescription("The familiar home you walk through thinking about the adventure ahead. \nThe freshly painted interior and picture of an abstract nose stands \nbefore you as you made your way to the hallway. The fireplace is \npolished and seemingly never used.\n\nYou have picked up your bellow Backpack and are ready to go.\n");
        world[WET_FOREST].setLongDescription("You have come across the Dank Forest. With a name like that, you can see how there \ncan be trouble getting around. The forest is always dark and never dry. \nIt fills with the sound of birds as you enter. Mud gathers where \nthe river meets the forest soil.\n");
        world[LAKE].setLongDescription("The green lake east of the forest. It's swampy layer makes you wonder \nwhy it's not called a swamp. A crocodile's eyes peak through the \ngreen cover, sneaking up onto a fresh fish.\n ");
        world[NORTH_HALL].setLongDescription("An abandoned house's northern hall seen with one kitchen. \nNo furniture. Paint peeling. No one has been here for a long time. \nThe doors are broken. ");
        world[MID_HALL].setLongDescription("An abandoned house's northern hall seen with two rooms. \nNo furniture. Paint peeling. No one has been here for a long time. \nThe doors are open. ");
        world[SOUTH_HALL].setLongDescription("An abandoned house's southern hall seen with one bathroom. \nNo furniture. Paint peeling. No one has been here for a long time. \nThe door to the room is intact and locked.\n ");
        world[KITCHEN].setLongDescription("An old empty kitchen. Nothing here.\n");
        world[ROOM_1].setLongDescription("An old bedroom. Looks like adults used to live in this one. \nLots of cloth. \n");
        world[ROOM_2].setLongDescription("An old bedroom. Looks like children used to live in this one. \nLots of toys.\n");
        world[SEA].setLongDescription("The waves crash onto the shore of the sea. The beautiful cold \nbreeze skims over the water making a ripple effect. The warm weather brings \nthe fish jumping in and out of the water. A boat appears on the \nshallow part of the shore.\n ");
        world[WINTER_ISLAND].setLongDescription("The cold island with the big snowy mountain. The island \nhas a cold beach, but deeper into the island there are frozen streams \nand rivers all leading up to a big snowy mountain.\n ");
        world[SNOWY_MOUNTAIN].setLongDescription("The Snowy Mountain which has claimed more lives than \nmost mountains. Only the hardiest of men have ever made it back from the \nmountain to tell their tale.\n\nA cliff is at the East of the mountin\n but it's much too cold to go there.\n");
        world[CLIFF].setLongDescription("An icy cliff at the edge of the Snowy Mountain. The 2000ft drop \nonly leads to a thick layer of sharp ice below. Three large puddles of \nred ice are frozen ten layers deep.\n ");
        world[FOREST_ISLAND].setLongDescription("A large island filled with one big forest. Not damp like \nthe one near home, but much dryer.");
        world[DRY_FOREST].setLongDescription("A dry forest which covers the island. Wild animals are the \nonly dangers in these parts, which include Jaguars, Pit Vipers, \nRattlesnakes, and Anacondas. You best watch out for them.\n");
        world[PRAIRIE_ISLAND].setLongDescription("The island doesn't look like much, but it may have more \nthan meets the eye. A small hill sits mystically in the middle of the \nisland. Around the hill, nothing can be seen except for a large mirage \ncovering the foot of the hill.\n");
        world[HILL].setLongDescription("The rocky hill seemed mystical from afar, but it isn't much up \nclose. It seems regular, but there is a cave at the foot, where the mirage \nwas cover.\n ");
        world[CAVE].setLongDescription("The cave is mostly dark, but there is a small red light coming \nfrom the center. The damp ground holds a stream of water ahead. The flapping \nof wings is heard from the cave.\nA hard rock is covering the hole\n");
        world[VOLCANIC_ISLAND].setLongDescription("The dusty island sits on the western side of the sea. \nMany legends are told about this place, but none of them seem to be believed. \nThe island is covered in sand with nothing but one volcano \nemerging from the center. It is known to be the hottest island. The bright \nlava makes the island glow and gives it a mystical look.\n\nA large stone wall covers the entrance to the volcano in the center \nof the wall, there are two holes. Something needs to fill them.\nIt's also really hot! You are also getting really thirsty");
        world[VOLCANO].setLongDescription("A steep mountain with flowing lava at all corners. No one has \nclimbed it before, but an old legend says there is are treasures showered \nover the surface of the top of the volcano. The volcano is \nactive and can blow at any second, minute, hour, day, or even month.\n\nA fire beast approaches and means to kill you.");
        world[BEACH].setLongDescription("A rocky beach found to the North. From here, nothing is seen \nfor miles; just water. Beautiful shimmering blue water.\n\nA broken boat sits on shore.");

        currentArea = HOME;
    }   

    private static void displayCurrentArea()
    {
        System.out.println("--------------------------------------------------");
        if (world[currentArea].getVisited())
        {
            System.out.println(world[currentArea].getShortDescription());
        }
        else
        {
            System.out.println(world[currentArea].getLongDescription());
            world[currentArea].setVisited(true);
            if (currentArea == 1)
                world[HOME].setLongDescription("The familiar home you walk through thinking about the adventure ahead. \nThe freshly painted interior and picture of an abstract nose stands \nbefore you as you made your way to the hallway. The fireplace is \npolished and seemingly never used.");
        }
        world[currentArea].displayInventory();
        world[currentArea].displayExits();
    }

    private static void getUserInput()
    {
        userInput = reader.readString("");
        userInput = StringParser.formatString(userInput);
        command = StringParser.getCommand(userInput);
        phrase = StringParser.getPhrase(userInput);
    }

    private static void parseUserInput()
    {
        if (command.equals("q") || command.equals("quit"))
            endGame=true;
        if (command.equals("look") || command.equals("l"))
            System.out.println(world[currentArea].getLongDescription());
        if (command.equals("i") || command.equals("inv") || command.equals("inventory"))
            inventoryDisplay();
        if (command.equals("score")){
            System.out.println("You currently have " + score + " out of 50 points.");
            if(score < 11)
                System.out.println("You have achieved the rank of Failed Warrior.");
            else if (score < 21)
                System.out.println("You have achieved the rank of Amateur Marksman.");
            else if (score < 31)
                System.out.println("You have achieved the rank of Silver Thief.");
            else if (score < 41)
                System.out.println("You have achieved the rank of Professional Javelineer.");
            else if (score < 50)
                System.out.println("You have achieved the rank of Expert Torchbearer.");
            else
                System.out.println("You have achieved the rank of Legendary Mountaineer.");
        }

        if (currentArea != SNOWY_MOUNTAIN){
            if (command.equals("n") || command.equals("north"))
                move("north");
            if (command.equals("s") || command.equals("south"))
                move("south");
            if (command.equals("e") || command.equals("east"))
                move("east");
            if (command.equals("w") || command.equals("west"))
                move("west");
            if (command.equals("ne") || command.equals("northeast"))
                move("northeast");
            if (command.equals("nw") || command.equals("northwest"))
                move("northwest");
            if (command.equals("se") || command.equals("southeast"))
                move("southeast");
            if (command.equals("sw") || command.equals("southwest"))
                move("southwest");

            if (command.equals("go") || command.equals("walk") || command.equals("run"))
            {
                if (phrase.equals("n") || phrase.equals("north"))
                    move("north");
                else if (phrase.equals("s") || phrase.equals("south"))
                    move("south");
                else if (phrase.equals("e") || phrase.equals("east"))
                    move("east");
                else if (phrase.equals("w") || phrase.equals("west"))
                    move("west");
                else if (phrase.equals("ne") || phrase.equals("northeast"))
                    move("northeast");
                else if (phrase.equals("nw") || phrase.equals("northwest"))
                    move("northwest");
                else if (phrase.equals("se") || phrase.equals("southeast"))
                    move("southeast");
                else if (phrase.equals("sw") || phrase.equals("southwest"))
                    move("southwest");
                else
                    System.out.println("Which way do you want to go?");
            }
        }

        else{
            if ((command.equals("e") || command.equals("east")) && !wearingJacket){
                System.out.println("Very Dumb move. Way to forget wearing your jacket. \nYou have died from hypothermia...");
                endGame = true;
            }

            if ((command.equals("go") || command.equals("walk") || command.equals("run")) && !wearingJacket)
                if (phrase.equals("e") || phrase.equals("east")){
                    System.out.println("Very Dumb move. Way to forget wearing your jacket. \nYou have died from hypothermia...");
                    endGame = true;
                }

            if((command.equals("e") || command.equals("east")) && wearingJacket)
                move("east");

            if ((command.equals("go") || command.equals("walk") || command.equals("run")) && wearingJacket)
                if (phrase.equals("e") || phrase.equals("east"))
                    move("east");

            if(command.equals("n") || command.equals("north"))
                move("north");

            if (command.equals("go") || command.equals("walk") || command.equals("run"))
                if (phrase.equals("n") || phrase.equals("north"))
                    move("north");
        }

        if (command.equals("take") || command.equals("get") || command.equals("grab") 
        || command.equals("grasp") || command.equals("pick"))
        {
            if (world[currentArea].removeItem(phrase)){
                backpack.add(phrase);
                System.out.println("You have picked up " + phrase);
                items++;
            }
            else
                System.out.println("That item is not here.");
        }

        if (command.equals("drop"))
        {
            if (backpack.contains(phrase)){
                world[currentArea].addItem(phrase);
                backpack.remove(phrase);
                System.out.println("You have dropped " + phrase);
            }
            else
                System.out.println("That item is not here.");
        }

        if (command.equals("unlock"))
        {
            if (phrase.equals("door") && currentArea == SOUTH_HALL)
            {
                System.out.println("What do you want to unlock the door with?");
            }
            else if (phrase.equals("door with skeleton key") && currentArea == SOUTH_HALL)
            {
                if (backpack.contains("skeleton key")){
                    System.out.println("The bathroom is unlocked");
                    world[currentArea].addItem("skeleton key");
                    backpack.remove("skeleton key");
                    world[SOUTH_HALL].setEast(BATHROOM);
                    score += 5;
                    unlocked = true;
                    world[BATHROOM].setShortDescription("Unlocked Bathroom\n");
                    world[BATHROOM].setLongDescription("An old while door leads to the previously locked bathroom. \nThe bathroom is flooded. A clogged up drain kept the water from leaving. \nThe faucet is still on. The toilet broken. Brown mold on the roof and \na single, untouched, shiny mirror.\nNothing here.\n");
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != SOUTH_HALL)
                System.out.println("I don't see anything to unlock here!");
        }

        if (command.equals("fix") || command.equals("repair") || command.equals("mend"))
        {
            if (phrase.equals("boat") && currentArea == BEACH)
            {
                System.out.println("What do you want to repair the boat with?");
            }
            else if (phrase.equals("boat with red wrench") && currentArea == BEACH)
            {
                if (backpack.contains("red wrench")){
                    System.out.println("The boat is fixed");
                    world[currentArea].addItem("red wrench");
                    backpack.remove("red wrench");
                    score += 10;
                    world[BEACH].setNorth(SEA);
                    world[BEACH].setLongDescription("A rocky beach found to the North. From here, nothing is seen\nfor miles; just water. Beautiful shimmering blue water.\nA working boat sits on shore.");
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != BEACH)
                System.out.println("I don't see anything to repair here!");
        }

        if (command.equals("stab") || command.equals("kill"))
        {
            if (phrase.equals("fire beast") && currentArea == VOLCANO)
            {
                System.out.println("What do you want to kill the fire beast with?");
            }
            else if (phrase.equals("fire beast with rusty knife") && currentArea == VOLCANO)
            {
                if (backpack.contains("rusty knife")){
                    System.out.println("The fire monstor has fell!\nA pot o' gold has appeared behind it!");
                    world[CAVE].addItem("pot o' gold");
                    score = 49;
                    if (unlocked)
                        score = 50;
                    world[currentArea].addItem("rusty knife");
                    backpack.remove("rusty knife");
                    endGame = true;
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != VOLCANO)
                System.out.println("I don't see anything to kill here!");
        }

        if (command.equals("hit") || command.equals("break"))
        {
            if (phrase.equals("rock") && currentArea == CAVE)
            {
                System.out.println("What do you want to hit the rock with?");
            }
            else if (phrase.equals("rock with crocodile tooth") && currentArea == CAVE)
            {
                if (backpack.contains("crocodile tooth")){
                    System.out.println("The rock broke!\nThe compulsive riddler appeared behind it holding the flame scepter!");
                    world[currentArea].addItem("crocodile tooth");
                    backpack.remove("crocodile tooth");
                    score += 5;
                    while (riddles == 0){
                        System.out.println("\nI can run but not walk. Wherever I go thought follows close behind. What am I?");
                        String ans = "nose";
                        getUserInput();
                        if (command.equals(ans) || ans.equals(phrase)){
                            System.out.println("\nGood Job.\nNext One:");
                            score++;
                            riddles++;
                        }
                    }
                    while (riddles == 1){
                        System.out.println("\nWhat belongs to you, but others use it more than you?");
                        String ans = "name";
                        getUserInput();
                        if (command.equals(ans) || ans.equals(phrase)){
                            System.out.println("\nGood Job.\nNext One:");
                            score++;
                            riddles++;
                        }
                        else
                            System.out.println("\nTry Again.");
                    }  
                    while (riddles == 2){
                        System.out.println("\nFeed me and I live. Give me a drink and I die. What am I?");
                        String ans = "fire";
                        getUserInput();
                        if (command.equals(ans) || ans.equals(phrase)){
                            System.out.println("\nGood Job.\nNext One:");
                            score++;
                            riddles++;
                        }
                        else
                            System.out.println("\nTry Again.");
                    } 
                    while (riddles == 3){
                        System.out.println("\nForwards, I am heavy. Backwards, I am not. What am I?");
                        String ans = "ton";
                        getUserInput();
                        if (command.equals(ans) || ans.equals(phrase)){
                            System.out.println("\nGood Job.\nNext One:");
                            score++;
                            riddles++;
                        }
                        else
                            System.out.println("\nTry Again.");
                    }  
                    while (riddles == 4){
                        System.out.println("\nWhat holds water but is full of holes?");
                        String ans = "sponge";
                        getUserInput();
                        if (command.equals(ans) || ans.equals(phrase)){
                            System.out.println("\nGood Job.\nThe compulsive riddler ran away leaving the \nflame scepter behind.");
                            world[currentArea].addItem("flame scepter");
                            score++;
                            riddles++;
                        }
                        else
                            System.out.println("\nTry Again.");
                    } 
                    world[CAVE].setLongDescription("The cave is mostly dark, but there is a small red light coming \nfrom the center. The damp ground holds a stream of water ahead. The flapping \nof wings is heard from the cave.\n");
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != CAVE)
                System.out.println("I don't see anything to kill here!");
        }

        if (command.equals("wear"))
        {
            if (!phrase.equals("black jacket") && currentArea == SNOWY_MOUNTAIN)
            {
                System.out.println("Wear What?");
            }
            else if (phrase.equals("black jacket") && currentArea == SNOWY_MOUNTAIN)
            {
                if (backpack.contains("black jacket")){
                    System.out.println("much better!");
                    backpack.remove("black jacket");
                    score += 5;
                    wearingJacket = true;
                    world[SNOWY_MOUNTAIN].setLongDescription("The Snowy Mountain which has claimed more lives than \nmost mountains. Only the hardiest of men have ever made it back from the \nmountain to tell their tale.\n\nA cliff is at the East of the mountin.\n");
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != SNOWY_MOUNTAIN)
                System.out.println("I don't see any reason to wear anything more.");
        }

        if (command.equals("remove"))
        {
            if (!phrase.equals("black jacket") && currentArea == VOLCANIC_ISLAND)
            {
                System.out.println("Remove What?");
            }
            else if (phrase.equals("black jacket") && currentArea == VOLCANIC_ISLAND)
            {
                if (wearingJacket){
                    System.out.println("much better!");
                    wearingJacket = false;
                    score += 5;
                    backpack.add("black jacket");
                    enter++;
                    if (enter == 4){
                        System.out.println("A pattern has shown up on the rock.\n1, 11, 21, 1112, 3112\n");
                        num = reader.readInt("What is the next number in the pattern? ");
                        while (num != 211213)
                            num = reader.readInt("Wrong! What is the next number in the pattern?");
                        System.out.println("The wall opens up!");
                        world[VOLCANIC_ISLAND].setWest(VOLCANO);
                    }
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != VOLCANIC_ISLAND)
                System.out.println("I don't see any reason to remove anything.");
        }

        if (command.equals("put"))
        {
            if (phrase.equals("in rock") || phrase.equals("in hole") && currentArea == VOLCANIC_ISLAND)
            {
                System.out.println("Put what in the rock?");
            }
            else if ((phrase.equals("aquastaff in rock") || phrase.equals("aquastaff in hole")) && currentArea == VOLCANIC_ISLAND)
            {
                if (backpack.contains("aquastaff")){
                    System.out.println("You placed it in the rock.");
                    backpack.remove("aquastaff");
                    enter++;
                    score += 5;
                    if (enter == 4){
                        System.out.println("A pattern has shown up on the rock.\n1, 11, 21, 1112, 3112\n");
                        num = reader.readInt("What is the next number in the pattern? ");
                        while (num != 211213)
                            num = reader.readInt("Wrong! What is the next number in the pattern?");
                        System.out.println("The wall opens up!");
                        world[VOLCANIC_ISLAND].setWest(VOLCANO);
                    }
                }
            }
            else if ((phrase.equals("flame scepter in rock") || phrase.equals("flame scepter in hole")) && currentArea == VOLCANIC_ISLAND)
            {
                if (backpack.contains("flame scepter")){
                    System.out.println("You placed it in the rock.");
                    backpack.remove("flame scepter");
                    enter++;
                    score += 5;
                    if (enter == 4){
                        System.out.println("A pattern has shown up on the rock.\n1, 11, 21, 1112, 3112\n");
                        num = reader.readInt("What is the next number in the pattern? ");
                        while (num != 211213)
                            num = reader.readInt("Wrong! What is the next number in the pattern?");
                        world[VOLCANIC_ISLAND].setWest(VOLCANO);
                        System.out.println("The wall opens up!");
                    }
                }
            }
            else if (currentArea != VOLCANIC_ISLAND)
                System.out.println("I don't see any reason to put anything anywhere.");
            else
                System.out.println("You do not have that item");
        }

        if (command.equals("drink")){
            if (phrase.equals("from") && currentArea == VOLCANIC_ISLAND)
            {
                System.out.println("Drink from what?");
            }
            else if (phrase.equals("water") || phrase.equals("from water bottle") && currentArea == VOLCANIC_ISLAND)
            {
                if (backpack.contains("water bottle")){
                    System.out.println("You drank water.");
                    backpack.remove("water bottle");
                    world[VOLCANIC_ISLAND].addItem("empty water bottle");
                    enter++;
                    score += 1;
                    if (enter == 4){
                        System.out.println("A pattern has shown up on the rock.\n1, 11, 21, 1112, 3112\n");
                        num = reader.readInt("What is the next number in the pattern? ");
                        while (num != 211213)
                            num = reader.readInt("Wrong! What is the next number in the pattern?");
                        System.out.println("The wall opens up!");
                        world[VOLCANIC_ISLAND].setWest(VOLCANO);
                    }
                }
                else
                    System.out.println("You do not have that item");
            }
            else if (currentArea != VOLCANIC_ISLAND)
                System.out.println("I don't see any reason to drink water. Your thirst is quenched");
        }

        if (items >= 9)
            world[SEA].setWest(VOLCANIC_ISLAND);
    }

    private static void displayFarewellMessageAndScore()
    {
        if(score == 50)
            System.out.println("Congratulations!!! You Have Found The Treasure!");
        else
            System.out.println("You have died. :(");
        System.out.println("You scored "+ score +" points out of 50.");
        if(score < 11)
            System.out.println("You have achieved the rank of Failed Warrior.");
        else if (score < 21)
            System.out.println("You have achieved the rank of Amateur Marksman.");
        else if (score < 31)
            System.out.println("You have achieved the rank of Silver Thief.");
        else if (score < 41)
            System.out.println("You have achieved the rank of Professional Javelineer.");
        else if (score < 50)
            System.out.println("You have achieved the rank of Expert Torchbearer.");
        else
            System.out.println("You have achieved the rank of Legendary Mountaineer.");
    }

    private static void inventoryDisplay()
    {
        if (!backpack.isEmpty()){
            System.out.println("The backpack contains:");
            for (int i = 0; i < backpack.size(); i++)
                System.out.println(backpack.get(i));    
        }
        else
            System.out.println("You backpack is empty.");
        System.out.println("\n");
    }

    private static void move (String direction)
    {
        if (direction.equals("north")){
            if (world[currentArea].getNorth() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getNorth();
        }

        if (direction.equals("south")){
            if (world[currentArea].getSouth() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getSouth();
        }

        if (direction.equals("east")){
            if (world[currentArea].getEast() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getEast();
        }

        if (direction.equals("west")){
            if (world[currentArea].getWest() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getWest();
        }

        if (direction.equals("southeast")){
            if (world[currentArea].getSoutheast() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getSoutheast();
        }

        if (direction.equals("southwest")){
            if (world[currentArea].getSouthwest() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getSouthwest();
        }

        if (direction.equals("northeast")){
            if (world[currentArea].getNortheast() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getNortheast();
        }

        if (direction.equals("getNorthwest")){
            if (world[currentArea].getNorthwest() == NO_EXIT)
                System.out.println("Cannot go that way. Turn around.");
            else 
                currentArea = world[currentArea].getNorthwest();
        }
    }
}