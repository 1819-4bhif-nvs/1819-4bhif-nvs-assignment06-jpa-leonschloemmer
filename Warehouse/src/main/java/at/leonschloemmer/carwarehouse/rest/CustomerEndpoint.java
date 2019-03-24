package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
@Stateless
public class CustomerEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getAllCustomers(@QueryParam("id") Long id,
                                    @QueryParam("customerId") String customerId,
                                    @QueryParam("name") String name)
    {
        try {
            if(id == null && customerId == null && name == null) {
                TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
                List<Customer> customers = query.getResultList();
                return Response.ok().entity(customers).build();
            } else if(id != null && customerId == null && name == null) {
                TypedQuery<Customer> query = em.createNamedQuery("Customer.findById", Customer.class).setParameter("id", id);
                Customer customers = query.getSingleResult();
                return Response.ok().entity(customers).build();
            } else if(id == null && customerId != null && name == null) {
                TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCustomerId", Customer.class).setParameter("id", customerId);
                Customer customers = query.getSingleResult();
                return Response.ok().entity(customers).build();
            } else if(id == null && customerId == null && name != null) {
                TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName", Customer.class).setParameter("name", "%" + name + "%");
                List<Customer> customers = query.getResultList();
                return Response.ok().entity(customers).build();
            }
        } catch (NoResultException e) {
            return Response.status(404).build();
        }
        return Response.status(405).build();
    }

    @POST
    public Response newCustomer(Customer customer) {
        em.persist(customer);
        return Response.ok().entity(customer).build();
    }

    @PUT
    public Response updateCustomer(Customer customer) {
        em.merge(customer);
        return Response.ok().entity(customer).build();
    }

    @DELETE
    public Response deleteCustomer(Customer customer) {
        em.remove(customer);
        return Response.ok().entity(customer).build();
    }

}
