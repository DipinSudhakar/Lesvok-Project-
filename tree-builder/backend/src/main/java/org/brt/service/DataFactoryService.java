package org.brt.service;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import org.brt.entity.DataFactory;
import org.brt.entity.PrimaryNode;
import org.brt.entity.SecondaryNode;
import org.brt.repository.DataFactoryRepo;
import org.brt.repository.PrimaryNodeRepo;
import org.brt.repository.SecondaryNodeRepo;
import org.bson.types.ObjectId;

@ApplicationScoped
public class DataFactoryService {

    // @Inject
    // MongoClient mongoClient;


    @Inject
    DataFactoryRepo dataFactoryRepo;

    @Inject
    PrimaryNodeRepo primaryNodeRepo;

    @Inject
    SecondaryNodeRepo secondaryNodeRepo;

    public List<DataFactory> getAllData() {
        List<DataFactory> list = dataFactoryRepo.listAll();
        return list;

    }

    public void createNode(DataFactory data) {

        var current_timestamp = new Date();

        data.setCreateDate(current_timestamp);
        data.setUpdateDate(current_timestamp);
        dataFactoryRepo.persist(data);

    }

    public Object updateNode(ObjectId id, DataFactory data) {

        var current_timestamp = new Date();

        DataFactory tNode = dataFactoryRepo.findById(id);

        if (tNode != null) {

            tNode.setUpdateDate(current_timestamp);
            tNode.setName(data.getName());
            tNode.setType(data.getType());
            tNode.setValue(data.getValue());
            dataFactoryRepo.persistOrUpdate(tNode);
            return tNode;

        } else{

            PrimaryNode pNode = primaryNodeRepo.findById(id);

            if(pNode != null){

            pNode.setUpdateDate(current_timestamp);
            pNode.setName(data.getName());
            pNode.setType(data.getType());
            pNode.setValue(data.getValue());
            primaryNodeRepo.persistOrUpdate(pNode);

            return pNode;
            }else{

                SecondaryNode sNode = secondaryNodeRepo.findById(id);

                if(sNode != null){

                sNode.setUpdateDate(current_timestamp);
                sNode.setName(data.getName());
                sNode.setType(data.getType());
                sNode.setValue(data.getValue());
                secondaryNodeRepo.persistOrUpdate(sNode);

                return sNode;
                }else{
                    throw new NotFoundException();
                }
            }
        }

    }

    public void deleteNode(ObjectId id) {

        DataFactory tNode = dataFactoryRepo.findById(id);
        if(tNode != null){
            dataFactoryRepo.deleteById(id);
        }else {
            PrimaryNode pNode = primaryNodeRepo.findById(id);
            if(pNode != null){
                primaryNodeRepo.deleteById(id);
            }else{
                SecondaryNode sNode = secondaryNodeRepo.findById(id);
                if(sNode != null){
                    secondaryNodeRepo.deleteById(id);
                }else{
                    throw new NotFoundException();
                }
            }
        }
    }

    public List<DataFactory> getAllById(ObjectId id) {
        List<DataFactory> list = dataFactoryRepo.list("referenceId", id);
        return list;

    }

    public Object getById(ObjectId id) {

        DataFactory tNode = dataFactoryRepo.findById(id);

        if(tNode != null){
            return tNode;
        }else{
            PrimaryNode pNode = primaryNodeRepo.findById(id);

            if(pNode != null){
                    return pNode;
            }else{
                SecondaryNode sNode = secondaryNodeRepo.findById(id);

                if(sNode != null){
                    return sNode;
                }else{
                    throw new NotFoundException();
                }
            }
        }


    }

}