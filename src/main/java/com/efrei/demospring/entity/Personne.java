package com.efrei.demospring.entity;

import com.efrei.demospring.enums.GenreEnum;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Personne {

    int id ;
    String nom ;
    GenreEnum genre ;
    int age ;
}