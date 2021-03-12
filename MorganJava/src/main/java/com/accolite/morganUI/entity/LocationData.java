package com.accolite.morganUI.entity;


import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LocationData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "loco_Sequence")
    @SequenceGenerator(name = "loco_Sequence", sequenceName = "LOCATION_SEQ")
    private Long locationId;

    @Column
    private String locationName;

    @Column
    private String city;

    @Column
    private String pincode;

    public LocationData(String locationName, String city, String pincode){
        this.locationName = locationName;
        this.city = city;
        this.pincode = pincode;
    }
}
