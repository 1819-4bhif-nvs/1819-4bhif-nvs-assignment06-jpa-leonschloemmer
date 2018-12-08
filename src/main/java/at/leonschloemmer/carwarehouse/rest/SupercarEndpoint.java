package at.leonschloemmer.carwarehouse.rest;

import at.leonschloemmer.carwarehouse.model.cars.Supercar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("supercars")
public class SupercarEndpoint {

    @GET
    public Supercar getSupercar() {
        return new Supercar(610, 250000, 2017, "Lamborghini", "Huracan", 16328, "Pirelli PZero Corsa 320/10", 5.0, "5w-30", "100 Octane", 300, 15);
    }
}
