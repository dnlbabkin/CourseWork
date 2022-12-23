package com.example.aeronautics.repository;

import com.example.aeronautics.DAO.AeronauticsModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AeronauticsRepository {

    private final Map<Long, AeronauticsModel> storage = new HashMap<>();

    public AeronauticsModel create(Long id, AeronauticsModel model)  {
        return storage.put(id, model);
    }

    public AeronauticsModel get(Long id) {
        return storage.get(id);
    }

    public AeronauticsModel delete(Long id) {
        return storage.remove(id);
    }

    public AeronauticsModel update(Long id, AeronauticsModel model) {
        return storage.put(id, model);
    }
}
