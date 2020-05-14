package com.hongwei.model.arcgis.covid19;

public class BeanByAuState {
    public String objectIdFieldName;
    public UniqueIdField uniqueIdField;
    public String globalIdFieldName;
    public Fields[] fields;
    public Features[] features;
}

class UniqueIdField {
    public String name;
    public boolean isSystemMaintained;
}

class Fields {
    public String name;
    public String type;
    public String alias;
    public String sqlType;
    public long length = 0;
    public String domain;
    public String defaultValue;
    public String description = "";
}
