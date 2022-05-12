package com.example.pillproject;

public class PresDrug {
    //-----fields------//
    private int drugID;
    private String drugName;
    private String drugDesc;

    //-----constructors------//
    public PresDrug()
    {
    }

    public PresDrug(int id, String drugname,String drugdesc)
    {
        this.drugID= id;
        this.drugName=drugname;
        this.drugDesc=drugdesc;
    }

    //-----properties------//
    //drugID
    public void setDrugID(int id)
    {
        this.drugID = id;
    }
    public int getDrugID()
    {
        return this.drugID;
    }

    //drugName
    public void setDrugName(String drugname)
    {
        this.drugName = drugname;
    }
    public String getDrugName()
    {
        return this.drugName;
    }

    //drugDesc
    public void setDrugDesc(String drugdesc)
    {
        this.drugDesc=drugdesc;
    }
    public String getDrugDesc(){return this.drugDesc;}
}


