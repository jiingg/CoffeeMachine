import java.util.Scanner;

public class Main {

    public static void remaining(int water, int milk, int beans, int disposableCups, int money) {

        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println(money + " of money");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int water = 400;
        int milk = 540;
        int beans = 120;
        int disposableCups = 9;
        int money = 550;

        //espresso
        int esprWater = 250;
        int esprBeans = 16;
        int esprMoney = 4;

        //latte
        int latteWater = 350;
        int latteMilk = 75;
        int latteBeans = 20;
        int latteMoney = 7;

        //cappuccino
        int cappWater = 200;
        int cappMilk = 100;
        int cappBeans = 12;
        int cappMoney = 6;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = sc.nextLine();

        while (!action.equals("quit")) {
            if (action.equals("buy")) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                int buyChoice = sc.nextInt();
                switch (buyChoice) {
                    case 1:
                        water -= esprWater;
                        beans -= esprBeans;
                        money += esprMoney;
                        disposableCups -= 1;

                        remaining(water, milk, beans, disposableCups, money);
                        break;
                    case 2:
                        water -= latteWater;
                        milk -= latteMilk;
                        beans -= latteBeans;
                        money += latteMoney;
                        disposableCups -= 1;

                        remaining(water, milk, beans, disposableCups, money);
                        break;
                    case 3:
                        water -= cappWater;
                        milk -= cappMilk;
                        beans -= cappBeans;
                        money += cappMoney;
                        disposableCups -= 1;

                        remaining(water, milk, beans, disposableCups, money);
                        break;
                    default:
                        System.out.println("ERROR");
                }
            } else if (action.equals("fill")) {
                System.out.println("Write how many ml of water do you want to add:");
                int addWater = sc.nextInt();
                System.out.println("Write how many ml of milk do you want to add:");
                int addMilk = sc.nextInt();
                System.out.println("Write how many grams of coffee beans do you want to add:");
                int addBeans = sc.nextInt();
                System.out.println("Write how many disposable cups do you want to add:");
                int addDispCups = sc.nextInt();

                water += addWater;
                milk += addMilk;
                beans += addBeans;
                disposableCups += addDispCups;

                remaining (water, milk, beans, disposableCups, money);
            } else if (action.equals("take")) {
                System.out.println("I gave you " + money);
                System.out.println();

                money -= money;

                remaining (water, milk, beans, disposableCups, money);
            } else if (action.equals("remaining")) {
                System.out.println("The coffee machine has:");
                System.out.println(water + " of water");
                System.out.println(milk + " of milk");
                System.out.println(beans + " of coffee beans");
                System.out.println(disposableCups + " of disposable cups");
                System.out.println(money + " of money");
            } else {
                System.out.println("ERROR");
            }
        }

    }
}

