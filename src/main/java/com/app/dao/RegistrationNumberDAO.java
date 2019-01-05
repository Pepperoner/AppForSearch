package com.app.dao;

import com.app.entity.RegistrationNumber;

import java.util.List;

public interface RegistrationNumberDAO {

    long getRegisId(String name);

    List<RegistrationNumber> getAll();
}
