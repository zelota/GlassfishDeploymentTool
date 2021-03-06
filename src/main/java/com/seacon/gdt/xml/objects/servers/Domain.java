package com.seacon.gdt.xml.objects.servers;

import com.seacon.gdt.utility.Utility;
import com.seacon.gdt.xml.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.domain, namespace = "org.moooz.servers")
public class Domain implements Serializable {
    public static final long serialVersionUID = 2015013112L;
    
    private String id;
    private String skip;
    private String action;

    private List<Component> components;
    
    public Domain() {
        this.id = "";
        this.skip = "";
        this.action = "";
        
        this.components = new ArrayList<Component>();
    }

    public Boolean isSkip() {
        return Utility.convertStrToBoolean(this.skip);
    }
    
    public Boolean isCreate() {
        return (this.action != null && "create".equals(this.action.toLowerCase()));
    }

    public Boolean isRestart() {
        return (this.action != null && "restart".equals(this.action.toLowerCase()));
    }

    public Boolean isDrop() {
        return (this.action != null && "drop".equals(this.action.toLowerCase()));
    }

    public Boolean isStart() {
        return (this.action != null && "start".equals(this.action.toLowerCase()));
    }

    public Boolean isStop() {
        return (this.action != null && "stop".equals(this.action.toLowerCase()));
    }
    
    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getSkip() {
        return skip;
    }

    @XmlAttribute
    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getAction() {
        return action;
    }

    @XmlAttribute
    public void setAction(String action) {
        this.action = action;
    }

    public List<Component> getComponents() {
        return components;
    }

    @XmlElementWrapper
    @XmlElement(name=Constants.component)
    public void setComponents(List<Component> components) {
        this.components = components;
    }
    
    
}
