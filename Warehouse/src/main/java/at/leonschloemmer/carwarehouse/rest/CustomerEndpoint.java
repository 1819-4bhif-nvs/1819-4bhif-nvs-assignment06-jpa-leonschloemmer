package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.people.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
public class CustomerEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getCustomers() {
        TypedQuery<Customer> query = em.createQuery("select c from customer c", Customer.class);
        List<Customer> customers = query.getResultList();
        return Response.ok().entity(customers).build();
    }

}
