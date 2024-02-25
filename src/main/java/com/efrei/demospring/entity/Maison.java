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
    String nomRue ;
    int numRue ;

    List<Personne> occupants ;

}
