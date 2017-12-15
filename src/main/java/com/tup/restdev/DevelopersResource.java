/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tup.restdev;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import javax.ejb.Asynchronous;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kheim
 * 
 * @Produces(MediaType.APPLICATION_JSON) //default in Java EE7
 * Object wird automatisch in Json zurückgeliefert
 * 
 * Achtung: curl -i -H "" http://localhost:8080/restdev/resources/developers --noproxy localhost
 */
@Path("developers")
public class DevelopersResource {
    
    //synchronous
//    @GET
//    public JsonArray developers(@Context HttpHeaders http) {
//        JsonObject object = Json.createObjectBuilder().add("firstname", "dutches").build();
//        return Json.createArrayBuilder().add(object).build();
//        //throw new RuntimeException("sinnvolle Meldung");
//    }
    
    /* 1. Variant
    @GET
    @Path("{first}-{last}")
    public Developer developer(@PathParam("first") String first, @PathParam("last") String last) {
        return new Developer(first, last);
    }
    */
    @GET
    @Path("{first}-{last}")
    public Response developer(@PathParam("first") String first, @PathParam("last") String last) {
        return Response.status(Status.OK).entity(new Developer(first, last)).build();
    }
    
    @POST
    public Response save(Developer developer, @Context UriInfo info) {
        URI uri = info.getAbsolutePathBuilder().path(developer.getFirst() + "-" + developer.getLast()).build();
        return Response.created(uri).build();
    }
    
    //asynchronous
    @GET
    //@Asynchronous
    public void developers(@Suspended AsyncResponse response) {
        JsonObject object = Json.createObjectBuilder().add("firstname", "dutches").build();
        JsonArray array = Json.createArrayBuilder().add(object).build();
        response.resume(array);
        response.setTimeout(1, TimeUnit.HOURS);
        response.setTimeoutHandler(new TimeoutHandler() {
            
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                
            }
        });
    }
}
