package com.tcs.toolultimate.dao.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class BaseDAO {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public <T> List<T> fetchDistinctRecords(String collectionName,
			Class<T> recordObjectType) {

		Field[] objAttr = recordObjectType.getDeclaredFields();
		DBObject fields = new BasicDBObject();

		for (Field field : objAttr) {
			fields.put(field.getName(), "$" + field.getName());
		}

		DBObject groupFields = new BasicDBObject("_id", fields);
		DBObject group = new BasicDBObject("$group", groupFields);
		List<DBObject> pipeline = new ArrayList<DBObject>();
		pipeline.add(group);

		AggregationOutput output = mongoTemplate.getCollection(collectionName)
				.aggregate(pipeline);
		Iterable<DBObject> results = output.results();

		List<T> recordObjects = null;
		try {
			ObjectMapper obmap = new ObjectMapper();
			recordObjects = new ArrayList<T>();
			T recordObject = null;
			for (DBObject record : results) {
				recordObject = obmap.readValue(record.get("_id").toString(),
						recordObjectType);
				recordObjects.add(recordObject);
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordObjects;
	}

}
