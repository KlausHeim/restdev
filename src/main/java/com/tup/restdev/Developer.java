/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tup.restdev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kheim
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Developer {
    
    @XmlElement(name = "firstname")
    private String first;
    private String last;

    public Developer() {
    }

    public Developer(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
    
    
}
