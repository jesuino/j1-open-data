/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.campaigndonation;

/**
 *
 * @author william
 */
public class Donation {

    private long amount;
    private String donorName;

    public Donation() {
    }

    public Donation(String donatorName) {
        this.donorName = donatorName;
    }

    public Donation(long amount, String donorName) {
        this.amount = amount;
        this.donorName = donorName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }
    
    
}
