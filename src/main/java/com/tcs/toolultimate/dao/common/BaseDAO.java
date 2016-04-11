package com.tcs.toolultimate.dao.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tcs.toolultimate.controller.UserController;

public class BaseDAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public <T> List<T> fetchDistinctRecords(String collectionName,Map<String,String> matchCriteria,String notNullField,
			Class<T> recordObjectType) {
		
		Field[] objAttr = recordObjectType.getDeclaredFields();
		List<DBObject> pipeline = new ArrayList<DBObject>();
		List<DBObject> matchPipeLine = null;
		
		DBObject matchfields = null;
		DBObject notNullFieldObj = null;
		String[] matchCritValues = null;
		
		if((matchCriteria != null && !matchCriteria.isEmpty()) || !StringUtils.isEmpty(notNullField)){
			matchPipeLine = new ArrayList<DBObject>();
			if(!StringUtils.isEmpty(notNullField)) {
				DBObject notNullOper = new BasicDBObject();
				notNullOper.put("$ne", null);
				notNullFieldObj = new BasicDBObject();
				notNullFieldObj.put(notNullField, notNullOper);
				matchPipeLine.add(notNullFieldObj);
				
			}
			if(matchCriteria != null && !matchCriteria.isEmpty()){
				matchfields = new BasicDBObject();
				for(Map.Entry<String,String> mapentry : matchCriteria.entrySet()){
					if(mapentry.getValue().contains(",")){
						matchCritValues = mapentry.getValue().split(","); 
						DBObject basicDB = new BasicDBObject();
						basicDB.put("$in", matchCritValues);
						matchfields.put(mapentry.getKey(), basicDB);
					}else {
						matchfields.put(mapentry.getKey(), mapentry.getValue());
					}
					
					
				}
				matchPipeLine.add(matchfields);
			}
			
			DBObject andOper = new BasicDBObject();
			andOper.put("$and", matchPipeLine);
			
			DBObject match = new BasicDBObject("$match", andOper);
			pipeline.add(match);
		}
	
		
		DBObject groupfields = new BasicDBObject();

		for (Field field : objAttr) {
			groupfields.put(field.getName(), "$" + field.getName());
		}
		
		DBObject groupFields = new BasicDBObject("_id", groupfields);
		DBObject group = new BasicDBObject("$group", groupFields);
		
		
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
			logger.error("Error while pasring JSON in BaseDAO.fetchDistinctRecords ", e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error("Error while pasring JSON in BaseDAO.fetchDistinctRecords ", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error while pasring JSON in BaseDAO.fetchDistinctRecords ", e);
		}
		return recordObjects;
	}
	
	

}
