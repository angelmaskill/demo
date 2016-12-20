package com.xml.xml2java.selftool.demoger;

import java.util.List;
public class ClinicalDocument
{ 
private String schemaLocation;
private String realmCode;
private String typeId;
private String templateId;
private String id;
private List<Code>  Code;
private String title;
private List<EffectiveTime>  EffectiveTime;
private String confidentialityCode;
private String languageCode;
private String setId;
private String versionNumber;
private List<RecordTarget>  RecordTarget;
private List<Author>  Author;
private List<Custodian>  Custodian;
private List<LegalAuthenticator>  LegalAuthenticator;
private List<Authenticator>  Authenticator;
private String participant;
private List<RelatedDocument>  RelatedDocument;
private List<ComponentOf>  ComponentOf;
private String component;
public void setSchemaLocation(String schemaLocation) {
this.schemaLocation=schemaLocation;
}
public String getSchemaLocation() {
return schemaLocation;
}
public void setRealmCode(String realmCode) {
this.realmCode=realmCode;
}
public String getRealmCode() {
return realmCode;
}
public void setTypeId(String typeId) {
this.typeId=typeId;
}
public String getTypeId() {
return typeId;
}
public void setTemplateId(String templateId) {
this.templateId=templateId;
}
public String getTemplateId() {
return templateId;
}
public void setId(String id) {
this.id=id;
}
public String getId() {
return id;
}
public void setCode(List<Code>  Code) {
this.Code=Code;
}
public List<Code>  getCode() {
return Code;
}
public void setTitle(String title) {
this.title=title;
}
public String getTitle() {
return title;
}
public void setEffectiveTime(List<EffectiveTime>  EffectiveTime) {
this.EffectiveTime=EffectiveTime;
}
public List<EffectiveTime>  getEffectiveTime() {
return EffectiveTime;
}
public void setConfidentialityCode(String confidentialityCode) {
this.confidentialityCode=confidentialityCode;
}
public String getConfidentialityCode() {
return confidentialityCode;
}
public void setLanguageCode(String languageCode) {
this.languageCode=languageCode;
}
public String getLanguageCode() {
return languageCode;
}
public void setSetId(String setId) {
this.setId=setId;
}
public String getSetId() {
return setId;
}
public void setVersionNumber(String versionNumber) {
this.versionNumber=versionNumber;
}
public String getVersionNumber() {
return versionNumber;
}
public void setRecordTarget(List<RecordTarget>  RecordTarget) {
this.RecordTarget=RecordTarget;
}
public List<RecordTarget>  getRecordTarget() {
return RecordTarget;
}
public void setAuthor(List<Author>  Author) {
this.Author=Author;
}
public List<Author>  getAuthor() {
return Author;
}
public void setCustodian(List<Custodian>  Custodian) {
this.Custodian=Custodian;
}
public List<Custodian>  getCustodian() {
return Custodian;
}
public void setLegalAuthenticator(List<LegalAuthenticator>  LegalAuthenticator) {
this.LegalAuthenticator=LegalAuthenticator;
}
public List<LegalAuthenticator>  getLegalAuthenticator() {
return LegalAuthenticator;
}
public void setAuthenticator(List<Authenticator>  Authenticator) {
this.Authenticator=Authenticator;
}
public List<Authenticator>  getAuthenticator() {
return Authenticator;
}
public void setParticipant(String participant) {
this.participant=participant;
}
public String getParticipant() {
return participant;
}
public void setRelatedDocument(List<RelatedDocument>  RelatedDocument) {
this.RelatedDocument=RelatedDocument;
}
public List<RelatedDocument>  getRelatedDocument() {
return RelatedDocument;
}
public void setComponentOf(List<ComponentOf>  ComponentOf) {
this.ComponentOf=ComponentOf;
}
public List<ComponentOf>  getComponentOf() {
return ComponentOf;
}
public void setComponent(String component) {
this.component=component;
}
public String getComponent() {
return component;
}
}