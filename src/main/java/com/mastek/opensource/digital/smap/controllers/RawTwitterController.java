package com.mastek.opensource.digital.smap.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mastek.opensource.digital.smap.persistence.MongoCrudService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Path("RawTwitterQueue")
public class RawTwitterController  {

	
	public String getEntityName() {
		return "RawTwitterCollection";
	}

	
	public String getKey() {
		return "id";
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


}
