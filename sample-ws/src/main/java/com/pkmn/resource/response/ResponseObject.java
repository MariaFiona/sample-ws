package com.pkmn.resource.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseObject {

    private String ref;
    private String name;
    private String id;

    public ResponseObject() {

    }

    public ResponseObject(String ref, String name, String id) {
        this.ref = ref;
        this.name = name;
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
