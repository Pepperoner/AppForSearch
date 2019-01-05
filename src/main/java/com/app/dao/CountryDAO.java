package com.app.dao;

import com.app.entity.Country;

import java.util.List;

public interface CountryDAO {

    String getCountryName(long id);

    List <Country> getAll();
}
