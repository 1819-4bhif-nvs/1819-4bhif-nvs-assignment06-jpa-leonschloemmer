package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.Manager;
import at.leonschloemmer.carwarehouse.model.Warehouse;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("managers")
@Stateless
public class ManagerEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getAllManagers() {
        TypedQuery<Manager> query = em.createNamedQuery("Manager.findAll", Manager.class);
        List<Manager> managers = query.getResultList();
        return Response.ok().entity(managers).build();
    }

    @GET
    @Path("manager") // TODO Clean up methods, make GET one
    public Response getManagerWithId(@QueryParam("id") Long id) {
        try {
            TypedQuery<Manager> query = em.createNamedQuery("Manager.findById", Manager.class).setParameter("id", id);
            Manager manager = query.getSingleResult();
            return Response.ok().entity(manager).build();
        } catch (NoResultException e) {
            return Response.status(404).build();
        }
    }

    @POST
    public Response newManager(Manager manager) {
        if (setWarehouseOnManager(manager)) return Response.status(404).entity("Warehouse not found").build();
        em.persist(manager);
        return Response.ok().entity(manager).build();
    }

    @PUT
    public Response updateManager(Manager manager) {
        if (setWarehouseOnManager(manager)) return Response.status(404).entity("Warehouse not found").build();
        manager.getWarehouse().setManagers(null);
        em.merge(manager);
        return Response.ok().entity(manager).build();
    }

    @DELETE
    public Response deleteManager(@QueryParam("id") long id) {
        try {
            TypedQuery<Manager> query = em.createNamedQuery("Manager.findById", Manager.class).setParameter("id", id);
            Manager manager = query.getSingleResult();
            manager.getWarehouse().getManagers().remove(manager);
            Warehouse warehouse = manager.getWarehouse();
            manager.setWarehouse(null);
            em.merge(warehouse);
            em.merge(manager);
            em.remove(manager);
            return Response.ok().entity(manager).build();
        } catch (NoResultException e) {
            return Response.status(404).build();
        }
    }

    private boolean setWarehouseOnManager(@NotNull Manager manager) {
        try {
            TypedQuery<Warehouse> query = em.createNamedQuery("Warehouse.findById", Warehouse.class).setParameter("id", manager.getWarehouse().getId());
            Warehouse warehouse = query.getSingleResult();
            manager.setWarehouse(warehouse);
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

}
