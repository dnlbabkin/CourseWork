package com.example.aeronautics.service;

import com.example.aeronautics.DAO.AeronauticsModel;
import com.example.aeronautics.repository.AeronauticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AeronauticsService {

    private final AeronauticsRepository aeronauticsRepository;

    public AeronauticsModel createAircraft(Long id, AeronauticsModel aeronauticsModel) {
        return aeronauticsRepository.create(id, aeronauticsModel);
    }

    public AeronauticsModel getAircraft(Long id) {
        return aeronauticsRepository.get(id);
    }

    public AeronauticsModel deleteAircraft(Long id) {
        return aeronauticsRepository.delete(id);
    }

    public AeronauticsModel updateAircraft(Long id, AeronauticsModel aeronauticsModel) {
        return aeronauticsRepository.update(id, aeronauticsModel);
    }
}
