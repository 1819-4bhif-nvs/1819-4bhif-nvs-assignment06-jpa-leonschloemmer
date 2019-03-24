package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cars")
@Stateless
public class CarEndpoint {
    @PersistenceContext
    private EntityManager em;

    @GET
    public Response getAllCars() {
        TypedQuery<Car> query = em.createNamedQuery("Car.findAll", Car.class);
        List<Car> cars = query.getResultList();
        return Response.ok().entity(cars).build();
    }

    @GET
    @Path("car")
    public Response getCarById (@QueryParam("id") long id) {
        TypedQuery<Car> query = em.createNamedQuery("Car.findById", Car.class).setParameter("id", id);
        Car car = query.getSingleResult();
        return Response.ok().entity(car).build();
    }

    @POST
    public Response addNewCar(Car car) {
        em.persist(car);
        return Response.ok().entity(car).build();
    }

    @PUT
    public Response updateCar(Car car) {
        em.merge(car);
        return Response.ok(car).build();
    }

    @DELETE
    public Response deleteCar(@QueryParam("id") long id) {
        try {
            TypedQuery<Car> query = em.createNamedQuery("Car.findById", Car.class);
            Car car = query.getSingleResult();
            return Response.ok().entity(car).build();
        } catch (NoResultException e) {
            return Response.status(404).build();
        }
    }
}
