package org.brt.service;

import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.brt.entity.DataFactory;
import org.brt.entity.PrimaryNode;
import org.brt.entity.SecondaryNode;
import org.brt.repository.DataFactoryRepo;
import org.brt.repository.PrimaryNodeRepo;
import org.bson.types.ObjectId;

@ApplicationScoped
public class PrimaryNodeService {

    @Inject
    PrimaryNodeRepo primaryNodeRepo;

    @Inject
    DataFactoryRepo dataFactoryRepo;

    @Inject
    SecondaryNodeService secondaryNodeService;

    public Object createChildNode(ObjectId id, PrimaryNode childNode) {

        DataFactory tdata = dataFactoryRepo.findById(id);
        var current_timestamp = new Date();

        if (tdata != null) {

            childNode.setCreateDate(current_timestamp);
            childNode.setUpdateDate(current_timestamp);
            childNode.setRefId(id);
            primaryNodeRepo.persist(childNode);

            return childNode;
        } else {
            SecondaryNode secondaryChildNode = new SecondaryNode();

            secondaryChildNode.setName(childNode.getName());
            secondaryChildNode.setType(childNode.getType());
            secondaryChildNode.setValue(childNode.getValue());
            secondaryChildNode.setRefId(id);
            secondaryChildNode.setCreateDate(current_timestamp);
            secondaryChildNode.setUpdateDate(current_timestamp);

            secondaryNodeService.createChildNode(id, secondaryChildNode);
            return secondaryChildNode;
        }

    }

}
