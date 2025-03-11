package com.esprit.amen.service;

import com.esprit.amen.entity.Don;
import com.esprit.amen.entity.Reclamation;
import com.esprit.amen.repository.DonRepository;
import com.esprit.amen.repository.ReclamationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImp implements IService {

    @Autowired
    private DonRepository donRepository;

    @Autowired
    private ReclamationRepo reclamationRepo;

    @Override
    public Don addDon(Don don) {
        if (don.getReclamations() != null) {
            for (Reclamation reclamation : don.getReclamations()) {
                reclamation.setDon(don); // Set the Don for each Reclamation
            }
        }
        return donRepository.save(don);
    }

    @Override
    public Don updateDon(Long id, Don don) {
        Optional<Don> existingDon = donRepository.findById(id);
        if (existingDon.isPresent()) {
            Don updatedDon = existingDon.get();
            updatedDon.setTitre(don.getTitre());
            updatedDon.setDescription(don.getDescription());
            updatedDon.setMontant(don.getMontant());
            updatedDon.setStatus(don.getStatus());
            return donRepository.save(updatedDon);
        } else {
            throw new RuntimeException("Don not found with id: " + id);
        }
    }

    @Override
    public void deleteDon(Long id) {
        if (donRepository.existsById(id)) {
            donRepository.deleteById(id);
        } else {
            throw new RuntimeException("Don not found with id: " + id);
        }
    }

    @Override
    public Don getDonById(Long id) {
        return donRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Don not found with id: " + id));
    }

    @Override
    public List<Don> getAllDons() {
        return donRepository.findAll();
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        // Ensure the Don exists
        Don don = donRepository.findById(reclamation.getDon().getId())
                .orElseThrow(() -> new RuntimeException("Don not found with id: " + reclamation.getDon().getId()));
        reclamation.setDon(don); // Set the Don for the Reclamation
        return reclamationRepo.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Long id, Reclamation reclamation) {
        Optional<Reclamation> existingReclamation = reclamationRepo.findById(id);
        if (existingReclamation.isPresent()) {
            Reclamation updatedReclamation = existingReclamation.get();
            updatedReclamation.setTitre(reclamation.getTitre());
            updatedReclamation.setDescription(reclamation.getDescription());
            updatedReclamation.setType(reclamation.getType());
            return reclamationRepo.save(updatedReclamation);
        } else {
            throw new RuntimeException("Reclamation not found with id: " + id);
        }
    }

    @Override
    public void deleteReclamation(Long id) {
        if (reclamationRepo.existsById(id)) {
            reclamationRepo.deleteById(id);
        } else {
            throw new RuntimeException("Reclamation not found with id: " + id);
        }
    }

    @Override
    public Reclamation getReclamationById(Long id) {
        return reclamationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamation not found with id: " + id));
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepo.findAll();
    }
}