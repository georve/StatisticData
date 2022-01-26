package com.auto.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
public class Statistic {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer county_fips;
    private String country_name;
    private String state_name;
    private Date date;
    private Integer county_vmt;

}
