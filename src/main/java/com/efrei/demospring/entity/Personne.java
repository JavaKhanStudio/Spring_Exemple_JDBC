package com.efrei.demospring.entity;

import com.efrei.demospring.enums.GenreEnum;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Personne {

    Integer id ;
    String nom ;
    GenreEnum genre ;
    Integer age ;
    Integer maisonId;
}
