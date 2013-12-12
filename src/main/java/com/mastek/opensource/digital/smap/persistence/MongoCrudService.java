package com.mastek.opensource.digital.smap.persistence; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class MongoCrudService {

	private static DB mongoDb = null;
	private static String databaseName = "mydb";
	
	public static String collName = "testData";
	
	
	public static DB getConnection() {
		try {
			if(mongoDb != null)
				return mongoDb;
			
			MongoClient client = new MongoClient( "localhost" , 27017 );
			System.out.println("client = "+client);
			mongoDb = client.getDB(databaseName);
			return mongoDb;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error getting connection - "+e.getMessage());
		}
	}
	
	public static Object insertNewObject(String seed){
		
		DBCollection coll = mongoDb.getCollection(collName);
		
		BasicDBObject document = new BasicDBObject();
		document.put("name", "ganesh ghag"+seed);
		document.put("address", "thane, maharashtra, india"+seed);
		document.put("email", "ganeshghag@email.com"+seed);
		document.put("mobile", "9920392991"+seed);
		document.put("description", "this is a test doc going into mongo db"+seed);
		document.put("age", 40);
		document.put("createdDate", new Date());
		
		coll.insert(document);
		
		return document;
	}
	
	public static String[] getAll(){
		
		DBCollection table = mongoDb.getCollection(collName);
		DBCursor cursor = table.find();
		
		String[] ret = new String[(int)table.getCount()];
		System.out.println("get all fetched count = "+ ret.length);
		
		int i = 0;
		while (cursor.hasNext()) {
			ret[i++] = cursor.next().toString();
		}
		
		return ret;
	}
	
	public static List<Map> getAllAsList(){
		
		DBCollection table = mongoDb.getCollection(collName);
		DBCursor cursor = table.find();
		
		int i = 0;
		ArrayList<Map> ret = new ArrayList<Map>();
		while (cursor.hasNext()) {
			ret.add(cursor.next().toMap());
		}
		
		return ret;
	}
	
	public static void removeAll(){
		
		System.out.println("removing rows...");
		DBCollection table = mongoDb.getCollection(collName);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("age", 40);
		
		table.remove(searchQuery);
	}
	
	public static void updateObject(String seed){
		DBCollection table = mongoDb.getCollection(collName);
		 
		BasicDBObject query = new BasicDBObject();
		query.put("age", "40");
	 
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("name", seed);
	 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
	 
		System.out.println("updating........with "+seed);
		table.update(query, updateObj);
	}

	public static void main(String[] args) {

		String[] allRows;
		System.out.println("connection = "+getConnection());

		System.out.println("inserted = "+insertNewObject(" 1"));
		insertNewObject(" 2");
		insertNewObject(" 3");
		insertNewObject(" 4");
		insertNewObject(" 5");
		
		System.out.println("Getting all rows in collection");
		System.out.println("*****************************");
		allRows = getAll();
		for (int i = 0; i < allRows.length; i++) {
			System.out.println("get all = "+allRows[i]);
		}
		System.out.println("*****************************");
		
		
		
		updateObject("updated by ganya ghag");
		updateObject("updated by ganya ghag");
		updateObject("updated by ganya ghag");
		getAll();

		
		removeAll();
		getAll();
		

	}

}
