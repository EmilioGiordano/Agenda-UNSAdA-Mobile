package com.example.agendaunsadatpi.models;

public class CareerPlan {
    private int id;
    private String name;
    private String proposal_code;

    public CareerPlan(int id, String name, String proposal_code) {
        this.id = id;
        this.name = name;
        this.proposal_code = proposal_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProposal_code() {
        return proposal_code;
    }

    public void setProposal_code(String proposal_code) {
        this.proposal_code = proposal_code;
    }

    @Override
    public String toString() {
        return name; // This will be displayed in the spinner
    }
}
