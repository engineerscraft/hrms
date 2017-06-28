package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocumentType {
	
	private long docTypeId;
	private String docTypeName;
	private String identitydoc;
	
	
	public long getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(long docTypeId) {
		this.docTypeId = docTypeId;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}
	public String getIdentitydoc() {
		return identitydoc;
	}
	public void setIdentitydoc(String identitydoc) {
		this.identitydoc = identitydoc;
	}
	
	
}
