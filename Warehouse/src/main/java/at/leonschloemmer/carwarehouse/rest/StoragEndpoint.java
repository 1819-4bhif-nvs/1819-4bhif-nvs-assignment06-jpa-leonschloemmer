package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("storages")
@Stateless
public class StoragEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response getStorages(@QueryParam("id") Long storageId,
                                @QueryParam("carid") Long carId,
                                @QueryParam("customerid") Long customerId,
                                @QueryParam("managerid") Long managerId,
                                @QueryParam("warehouseid") Long warehouseId)
    {
        try {
            if (storageId == null && carId == null && customerId == null && managerId == null && warehouseId == null)
            {
                TypedQuery<Storage> query = em.createNamedQuery("Storage.findAll", Storage.class);
                List<Storage> storages = query.getResultList();
                return Response.ok().entity(storages).build();
            } else if (storageId != null
                    && carId == null
                    && customerId == null
                    && managerId == null
                    && warehouseId == null)
            {
                TypedQuery<Storage> query = em.createNamedQuery("Storage.findById", Storage.class).setParameter("id", storageId);
                Storage storages = query.getSingleResult();
                return Response.ok().entity(storages).build();
            } else if (storageId == null
                    && carId != null
                    && customerId == null
                    && managerId == null
                    && warehouseId == null)
            {
                TypedQuery<Storage> query = em.createNamedQuery("Storage.findByCarId", Storage.class).setParameter("id", carId);
                List<Storage> storages = query.getResultList();
                return Response.ok().entity(storages).build();
            } else if (storageId == null
                    && carId == null
                    && customerId != null
                    && managerId == null
                    && warehouseId == null)
            {
                TypedQuery<Storage> query = em.createNamedQuery("Storage.findByCustomerId", Storage.class).setParameter("id", customerId);
                List<Storage> storages = query.getResultList();
                return Response.ok().entity(storages).build();
            } else if (storageId == null
                    && carId == null
                    && customerId == null
                    && managerId != null
                    && warehouseId == null)
            {
                TypedQuery<Storage> query = em.createNamedQuery("Storage.findByManagerId", Storage.class).setParameter("id", managerId);
                List<Storage> storages = query.getResultList();
                return Response.ok().entity(storages).build();
            } else if (storageId == null
                    && carId == null
                    && customerId == null
                    && managerId == null
                    && warehouseId != null)
            {
                TypedQuery<Storage> query = em.createNamedQuery("Storage.findByWarehouseId", Storage.class).setParameter("id", warehouseId);
                List<Storage> storages = query.getResultList();
                return Response.ok().entity(storages).build();
            }
        } catch (NoResultException e) {
            return Response.status(404).build();
        }

        return Response.status(405).build();
    }

    public boolean setFieldsOnStorage(Storage storage) {
        try {
            TypedQuery<Car> carQuery = em.createNamedQuery("Car.findById", Car.class).setParameter("id", storage.getCar().getId());
            Car car = carQuery.getSingleResult();
            storage.setCar(car);
        } catch (NoResultException e) {
            return true;
        }
        try {
            TypedQuery<Manager> managerQuery = em.createNamedQuery("Manager.findById", Manager.class).setParameter("id", storage.getManager().getId());
            Manager manager = managerQuery.getSingleResult();
            storage.setManager(manager);
        } catch (NoResultException e) {
            return true;
        }
        try {
            TypedQuery<Customer> customerQuery = em.createNamedQuery("Customer.findById", Customer.class).setParameter("id", storage.getCustomer().getId());
            Customer customer = customerQuery.getSingleResult();
            storage.setCustomer(customer);
        } catch (NoResultException e) {
            return true;
        }
        try {
//            TypedQuery<Service> serviceQuery = em.createNamedQuery("Service.findById", Service.class).setParameter("id", storage.getService().getId());
//            Service service = serviceQuery.getSingleResult();
//            storage.setService(service);
            Set<Service> services = storage.getServices();
            storage.setServices(new HashSet<>());
            for (Service service : services) {
            TypedQuery<Service> serviceQuery = em.createNamedQuery("Service.findById", Service.class).setParameter("id", service.getId());
            service = serviceQuery.getSingleResult();
            storage.getServices().add(service);
            }
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    @POST
    public Response newStorage(Storage storage) {
        if(setFieldsOnStorage(storage)) {
            return Response.status(404).entity("Some target entity not found.").build();
        }
        em.persist(storage);
        return Response.ok().entity(storage).build();
    }

    @PUT
    public Response updateStorage(Storage storage) {
        if(setFieldsOnStorage(storage)) {
            return Response.status(404).entity("Some target entity not found.").build();
        }
        em.merge(storage);
        return Response.ok().entity(storage).build();
    }

    @DELETE
    public Response deleteStorage(@QueryParam("id") Long id) {
        if (id == null) {
            return Response.status(405).entity("id missing").build();
        }
        TypedQuery<Storage> query = em.createNamedQuery("Storage.findById", Storage.class).setParameter("id", id);
        Storage storage = query.getSingleResult();
        em.remove(storage);
        return Response.ok().entity(storage).build();
    }

    // TODO DAOs

}
