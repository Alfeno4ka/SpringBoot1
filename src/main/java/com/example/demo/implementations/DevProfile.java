package com.example.demo.implementations;

import com.example.demo.interfaces.SystemProfile;

public class DevProfile implements SystemProfile {

    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
