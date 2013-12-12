package com.mastek.opensource.digital.smap.controllers;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mastek.opensource.digital.smap.persistence.MongoCrudService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
 

public abstract class MongoBaseController {
    
    abstract public String getEntityName();
    abstract public String getKey(); 

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(String entity){
    	DBCollection coll = MongoCrudService.getConnection().getCollection(getEntityName());
    	BasicDBObject row = (BasicDBObject)JSON.parse(entity);
		coll.insert(row);
		
		System.out.println("inserted entity "+row);
    	return "SUCCESS_INSERT";
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String upsert(String entity){
    	
    	DBObject inputRow = (DBObject)JSON.parse(entity);
    	DBCollection coll = MongoCrudService.getConnection().getCollection(getEntityName());
    	
    	DBObject src = coll.findOne(new BasicDBObject(getKey(), inputRow.get(getKey())));
    	if(src == null)
    		src = inputRow;
    	
		coll.update(src, inputRow, true, false);
		
		System.out.println("upserted entity "+inputRow);
    	return "SUCCESS_UPDATE";
    }
    
    @DELETE
    @Produces("text/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String delete(String entity){
		DBCollection coll = MongoCrudService.getConnection().getCollection(getEntityName());
		DBObject inputRow = (DBObject)JSON.parse(entity);
		DBObject src = coll.findOne(new BasicDBObject(getKey(), inputRow.get(getKey())));
		coll.remove(src);
		
		System.out.println("removed entity "+src);
    	return "SUCCESS_DELETE";
    }
    
    
}
