package com.mastek.opensource.digital.smap.processors;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.mastek.opensource.digital.smap.persistence.MongoCrudService;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Normalizer {
	
	
	public void processAll(){
		DB mongoDb = MongoCrudService.getConnection();
		DBCollection table = mongoDb.getCollection("RawFacebookCollection");
		DBCursor cursor = table.find();
		
		String[] ret = new String[(int)table.getCount()];
		System.out.println("get all fetched count = "+ ret.length);
		
		int i = 0;
		DBObject curr = null;
		while (cursor.hasNext()) {
			curr = cursor.next();
			processOneRow(curr);
		}
		
	}
	
	public void processOneRow(DBObject input){
		System.out.println("inside processOneRow with " +input);
		
		try {
			System.out.println("inside processOneRow describe with  " +BeanUtils.describe(input));
		} catch (Exception e) {
			throw new RuntimeException("Exception in processOneRow during beanutils describe of mongo DBObject", e);
		}
		
		
		System.out.println("inside processOneRow with " + ((Map)input.get("interests")).get("data"));
		
	}
	
 

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Normalizer n = new Normalizer();
		n.processAll();


	}

}
