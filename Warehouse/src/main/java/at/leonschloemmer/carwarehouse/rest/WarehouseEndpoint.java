package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.Car;
import at.leonschloemmer.carwarehouse.model.Warehouse;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("warehouses")
@Stateless
public class WarehouseEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getWarehouseByLocation (@QueryParam("location") String location, @QueryParam("id") Long id) {
        try {
            if (id == null && location != null) {
                TypedQuery<Warehouse> query = em.createNamedQuery("Warehouse.findByLocation", Warehouse.class).setParameter("location", location);
                List<Warehouse> warehouse = query.getResultList();
                return Response.ok().entity(warehouse).build();
            } else if (location == null && id != null){
                TypedQuery<Warehouse> query = em.createNamedQuery("Warehouse.findById", Warehouse.class).setParameter("id", id);
                Warehouse warehouse = query.getSingleResult();
                return Response.ok().entity(warehouse).build();
            } else {
                TypedQuery<Warehouse> query = em.createNamedQuery("Warehouse.findAll", Warehouse.class);
                List<Warehouse> warehouses = query.getResultList();
                return Response.ok().entity(warehouses).build();
            }
        } catch (NoResultException e) {
            return Response.status(404).build();
        }
    }


    @POST
    public Response addNewWarehouse(Warehouse warehouse) {
        em.persist(warehouse);
        return Response.ok().entity(warehouse).build();
    }

    @PUT
    public Response updateWarehouse(Warehouse warehouse) {
        em.merge(warehouse);
        return Response.ok().entity(warehouse).build();
    }

    @DELETE
    public Response deleteWarehouseWithId(@QueryParam("id") long id) {
        try {
            TypedQuery<Warehouse> query = em.createNamedQuery("Warehouse.findById", Warehouse.class).setParameter("id", id);
            Warehouse warehouse = query.getSingleResult();
            em.remove(warehouse);
            return Response.ok().entity(warehouse).build();
        } catch (NoResultException e) {
            return Response.status(404).build();
        }
    }

}
