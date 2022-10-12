package org.brt;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.*;
import java.util.Arrays;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.brt.entity.DataFactory;
import org.brt.entity.PrimaryNode;
import org.brt.repository.PrimaryNodeRepo;
import org.brt.service.DataFactoryService;
import org.brt.service.PrimaryNodeService;
import org.bson.Document;
import org.bson.types.ObjectId;

@Path("/api")
public class GreetingResource {

    PrimaryNode sample;

    @Inject
    PrimaryNodeRepo primaryNodeRepo;

    @Inject
    DataFactoryService dataFactoryService;

    @Inject
    PrimaryNodeService primaryNodeService;

    @Inject
    MongoClient mongoClient; 

    // creating a new mongoclient if not exist 
    MongoClient getMongoClient(){

        if(mongoClient == null){
            MongoClient mongoClient;
                    }
            return mongoClient;
        }   
    
    // getting root node
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Document getAllNode() {

         MongoClient Document = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("Lesvok");
         var x = database.getCollection("nodeDetails")
            .aggregate(Arrays.asList(lookup ("primaryNode", "refId","id", "children")
            ))
            .first();  
            
            // var y = database.getCollection("primaryNode")
            // .aggregate(Arrays.asList(lookup ("secondaryNode", "refId","id", "children")))
            // .first(); 

            // x.put("children", y);
            return x;
    }    

    // for adding root node(if main node lost)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addNode")
    public Response addData(DataFactory data) {

        dataFactoryService.createNode(data);
        return Response.ok(data).build();
    }

    // for updating node
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateNode/{id}")
    public Response updateNode(@PathParam("id") ObjectId id, DataFactory data) {

        dataFactoryService.updateNode(id, data);
        return Response.ok(data).build();
    }

    // for deleting node
    @DELETE
    @Path("/deleteNode/{id}")
    public Response deleteNode(@PathParam("id") ObjectId id) {
        dataFactoryService.deleteNode(id);
        return Response.ok("Deleted Successfully!!!").build();

    }

    // fetching a node by id
    @GET
    @Path("/getNodeById/{id}")
    public Object getById(@PathParam("id") ObjectId id) {
        return dataFactoryService.getById(id);

    }

    // creating new child node
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addChildNode/{id}")
    public Response createChildNode(@PathParam("id") ObjectId id, PrimaryNode childNode) {

        primaryNodeService.createChildNode(id, childNode);
        return Response.ok(childNode).build();
    }
}