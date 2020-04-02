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

        while (!action.equals("exit")) {

            while (!action.equals("back")) {
                if (action.equals("buy")) {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                            "back - to main menu:");
                    String buyChoice = sc.nextLine();
                    if (buyChoice.equals("1")) {
                        if (esprWater <= water && esprBeans <= beans && disposableCups > 0) {
                            water -= esprWater;
                            beans -= esprBeans;
                            money += esprMoney;
                            disposableCups -= 1;

                            System.out.println("I have enough resources, making you a coffee!");

                        } else if (esprWater > water && esprBeans > beans && disposableCups < 0) {
                            System.out.println("Sorry, not enough resources!");
                        } else if (esprWater > water) {
                            System.out.println("Sorry, not enough water!");
                        } else if (esprBeans > beans) {
                            System.out.println("Sorry, not enough beans!");
                        } else if (disposableCups < 0) {
                            System.out.println("Sorry, not enough cups!");
                        }
                    } else if (buyChoice.equals("2")) {
                        if (latteWater <= water && latteMilk <= milk && latteBeans <= beans && disposableCups > 0) {
                            water -= latteWater;
                            milk -= latteMilk;
                            beans -= latteBeans;
                            money += latteMoney;
                            disposableCups -= 1;

                            System.out.println("I have enough resources, making you a coffee!");

                        } else if (latteWater > water && latteMilk > milk && latteBeans > beans && disposableCups < 0) {
                            System.out.println("Sorry, not enough resources!");
                        } else if (latteWater > water) {
                            System.out.println("Sorry, not enough water!");
                        } else if (latteMilk > milk) {
                            System.out.println("Sorry, not enough milk!");
                        } else if (latteBeans > beans) {
                            System.out.println("Sorry, not enough beans!");
                        } else if (disposableCups < 0) {
                            System.out.println("Sorry, not enough cups!");
                        }

                    } else if (buyChoice.equals("3")) {
                        if (cappWater <= water && cappMilk <= milk && cappBeans <= beans && disposableCups > 0) {
                            water -= cappWater;
                            milk -= cappMilk;
                            beans -= cappBeans;
                            money += cappMoney;
                            disposableCups -= 1;

                            System.out.println("I have enough resources, making you a coffee!");

                        } else if (cappWater > water && cappMilk > milk && cappBeans > beans && disposableCups < 0) {
                            System.out.println("Sorry, not enough resources!");
                        } else if (cappWater > water) {
                            System.out.println("Sorry, not enough water!");
                        } else if (cappMilk > milk) {
                            System.out.println("Sorry, not enough milk!");
                        } else if (cappBeans > beans) {
                            System.out.println("Sorry, not enough beans!");
                        } else if (disposableCups < 0) {
                            System.out.println("Sorry, not enough cups!");
                        }
                    }
                    System.out.println();
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    action = sc.nextLine();
                    break;
                }
                else if (action.equals("fill")){
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

                    System.out.println();
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    action = sc.nextLine();
                    break;
                }
                else if (action.equals("take")) {
                    System.out.println("I gave you " + money);
                    System.out.println();

                    money -= money;

                    System.out.println();
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    action = sc.nextLine();
                    break;
                }
                else if (action.equals("remaining")) {
                    remaining (water, milk, beans, disposableCups, money);

                    System.out.println();
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    action = sc.nextLine();
                    break;
                }

                action = sc.nextLine();
            }
        }

    }
}

