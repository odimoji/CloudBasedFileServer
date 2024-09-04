/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxapplication1;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ntu-user
 */
public class Filemetadata {
    private SimpleStringProperty name;
    private SimpleStringProperty size;
    private SimpleStringProperty owner;
    private SimpleStringProperty creationdate;
    private SimpleStringProperty modificationdate;
    
    
    Filemetadata(String name, String size, String owner, String creationdate, String modificationdate) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
        this.owner = new SimpleStringProperty(owner);
        this.creationdate = new SimpleStringProperty(creationdate);
        this.modificationdate = new SimpleStringProperty(modificationdate);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSize() {
        return size.get();
    }

    public void setSize(String size) {
        this.size.set(size);
    }
    
    public String getOwner() {
        return owner.get();
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }
    
    public String getCreationdate() {
        return creationdate.get();
    }

    public void setCreationdate(String creationdate) {
        this.creationdate.set(creationdate);
    }
    
    public String getModificationdate() {
        return modificationdate.get();
    }

    public void setModificationdate(String modificationdate) {
        this.modificationdate.set(modificationdate);
    }

    


}

    
