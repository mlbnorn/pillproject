package com.example.pillproject;

public class Drug
{
    public final String brand_name;
    public final String scientific_name;
    public final String drug_class;
    public final String treatment;
    public final String dosage;
    public final String side_effects;
    public final String description;
    public final String interactions;
    public final String warnings;

    public Drug(
            String brand_name,
            String scientific_name,
            String drug_class,
            String treatment,
            String dosage,
            String side_effects,
            String description,
            String interactions,
            String warnings)
    {
        this.brand_name = brand_name;
        this.scientific_name = scientific_name;
        this.drug_class = drug_class;
        this.treatment = treatment;
        this.dosage = dosage;
        this.side_effects = side_effects;
        this.description = description;
        this.interactions = interactions;
        this.warnings = warnings;
    }

}
