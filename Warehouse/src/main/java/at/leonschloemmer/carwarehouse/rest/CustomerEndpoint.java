package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.Company;
import at.leonschloemmer.carwarehouse.model.people.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
@Stateless
public class CustomerEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getCustomers() {
        TypedQuery<Customer> query = em.createQuery("select c from customer c", Customer.class);
        List<Customer> customers = query.getResultList();
        return Response.ok().entity(customers).build();
    }

    @POST
    public Response addCustomer(Customer customer) {
//        System.out.println("What");
        TypedQuery<Company> query = em.createQuery("select c from company c", Company.class);
        Company c = query.getSingleResult();
        customer.setCompany(c);
        em.persist(customer);
        return Response.ok().build();
    }

}
