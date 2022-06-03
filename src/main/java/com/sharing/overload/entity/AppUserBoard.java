package com.sharing.overload.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_user_boards")
@JsonIgnoreProperties(ignoreUnknown = true)

@AllArgsConstructor
@NoArgsConstructor
public class AppUserBoard {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @OneToOne(mappedBy = "appUserBoard")
    @Getter @Setter
    private AppUser appUser;

    @Getter @Setter
    private String header;
}
