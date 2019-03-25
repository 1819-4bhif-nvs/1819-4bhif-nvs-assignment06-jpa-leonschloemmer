package at.leonschloemmer.warehouse.test;


import org.junit.Before;
import org.junit.Test;

import javax.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class POSTEndpointsIT {

    private Client client;
    private WebTarget target;

    @Before
    public void setup(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/warehouse/rs/");
    }

    @Test
    public void test01_postCustomer() {
        Response firstResponse = target.path("customers").request(MediaType.APPLICATION_JSON).get();
        JsonArray all = firstResponse.readEntity(JsonArray.class);
        int size = all.size();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", "Lukas");
        builder.add("customerid", "lukas01");

        Response response = target.path("customers").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(builder.build().toString(), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(200));

        Response secondResponse = target.path("customers").request(MediaType.APPLICATION_JSON).get();
        all = secondResponse.readEntity(JsonArray.class);
        int newSize = all.size();
        assertThat(size + 1, is(newSize));
    }

    @Test
    public void test02_addManager() {
        Response firstResponse = target.path("managers").request(MediaType.APPLICATION_JSON).get();
        JsonArray all = firstResponse.readEntity(JsonArray.class);
        int size = all.size();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", "Julian");
        JsonObjectBuilder builder1 = Json.createObjectBuilder();
        builder1.add("id", 1);
        builder.add("warehouse", builder1);

        Response response = target.path("managers").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(builder.build().toString(), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(200));

        Response secondResponse = target.path("managers").request(MediaType.APPLICATION_JSON).get();
        all = secondResponse.readEntity(JsonArray.class);
        int newSize = all.size();
        assertThat(size + 1, is(newSize));
    }

    @Test
    public void test03_addWarehouse() {
        Response firstResponse = target.path("warehouses").request(MediaType.APPLICATION_JSON).get();
        JsonArray all = firstResponse.readEntity(JsonArray.class);
        int size = all.size();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("location", "Wien");

        Response response = target.path("warehouses").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(builder.build().toString(), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(200));

        Response secondResponse = target.path("warehouses").request(MediaType.APPLICATION_JSON).get();
        all = secondResponse.readEntity(JsonArray.class);
        int newSize = all.size();
        assertThat(size + 1, is(newSize));
    }

    @Test
    public void test04_addCar() {
        Response firstResponse = target.path("cars").request(MediaType.APPLICATION_JSON).get();
        JsonArray all = firstResponse.readEntity(JsonArray.class);
        int size = all.size();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("horsepower", 500);
        builder.add("make", "Mercedes AMG");
        builder.add("model", "C 63");
        builder.add("value", 100000);

        Response response = target.path("cars").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(builder.build().toString(), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(200));

        Response secondResponse = target.path("cars").request(MediaType.APPLICATION_JSON).get();
        all = secondResponse.readEntity(JsonArray.class);
        int newSize = all.size();
        assertThat(size + 1, is(newSize));
    }

    @Test
    public void test05_addStorage() {
        Response firstResponse = target.path("storages").request(MediaType.APPLICATION_JSON).get();
        JsonArray all = firstResponse.readEntity(JsonArray.class);
        int size = all.size();

        JsonObjectBuilder builder = Json.createObjectBuilder();

        JsonObjectBuilder car = Json.createObjectBuilder();
        car.add("id", 1);
        JsonObjectBuilder manager = Json.createObjectBuilder();
        manager.add("id", 2);
        JsonObjectBuilder customer = Json.createObjectBuilder();
        customer.add("id", 1);
        JsonObjectBuilder service = Json.createObjectBuilder();
        service.add("id", 1);

        JsonArrayBuilder services = Json.createArrayBuilder();
        services.add(service);

        builder.add("car", car);
        builder.add("manager", manager);
        builder.add("customer", customer);
        builder.add("services", services);
        builder.add("endOfStorage", "2019-04-24");
        builder.add("startOfStorage", "2019-03-24");

        Response response = target.path("storages").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(builder.build().toString(), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(200));

        Response secondResponse = target.path("storages").request(MediaType.APPLICATION_JSON).get();
        all = secondResponse.readEntity(JsonArray.class);
        int newSize = all.size();
        assertThat(size + 1, is(newSize));
    }

}
