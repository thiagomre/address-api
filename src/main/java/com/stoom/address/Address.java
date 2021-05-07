package com.stoom.address;

import com.stoom.config.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
public class Address extends BaseEntity<Long> {

    @NotNull
    @NotBlank
    @Column
    private String streetName;

    @NotNull
    @Column
    private Integer number;

    @Column
    private String complement;

    @NotNull
    @NotBlank
    @Column
    private String neighbourhood;

    @NotNull
    @NotBlank
    @Column
    private String city;

    @NotNull
    @NotBlank
    @Column
    private String state;

    @NotNull
    @NotBlank
    @Column
    private String country;

    @NotNull
    @NotBlank
    @Column
    private String zipcode;

    @Column
    private Double latitude;

    @Column
    private Double longitude;
}
