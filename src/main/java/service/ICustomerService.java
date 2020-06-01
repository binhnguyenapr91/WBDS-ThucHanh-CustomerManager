package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ICustomerService implements CustomerService{
    private static Map<Integer,Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1,"Binh","binh@email.com","NewYork"));
        customers.put(2, new Customer(2,"Linh","linh@email.com","HaNoi"));
        customers.put(3, new Customer(3,"Huynh","huynh@email.com","HaiDuong"));
        customers.put(4, new Customer(4,"Thinh","thinh@email.com","HaiPhong"));
        customers.put(5, new Customer(5,"Tuan","tuan@email.com","HaNoi"));
    }
    @Override
    public List<Customer> getAllCustomer() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void createCustomer(Customer customer) {
        customers.put(customer.getId(),customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public void updateCustomer(int id, Customer customer) {
        customers.put(id,customer);
    }

    @Override
    public void deleteCustomerById(int id) {
        customers.remove(id);
    }
}
