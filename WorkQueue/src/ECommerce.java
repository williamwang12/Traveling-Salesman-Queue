public class ECommerce {
    private static Queue<Worker> work = new Queue<Worker>();

    private static double appleCost = 0.0;
    private static double appleProfit = 0.0;
    private static double orangeCost = 0.0;
    private static double orangeProfit = 0.0;
    private static double bananaCost = 0.0;
    private static double bananaProfit = 0.0;

    private static int apples = 0;
    private static int bananas = 0;
    private static int oranges = 0;
    private static double finance = 0.0;


    public static void modifyCost(ProductType p,double d){
        if (p==ProductType.apples){
            appleCost +=d;
        }
        else if (p==ProductType.oranges){
            orangeCost +=d;
        }
        else{
            bananaCost +=d;
        }
    }
    public static void modifyProfit(ProductType p,double d){
        if (p==ProductType.apples){
            appleProfit +=d;
        }
        else if (p==ProductType.oranges){
            orangeProfit +=d;
        }
        else{
            bananaProfit +=d;
        }
    }

    public static double getFinance(){
        return finance;
    }
    public static int returnQuantity(ProductType p){
        if (p == ProductType.apples){
            return apples;
        }
        else if (p == ProductType.bananas){
            return bananas;
        }
        else{
            return oranges;
        }

    }

    public static void modifyQuantity(ProductType p, int q){
        if (p == ProductType.apples){
            apples+=q;
        }
        else if (p == ProductType.bananas){
            bananas+=q;
        }
        else{
            oranges+=q;
        }
    }

    public static void modifyFinances(double quantity){
        finance+=quantity;
    }
    // initialize a queue and any other members as needed
    public ECommerce () {
        work = new Queue<Worker>();

    }

    public static void processRequests()
    {
        while (!work.isEmpty()){
            Worker w = work.dequeue();
            w.execute();
        }

    }
    // Create an instance of ReceiveGoods and enqueue it
    public static void receiveGoods(ProductType product, int quantity, double cost)
    {
        RecieveGoods s = new RecieveGoods(product,quantity, cost);
        work.enqueue(s);


    }
    // Create an instance of SellGoods and enqueue it
    public static void sellGoods(ProductType product, int quantity, double price)
    {
        SellGoods s = new SellGoods(product,quantity,price);
        work.enqueue(s);
    }

    public static void printInventory()
    {
        System.out.println("Apples in stock: " + apples);
        System.out.println("Oranges in stock: " + oranges);
        System.out.println("Bananas in stock: " + bananas);

    }
// Prints the current financials

    public static void printFinancials()
    {
        System.out.println("Cost of apples: $" + appleCost);
        System.out.println("Recieved for apples sold: $" + appleProfit);
        System.out.println("Cost of oranges: $" + orangeCost);
        System.out.println("Recieved for oranges sold: $" + orangeProfit);
        System.out.println("Cost of bananas: $" + bananaCost);
        System.out.println("Recieved for bananas sold: $" + bananaProfit);
        System.out.println("Total Cost: $" + (appleCost + orangeCost + bananaCost));
        System.out.println("Total Recieved; $" + (appleProfit + bananaProfit + orangeProfit));
        System.out.println("Net Profit: $" + ((appleProfit + bananaProfit + orangeProfit) - (appleCost + bananaCost + orangeCost)));

    }
}