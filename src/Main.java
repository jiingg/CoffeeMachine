import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Coffee coffeeMachine = new Coffee(400, 540, 120, 9, 550);
        Coffee someCoffee = null;

        State stateCoffeeMachine = State.POWER_ON;
        Processing process = new Processing();
        MainMenu chooseAction;
        CoffeeMenu chooseCoffee;
        String inputString;

        while (stateCoffeeMachine != State.POWER_OFF) {

            switch (stateCoffeeMachine) {

                case POWER_ON:
                    stateCoffeeMachine = State.MAIN_MENU;
                    break;

                case MAIN_MENU:
                    process.showMainMenu();
                    stateCoffeeMachine = State.CHOOSE_ACTION;
                    break;

                case COFFEE_MENU:
                    process.showCoffeeMenu();
                    stateCoffeeMachine = State.CHOOSE_COFFEE;
                    break;

                case CHOOSE_ACTION:
                    chooseAction = null;
                    inputString = scanner.nextLine();

                    for (MainMenu menu : MainMenu.values()) {
                        if (menu.name().equalsIgnoreCase(inputString)) {
                            chooseAction = menu;
                        }
                    }

                    if (chooseAction == null) {
                        System.out.println("Invalid action.");
                        stateCoffeeMachine = State.MAIN_MENU;
                        break;
                    }

                    switch (chooseAction) {
                        case BUY:
                            stateCoffeeMachine = State.COFFEE_MENU;
                            break;
                        case FILL:
                            stateCoffeeMachine = State.FILL_RESOURCES;
                            break;
                        case TAKE:
                            stateCoffeeMachine = State.TAKE_MONEY;
                            break;
                        case REMAINING:
                            stateCoffeeMachine = State.SHOW_RESOURCES;
                            break;
                        case EXIT:
                            stateCoffeeMachine = State.POWER_OFF;
                            break;
                    }
                    break;

                case CHOOSE_COFFEE:
                    someCoffee = null;
                    inputString = scanner.nextLine();

                    if (inputString.equals("back")) {
                        stateCoffeeMachine = State.MAIN_MENU;
                        break;
                    }

                    for (CoffeeMenu menu : CoffeeMenu.values()) {
                        if (menu.getNumber().equalsIgnoreCase(inputString)) {
                            someCoffee = new Coffee(menu.getWater(), menu.getMilk(), menu.getCoffeeBean(), menu.getCost());
                            stateCoffeeMachine = State.MAKE_COFFEE;
                            break;
                        }
                    }

                    if (someCoffee == null) {
                        System.out.println("Incorrect choose!");
                        stateCoffeeMachine = State.COFFEE_MENU;
                        break;
                    }
                    break;

                case MAKE_COFFEE:
                    if (coffeeMachine.checkResources(someCoffee)) {
                        coffeeMachine.spendResources(someCoffee);
                    }
                    stateCoffeeMachine = State.MAIN_MENU;
                    break;

                case FILL_RESOURCES:
                    coffeeMachine.fillResources();
                    stateCoffeeMachine = State.MAIN_MENU;
                    break;

                case TAKE_MONEY:
                    coffeeMachine.takeMoney();
                    stateCoffeeMachine = State.MAIN_MENU;
                    break;

                case SHOW_RESOURCES:
                    coffeeMachine.printResources();
                    stateCoffeeMachine = State.MAIN_MENU;
                    break;
            }
        }
    }
}

enum State {
    MAIN_MENU,
    COFFEE_MENU,
    MAKE_COFFEE,
    FILL_RESOURCES,
    TAKE_MONEY,
    SHOW_RESOURCES,
    POWER_OFF,
    POWER_ON,
    CHOOSE_ACTION,
    CHOOSE_COFFEE;
}

enum MainMenu {
    BUY,
    FILL,
    TAKE,
    REMAINING,
    EXIT;
}

enum CoffeeMenu {
    ESPRESSO("1", 250, 0, 16, 4),
    LATTE("2", 350, 75, 20, 7),
    CAPPUCCINO("3", 200, 100, 12, 6);

    private String str;
    private int water;
    private int milk;
    private int coffeeBean;
    private int cost;

    CoffeeMenu(String str, int water, int milk, int coffeeBean, int cost) {
        this.str = str;
        this.water = water;
        this.milk = milk;
        this.coffeeBean = coffeeBean;
        this.cost = cost;
    }

    public String getNumber() {return str;}
    public int getWater() {return water;}
    public int getMilk() {return milk;}
    public int getCoffeeBean() {return coffeeBean;}
    public int getCost() {return cost;}
}

class Processing {

    private String coffeeMenu;
    private String mainMenu;

    public void showMainMenu() {
        if (this.mainMenu == null) {
            makeMainMenu();
        }
        System.out.println(mainMenu);
    }

    public void showCoffeeMenu() {
        if (this.coffeeMenu == null) {
            makeCoffeeMenu();
        }
        System.out.println(coffeeMenu);
    }

    private void makeMainMenu() {
        String str = "";
        for (MainMenu menu : MainMenu.values()) {
            str += menu.name().toLowerCase() + ", ";
        }
        str = "\nWrite action: " + str.substring(0, str.length() - 2);
        this.mainMenu = str;
    }

    private void makeCoffeeMenu() {
        String str = "";
        for (CoffeeMenu menu : CoffeeMenu.values()) {
            str += menu.getNumber() + " - " + menu.name().toLowerCase() + ", ";
        }
        str = "\nWhat do you want to buy? " + str.substring(0, str.length() - 2);
        this.coffeeMenu = str;
    }
}

class Coffee {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cost;
    private int dispCups;
    private int leftMoney;

    public Coffee(int water, int milk, int coffeeBeans, int dispCups, int leftMoney) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.dispCups = dispCups;
        this.leftMoney = leftMoney;
    }

    public Coffee(int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.dispCups = 1;
        this.cost = cost;
    }

    public void printResources() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water%n", water);
        System.out.printf("%d of milk%n", milk);
        System.out.printf("%d of coffee beans%n", coffeeBeans);
        System.out.printf("%d of disposable cups%n", dispCups);
        System.out.printf("%d of money%n", leftMoney);
    }

    public boolean checkResources(Coffee someCoffee) {
        String str = "";
        boolean check = true;

        if (water < someCoffee.water) {
            str = "water";
            check = false;
        } else if (milk < someCoffee.milk) {
            str = "milk";
            check = false;
        } else if (coffeeBeans < someCoffee.coffeeBeans) {
            str = "coffeeBeans";
            check = false;
        } else if (dispCups < someCoffee.dispCups) {
            str = "disposable cups";
            check = false;
        }

        if (check) {
            System.out.printf("I have enough resources, making you a coffee!\n");
        } else {
            System.out.printf("Sorry, not enough %s!\n", str);
        }

        return check;
    }

    public void spendResources(Coffee someCoffee) {
        water -= someCoffee.water;
        milk -= someCoffee.milk;
        coffeeBeans -= someCoffee.coffeeBeans;
        dispCups -= someCoffee.dispCups;
        leftMoney += someCoffee.cost;
    }

    public void fillResources() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water do you want to add:");
        this.water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        this.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups do you want to add:");
        this.dispCups += scanner.nextInt();
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d.%n", leftMoney);
        leftMoney = 0;
    }
}

