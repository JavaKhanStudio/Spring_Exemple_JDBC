package com.efrei.demospring.newway.repo;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class PersonneNewRepo {

    private static final String findPersonByName = "SELECT * FROM personneNew WHERE nom = ? ORDER BY age DESC";

    private static final String findPersonByAgeRange = "SELECT * FROM personneNew WHERE age <= ? AND age >= ?";

    private final JdbcClient jdbcClient;

    public PersonneNewRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }



}
