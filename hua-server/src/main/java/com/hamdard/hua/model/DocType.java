package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Model object designating DocType
 * hrms/api/#_doctype
 */
@XmlRootElement
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
	@Override
	public String toString() {
		return "DocType [docTypeId=" + docTypeId + ", docTypeName=" + docTypeName + ", identityDoc=" + identityDoc
				+ "]";
	}
}


