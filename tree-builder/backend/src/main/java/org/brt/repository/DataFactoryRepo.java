package org.brt.repository;

import javax.enterprise.context.ApplicationScoped;

import org.brt.entity.DataFactory;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class DataFactoryRepo implements PanacheMongoRepository<DataFactory> {

}
