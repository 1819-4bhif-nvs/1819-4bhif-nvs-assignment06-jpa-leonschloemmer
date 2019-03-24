package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.Storage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

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

}
