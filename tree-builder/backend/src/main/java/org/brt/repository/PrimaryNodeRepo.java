package org.brt.repository;

import javax.enterprise.context.ApplicationScoped;

import org.brt.entity.PrimaryNode;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class PrimaryNodeRepo implements PanacheMongoRepository<PrimaryNode> {

}
