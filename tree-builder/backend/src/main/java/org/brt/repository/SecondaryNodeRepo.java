package org.brt.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;
import org.brt.entity.PrimaryNode;
import org.brt.entity.SecondaryNode;

@ApplicationScoped
public class SecondaryNodeRepo implements PanacheMongoRepository<SecondaryNode> {

    public void persist(PrimaryNode childNode) {
    }
    
}
