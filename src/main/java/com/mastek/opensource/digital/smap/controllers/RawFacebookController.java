package com.mastek.opensource.digital.smap.controllers;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.BSONObject;

import com.mastek.opensource.digital.smap.persistence.MongoCrudService;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Path("RawFacebookQueue")
public class RawFacebookController  {

	
	public String getEntityName() {
		return "RawFacebookCollection";
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String queryDB(String filter){

    	System.out.println("inside queryRegExpDB with String Filter as "+ filter);
    	DBObject query = (DBObject)JSON.parse(filter);
    	System.out.println("inside queryRegExpDB with DB Filter as "+ query);
    	
    	DBCollection coll = MongoCrudService.getConnection().getCollection(getEntityName());
    	String ret = null;
    	DBCursor retCursor = null;
    	try {
    		retCursor = coll.find(query);
        	ret = retCursor.toArray().toString();
		} finally{
        	retCursor.close();			
		}
    	
    	System.out.println("returning from queryRegExpDB with "+ret);
    	return ret;
    }


}
