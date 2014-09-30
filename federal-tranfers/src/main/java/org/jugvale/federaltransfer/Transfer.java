/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.federaltransfer;

import java.time.LocalDate;

/**
 *
 * @author william
 */
public class Transfer {

    private LocalDate date;
    private String city;
    private String state;
    private String area;
    private String govProgram;
    private Long value;

    public Transfer() {
    }

    public Transfer(LocalDate date, String city, String state, String area, String govProgram, Long value) {
        this.date = date;
        this.city = city;
        this.state = state;
        this.area = area;
        this.govProgram = govProgram;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGovProgram() {
        return govProgram;
    }

    public void setGovProgram(String govProgram) {
        this.govProgram = govProgram;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
