package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameter {
    private String key;
    private String value;
    private String description;
    private String clobValue;

    public Parameter() {

    }

    public Parameter(String key, String value, String description, String clobValue) {
        super();
        this.key = key;
        this.value = value;
        this.description = description;
        this.clobValue = clobValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClobValue() {
        return clobValue;
    }

    public void setClobValue(String clobValue) {
        this.clobValue = clobValue;
    }

}
