package ru.parsek.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Tool implements Serializable {

	@Id
    @GeneratedValue
    private Long id;

    @Column
    private String label;

    @Column
    private String alloy;
    
    //@OneToMany
    @Column
    private String manufacturer;
    
    public Tool(){
    	
    }

    public Tool(String label, String alloy, String manufacturer) {
		super();
		this.label = label;
		this.alloy = alloy;
		this.manufacturer = manufacturer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAlloy() {
		return alloy;
	}

	public void setAlloy(String alloy) {
		this.alloy = alloy;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	
}
