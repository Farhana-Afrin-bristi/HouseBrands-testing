package DashBoard;

import Base.BaseTest;
import org.testng.annotations.Test;
import pages.CreateOrderPage;
import pages.CustomerOderPage;
import pages.CustomerOrderlistPage;

public class CreateOrderTests extends BaseTest {

    @Test
    public void CreateOrder() throws InterruptedException {
        CustomerOderPage createCustomerOrder = dashboard.clickOrderManagement();

        createCustomerOrder.GoTocustomerOrderListPage();

        CustomerOrderlistPage createOrderButton = new CustomerOrderlistPage(driver);
        createOrderButton.CreateOrderButton();

        CreateOrderPage createOrder = new CreateOrderPage(driver);
        createOrder.selectCustomerByIndex(2);
        createOrder.selectProduct("Pink sofa");
        createOrder.submit();
        createOrder.addProductQuantity(0, 2);
        createOrder.selectProduct("Red vintage sofa");
        createOrder.submit();
        createOrder.addProductQuantity(1, 3);
        createOrder.addDiscount("Percentage", 1);
        createOrder.addShippingCharge(15);
        createOrder.addShippingAddress(7);
        createOrder.createOrder();
        createOrder.assertOrderCreatedWithToast("Order Placed Successfully");
    }
}
