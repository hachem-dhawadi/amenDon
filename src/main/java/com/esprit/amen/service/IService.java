package com.esprit.amen.service;

import com.esprit.amen.entity.Don;
import com.esprit.amen.entity.Reclamation;

import java.util.List;

public interface IService {
    Don addDon(Don don);
    Don updateDon(Long id, Don don);
    void deleteDon(Long id);
    Don getDonById(Long id);
    List<Don> getAllDons();

    Reclamation addReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Long id, Reclamation reclamation);
    void deleteReclamation(Long id);
    Reclamation getReclamationById(Long id);
    List<Reclamation> getAllReclamations();
}
