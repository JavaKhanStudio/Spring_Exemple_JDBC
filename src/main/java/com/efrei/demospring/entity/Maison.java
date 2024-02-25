package com.efrei.demospring.entity;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Maison {

    int id ;
    String numRue ;
    String nomProprio ;

    List<Personne> occupants ;

}
