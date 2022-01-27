package com.auto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
@Table(name="STATISTIC")
public class Statistic {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer county_fips;
    private String country_name;
    private String state_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    @Temporal(TemporalType.DATE)
    private Date date;
    private Integer county_vmt;
    private Integer baseline_jan_vmt;
    private Double percentageChangeFromJan;
    private Double meanCountryVmt;
    private Double meanPercentChangeFromJan;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    @Temporal(TemporalType.DATE)
    private Date dateAtLow;
    private Double meanCountryVmtAtLow;
    private Double percentChangeFromLow;

}
