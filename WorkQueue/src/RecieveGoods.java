public class RecieveGoods extends Worker{
    public ProductType product;
    public int quantity;
    public double cost;
    public RecieveGoods(ProductType product, int quantity, double cost) {
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }

    @Override
    public void execute(){
        if (product == ProductType.apples){

            System.out.println("Recieved " + quantity + " of apples for $" + cost);
            ECommerce.modifyQuantity(ProductType.apples, quantity);
            ECommerce.modifyFinances((-1) * cost);
            ECommerce.modifyCost(ProductType.apples, cost);

        }
        else if (product == ProductType.bananas){

            System.out.println("Recieved " + quantity + " of bananas for $" + cost);
            ECommerce.modifyQuantity(ProductType.bananas,quantity);
            ECommerce.modifyFinances((-1) * cost);
            ECommerce.modifyCost(ProductType.bananas, cost);
        }
        else{

            System.out.println("Recieved " + quantity + " of oranges for $" + cost);
            ECommerce.modifyQuantity(ProductType.oranges,quantity);
            ECommerce.modifyFinances((-1) * cost);
            ECommerce.modifyCost(ProductType.oranges,cost);
        }



    }
}
