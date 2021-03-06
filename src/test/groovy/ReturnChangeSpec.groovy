import hello.CoinManager
//import hello.VendingMachine
import spock.lang.Specification

class ReturnChangeSpec extends Specification {
    def "Return Change when Greater Amount"() {
        given: "A coin manager"
        CoinManager cm = new CoinManager();
        and: "total amount customer inserted in vending machine"
        double totalAmount = 2.50

        and: "customer selected a product with price"
        double productPrice = 1.50

        and: "total amount inserted is more than price of product"
        (totalAmount > productPrice) == true

        when: "machine dispenses product"
        double totalAmountOfChange = cm.getChangeInDollarAmount(totalAmount, productPrice)

        then: "machine returns difference in total amount inserted and product price"
        totalAmountOfChange == 1.00
    }

    def "Return change amount in individual coin counts"() {
        given: "total amount of change in dollar amount"
        double totalAmountOfChange = 1.20

        and: "A coin manager"
        CoinManager cm = new CoinManager();

        when: "convert change to coin count"
        String result = cm.returnChangeInCoinCount(totalAmountOfChange)

        then: "return coin count values"
        result.equals("Your change is: " + "\n" + "Quarters: " + "4"
                + "\n" + "Dimes: " + "2"
                + "\n" + "Nickels: " + "0")


    }

    def "Exact Amount of Coin"() {
        given: "A coin manager"
        CoinManager cm = new CoinManager();

        and: "Total amount of Change due back is 0"
        double totalAmountOfChange = 0.0

        when: "coin count change is calculated"
        String result = cm.returnChangeInCoinCount(totalAmountOfChange)

        then: "No change is returned"
        result.equals("Exact amount of coin entered")
    }

    def "Coin amount inserted is not enough for product"(){
        given: "A coin manager"
        CoinManager cm = new CoinManager();

        and: "customer has inserted coins"
        cm.addCustomerCoins("quarter");
        cm.addCustomerCoins("nickel");
        cm.addCustomerCoins("nickel");

        when: "Total amount of change is less than amount of price"
        double totalAmount = 1.0
        double retailPrice = 2.0;
        (totalAmount < retailPrice) == true;

        then: "Money inserted is returned"
        String result = cm.returnCustomerChangeInserted();

        result.equals("Not Enough Coins." + "\n" + "Quarters: " + "1"
                + "\n" + "Dimes: " + "0"
                + "\n" + "Nickels: " + "2");
    }

    def "Remove quarters from machine total coins when total amount inserted in greater"(){
        given: "a vending machine with a coin manager"
        CoinManager cm = new CoinManager();

        and: "quarters removed for change"
        int quarterCountRemoved = 4;

        when: "quarter is removed"
        cm.removeQuatersFromCM(quarterCountRemoved);

        then: "machine quarter count is reduced"
        cm.getQuarter() == 26;
    }
    def "Remove nickels from machine total coins when total amount inserted in greater"(){
        given: "a vending machine with a coin manager"
        CoinManager cm = new CoinManager();

        and: "nickels removed for change"
        int nickelCountRemoved = 4;

        when: "nickel is removed"
        cm.removeNickelsFromCM(nickelCountRemoved);

        then: "machine nickel count is reduced"
        cm.getNickel() == 26;
    }
    def "Remove dimes from machine total coins when total amount inserted in greater"(){
        given: "a vending machine with a coin manager"
        CoinManager cm = new CoinManager();

        and: "dimes removed for change"
        int dimeCountRemoved = 4;

        when: "dime is removed"
        cm.removeDimesFromCM(dimeCountRemoved);

        then: "machine dime count is reduced"
        cm.getDime() == 26;
    }

}