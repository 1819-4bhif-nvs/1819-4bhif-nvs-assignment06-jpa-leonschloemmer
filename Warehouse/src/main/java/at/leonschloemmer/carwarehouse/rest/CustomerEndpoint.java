package at.leonschloemmer.carwarehouse.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

//    @GET
//    public Response getCustomers() {
//        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
//        List<Customer> customers = query.getResultList();
//        return Response.ok().entity(customers).build();
//    }
//
//    @GET
//    @Path("{id}")
//    public Response getCustomerById(@PathParam("id") long id){
//        TypedQuery<Customer> query = em.createNamedQuery("Customer.findById", Customer.class).setParameter(1, id);
//        Customer customer = query.getSingleResult();
//        return Response.ok().entity(customer).build();
//    }
//
//    @POST
//    public Response addCustomer(Customer customer) {
//        //TypedQuery<Company> query = em.createQuery("select c from Company c", Company.class);
//        TypedQuery<Company> query = em.createNamedQuery("Company.findAll", Company.class);
//        Company c = query.getSingleResult();
//        customer.setCompany(c);
//        em.persist(customer);
//        return Response.ok().build();
//    }
//
//    @PUT
//    public Response updateCustomer(Customer customer) {
//        em.merge(customer);
//        return Response.ok().build();
//    }

    @GET
    public Response getSupercars() {
        return Response.ok().build();
    }
}
