package jpql;

import Model.Customer;
import Model.Employee;
import Model.JPQL_Order;

/**
 *
 * @author Lukasz Krawczyk
 */

public class JPQL {

    public static void main(String[] args) {
        Facade facade = new Facade();
        
        facade.createEmployee(26, "MyLastName", "MyFirstName", "MyExt", "MyEmail", "The Boss", facade.getOffice("1"));
        
        Customer cust = facade.findCustomer("Baane Mini Imports");
        cust.setCity("Lyngby");
        facade.updateCustomer(cust);
        
        System.out.println(facade.getEmployeeCount());
        
        facade.getCustomerInCity("Lyngby");
        
        
        for (Employee emp : facade.getEmployeeMaxCustomers()) {
            System.out.println(emp.getFirstName());
        }
        
        for (JPQL_Order order : facade.getOrdersOnHold()) {
            System.out.println("Order number: " + order.getOrderNumber() + " ->" + order.getStatus());
        }
        
        for (JPQL_Order order : facade.getOrdersOnHold(144)) {
            System.out.println("Order number: " + order.getOrderNumber() + " ->" + order.getStatus());
        }
          
    }
    
}
