package com.copy.deep.demo5;

public class InfoVO {    
    private int id;    
    private String date;    
    private Integer gender;    
    
    @Override
	public String toString() {
		return this.getId()+this.getDate()+this.getId();
	}
    public InfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InfoVO(int id, String date, Integer gender) {
		super();
		this.id = id;
		this.date = date;
		this.gender = gender;
	}
	public int getId() {    
        return id;    
    }    
    public void setId(int id) {    
        this.id = id;    
    }    
    public String getDate() {    
        return date;    
    }    
    public void setDate(String date) {    
        this.date = date;    
    }    
    public Integer getGender() {    
        return gender;    
    }    
    public void setGender(Integer gender) {    
        this.gender = gender;    
    }    
}   