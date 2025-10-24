/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

/**
 *
 * @author Stephen Oosthuizen
 */
@Entity
@Transactional
@Table(name = "rainbow_entity")
public class RainbowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tells H2 to auto-increment for us
    private long id;
    @Column
    private String text;
    @Column
    private int length;
    @Column(unique=true)
    private String hash;
    //todo: add a column for the hash algorithm type

    public RainbowEntity(String text, int length, String hash) {
        //this.id = id;
        this.text = text;
        this.length = length;
        this.hash = hash;
    }

    public RainbowEntity() {
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
