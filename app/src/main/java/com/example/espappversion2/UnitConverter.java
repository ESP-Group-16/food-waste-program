package com.example.espappversion2;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitConverter {
    private static final ArrayList<String> standardUnits = new ArrayList<String>() {{
        add("teaspoons");
        add("tablespoons");
        add("fluid ounces");
        add("cups");
        add("pints");
        add("quarts");
        add("gallons");
        add("milliliters");
        add("liters");
        add("ounces");
        add("pounds");
        add("grams");
        add("kilograms");
        add("pieces");
        add("dash");
        add("pinch");
        add("clove");
        add("slices");
    }};
    private static HashMap<String, String> units = new HashMap<>();

    // Adding a bunch of unit abbreviations in order to match them to our standard units.
    static {
        // Volume Units
        units.put("tsp", "teaspoon");
        units.put("teaspoon", "teaspoons");
        units.put("tbsp", "tablespoon");
        units.put("tablespoon", "tablespoons");
        units.put("tblsp", "tablespoon");
        units.put("fl oz", "fluid ounces");
        units.put("cup", "cups");
        units.put("pint", "pints");
        units.put("qt", "quarts");
        units.put("gal", "gallons");
        units.put("ml", "milliliters");
        units.put("l", "liters");

        // Weight Units
        units.put("oz", "ounces");
        units.put("lb", "pounds");
        units.put("g", "grams");
        units.put("kg", "kilograms");

        // Count Units
        units.put("pcs", "pieces");
        units.put("pc", "pieces");
        units.put("p", "pieces");

        // Other Units
        units.put("dash", "dash");
        units.put("bunch", "bunch");
        units.put("pinch", "pinch");
        units.put("clove", "cloves");
        units.put("slice", "slices");
        units.put("sliced", "slices");

        // Add more unit mappings as needed
    }

    public static String convertToStandardUnit(String searchedUnit) {
        searchedUnit = searchedUnit.toLowerCase();
        System.out.println("for: " + searchedUnit);
        if (units.containsKey(searchedUnit)) {
            return units.get(searchedUnit);
        } else if (standardUnits.contains(searchedUnit)) {
            return standardUnits.get(standardUnits.indexOf(searchedUnit));
        } else {
            // TODO: handle exception when unit is not found
            return "";
        }
    }

    public static ArrayList<String> getStandardUnits() {
        return standardUnits;
    }


}
