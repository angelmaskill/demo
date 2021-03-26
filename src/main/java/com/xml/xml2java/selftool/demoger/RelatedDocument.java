package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class RelatedDocument {
    private String typeCode;
    private List<ParentDocument> ParentDocument;

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setParentDocument(List<ParentDocument> ParentDocument) {
        this.ParentDocument = ParentDocument;
    }

    public List<ParentDocument> getParentDocument() {
        return ParentDocument;
    }
}