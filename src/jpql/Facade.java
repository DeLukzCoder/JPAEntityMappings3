package jpql;

import Model.Customer;
import Model.Employee;
import Model.JPQL_Order;
import Model.Office;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Lukasz Krawczyk
 */

public class Facade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPQLPU");
    EntityManager em = emf.createEntityManager();
    Query query;
    
    List<Employee> employees;
    List<Customer> customers;
    List<JPQL_Order> orders;
    
    Employee employee;
    Customer customer;
    Office office;
    
    public Customer findCustomer(String name) {
        query = em.createNamedQuery("Customer.findByCustomerName");
        query.setParameter("customerName", name);
        customer = (Customer) query.getSingleResult();
        
        return customer;
    }
    
    public Office getOffice(String id) {
        query = em.createNamedQuery("Office.findByOfficeCode");
        query.setParameter("officeCode", id);
        office = (Office) query.getSingleResult();
        
        return office;
    }
    
    public Employee createEmployee(Integer employeeNumber, String lastName, String firstName, String extension, String email, String jobTitle, Office office) {
        employee = new Employee(employeeNumber, lastName, firstName, extension, email, jobTitle);
        
        em.getTransaction().begin();
        employee.setOfficeCode(office);
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        
        return employee;
    }
    
    public Customer updateCustomer(Customer cust) {
        customer = cust;
        em.getTransaction().begin();
        em.merge(cust);
        em.getTransaction().commit();
        em.close();
        
        return customer;
    }
    
    public int getEmployeeCount() {
        int empSize = 0;
        query = em.createNamedQuery("Employee.findAll");
        employees = query.getResultList();
        for (Employee employee1 : employees) {
            empSize++;
        }
        return empSize;
    }
    
    public List<Customer> getCustomerInCity(String city) {
        query = em.createNamedQuery("Customer.findByCity");
        query.setParameter("city", city);
        customers = query.getResultList();
        
        return customers;
    }
    
    public List<Employee> getEmployeeMaxCustomers() {
        query = em.createNamedQuery("Employee.findAll");
        List<Employee> empList = query.getResultList();
        employees = new ArrayList();
        
        int checkHighest = 0;
        
        for (Employee employee1 : empList) {
            customers = employee1.getCustomerList();
            
            if(customers.size() == checkHighest) {
                employees.add(employee1);
            } else if(customers.size() > checkHighest) {
                employees = new ArrayList();
                employees.add(employee1);
                checkHighest = customers.size();
            }
        }
        
        return employees;
    }
    
    public List<JPQL_Order> getOrdersOnHold() {
        query = em.createNamedQuery("JPQL_Order.findByStatus");
        query.setParameter("status", "On Hold");
        orders = query.getResultList();
        
        return orders;
    }
    
    public List<JPQL_Order> getOrdersOnHold(int customerNumber) {
        query = em.createNamedQuery("JPQL_Order.findByStatusAndCustomer");
        query.setParameter("status", "On Hold");
        query.setParameter("customerNumber", new Customer(customerNumber));
        orders = query.getResultList();
        
        return orders;
    }
}
