public class SellGoods extends Worker {
    public ProductType product;
    public int quantity;
    public double cost;
    public SellGoods(ProductType product, int quantity, double price){
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }

    @Override
    public void execute() {


        if (product == ProductType.apples){
            if (ECommerce.returnQuantity(ProductType.apples) < quantity){
                System.out.println("Failed to sell " + quantity + " apples due to insufficient inventory." );
            }
            else{
                System.out.println("Sold " + quantity + " apples for $" + cost);
                ECommerce.modifyQuantity(ProductType.apples, quantity);
                ECommerce.modifyProfit(ProductType.apples,cost);
            }
        }
        else if (product == ProductType.bananas){
            if (ECommerce.returnQuantity(ProductType.bananas) < quantity){
                System.out.println("Failed to sell " + quantity + " bananas due to insufficient inventory." );
            }
            else{
                System.out.println("Sold " + quantity + " bananas for $" + cost);
                ECommerce.modifyQuantity(ProductType.bananas,quantity);
                ECommerce.modifyProfit(ProductType.bananas,cost);

            }
        }
        else{
            if (ECommerce.returnQuantity(ProductType.oranges) < quantity){
                System.out.println("Failed to sell " + quantity + " oranges due to insufficient inventory." );
            }
            else{
                System.out.println("Sold " + quantity + " oranges for $" + cost);
                ECommerce.modifyQuantity(ProductType.oranges,quantity);
                ECommerce.modifyProfit(ProductType.oranges,cost);
            }

        }

        ECommerce.modifyFinances(cost);
    }
}
