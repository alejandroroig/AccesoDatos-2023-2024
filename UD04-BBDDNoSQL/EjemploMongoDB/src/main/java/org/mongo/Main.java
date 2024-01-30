package org.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.mongo.entities.Driver;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Desactivamos los logs de MongoDB
        Logger logger = LoggerFactory.getLogger("org.mongodb.driver");

        // String uri = "mongodb://usuario:password@host:puerto";
        String uri = "mongodb://arrsan:qwerty1234@ec2-54-146-188-92.compute-1.amazonaws.com:27017";

        // Opción 1: Query a base de datos
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("Conexión con MongoClient sin CodecRegistry establecida");
            // Seleccionamos la base de datos para trabajar
            MongoDatabase database = mongoClient.getDatabase("arrsan");
            // Recogemos la colección "drivers" en una colección de documentos de MongoDB
            MongoCollection<Document> collection = database.getCollection("clients");
            System.out.println("La colección drivers tiene " + collection.countDocuments() + " documentos");
//            Document doc = collection.find(eq("code", "ALO")).first();
//            if (doc != null) {
//                System.out.println(doc.toJson());
//            } else {
//                System.out.println("No se han encontrado documentos que cumplan la condición.");
//            }
        }

        // Opción 2: Uso de CodecRegistry para mapear clases POJO a Documentos
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//            System.out.println("Conexión con MongoClient y CodecRegistry para el trabajo con POJOs");
//
//            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
//            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//
//            MongoDatabase database = mongoClient.getDatabase("f1-2006").withCodecRegistry(pojoCodecRegistry);
//            MongoCollection<Driver> collection = database.getCollection("drivers", Driver.class);
//
//            System.out.println("La colección drivers tiene " + collection.countDocuments() + " documentos");
//            System.out.println(collection.find(eq("code", "ALO")).first());
//
//            System.out.println("\nPilotos españoles antes de insertar a Sainz:");
//            collection.find(eq("nationality", "Spanish")).forEach(System.out::println);
//
//            Driver sainz = new Driver();
//            sainz.setCode("SAI");
//            sainz.setForename("Carlos");
//            sainz.setSurname("Sainz");
//            sainz.setNationality("Spanish");
//            collection.insertOne(sainz);
//
//            System.out.println("\nPilotos españoles tras insertar a Sainz:");
//            collection.find(eq("nationality", "Spanish")).forEach(System.out::println);
//
//            sainz = collection.find(eq("code", "SAI")).first();
//            if (sainz != null) {
//                sainz.setDob(Date.valueOf("1994-09-01"));
//                collection.replaceOne(eq("code", "SAI"), sainz);
//            }
//            System.out.println("\nPilotos españoles tras modificar a Sainz:");
//            collection.find(eq("nationality", "Spanish")).forEach(System.out::println);
//
//            collection.deleteOne(eq("code", "SAI"));
//            System.out.println("\nPilotos españoles tras eliminar a Sainz:");
//            collection.find(eq("nationality", "Spanish")).forEach(System.out::println);
//        }
    }
}