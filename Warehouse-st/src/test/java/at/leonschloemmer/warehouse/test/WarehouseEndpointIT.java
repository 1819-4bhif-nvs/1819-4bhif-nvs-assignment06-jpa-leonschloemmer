package at.leonschloemmer.warehouse.test;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WarehouseEndpointIT {

    private Client client;
    private WebTarget target;

    @Before
    public void setup(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/warehouse/rs/warehouses/");
    }

    @Test
    public void test01_getAll() {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test02_getWarehouseByLocation() {
        Response response = target.path("warehouse").queryParam("location", "london").request(MediaType.APPLICATION_JSON).get();
        JsonArray cars = response.readEntity(JsonArray.class);
        JsonObject car = cars.getJsonObject(0);
        assertThat(car.getInt("id"), is(1));
    }

    @Test
    public void test03_newWarehouse() {
        JsonObjectBuilder warehouseBuilder = Json.createObjectBuilder();
        warehouseBuilder.add("location", "Linz");
        JsonObject warehouse = warehouseBuilder.build();
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(warehouse.toString(), MediaType.APPLICATION_JSON));
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test04_updateWarehouse() {
        JsonObjectBuilder warehouseBuilder = Json.createObjectBuilder();
        warehouseBuilder.add("id", 2);
        warehouseBuilder.add("location", "Glasgow");
        JsonObject warehouse = warehouseBuilder.build();
        Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(warehouse.toString(), MediaType.APPLICATION_JSON));
        assertThat(response.getStatus(), is(200));
        warehouse = response.readEntity(JsonObject.class);
        assertThat(warehouse.getString("location"), is("Glasgow"));
    }
}
