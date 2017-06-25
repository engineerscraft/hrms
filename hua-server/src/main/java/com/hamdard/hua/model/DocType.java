package com.hamdard.hua.model;

/**
 * 
 * Model object designating DocType
 * hrms/api/#_doctype
 */
public class DocType {
    private int     docTypeId;
    private String  docTypeName;
    private boolean identityDoc;
    
    
    public int getDocTypeId() {
        return this.docTypeId;
    }
    public void setDocTypeId(int docTypeId) {
        this.docTypeId = docTypeId;
    }
    public String getDocTypeName() {
        return this.docTypeName;
    }
    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }
    public boolean isIdentityDoc() {
        return this.identityDoc;
    }
    public void setIdentityDoc(boolean identityDoc) {
        this.identityDoc = identityDoc;
    }
}


