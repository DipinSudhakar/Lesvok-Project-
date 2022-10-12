package org.brt.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.brt.entity.SecondaryNode;
import org.brt.repository.SecondaryNodeRepo;

@ApplicationScoped
public class SecondaryNodeService {

    @Inject
    SecondaryNodeRepo secondaryNodeRepo;

    public Object createChildNode(Object id, SecondaryNode childNode) {

        secondaryNodeRepo.persist(childNode);
        return childNode;
    }
    
}
