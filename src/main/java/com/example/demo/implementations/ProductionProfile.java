package com.example.demo.implementations;

import com.example.demo.interfaces.SystemProfile;

public class ProductionProfile implements SystemProfile {

    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
