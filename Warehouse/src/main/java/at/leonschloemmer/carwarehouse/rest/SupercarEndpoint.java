package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.cars.Supercar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("supercars")
@Stateless
public class SupercarEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getSupercars() {
        //return new Supercar(610, 250000, 2017, "Lamborghini", "Huracan", 16328, "Pirelli PZero Corsa 320/10", 5.0, "5w-30", "100 Octane", 300, 15);
        TypedQuery<Supercar> query = em.createNamedQuery("Supercar.findAll", Supercar.class);
        List<Supercar> supercarList = query.getResultList();
        return Response.ok().entity(supercarList).build();
    }
}
