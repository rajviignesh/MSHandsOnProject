package com.accolite.morganUI.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRequest {

    private String locationName;
    private String city;
    private String pincode;
}
