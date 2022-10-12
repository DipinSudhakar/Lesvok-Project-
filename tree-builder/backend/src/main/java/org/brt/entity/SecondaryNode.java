package org.brt.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import java.util.Date;
import org.brt.enums.NodeType;
import org.bson.types.ObjectId;

@MongoEntity(collection = "secondaryNode")
public class SecondaryNode {

    public ObjectId id;

    public String name;

    public NodeType type;

    public Integer value;

    public Date createDate;

    public Date updateDate;

    public ObjectId refId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public ObjectId getRefId() {
        return refId;
    }

    public void setRefId(ObjectId refId) {
        this.refId = refId;
    }
    
}
