package backend.controller;

import openAPI.api.EtudiantsApi;
import openAPI.model.DeleteEtudiant200Response;
import openAPI.model.ListEtudiants;
import openAPI.model.PEnseignant;
import openAPI.model.PEtudiant;
import org.springframework.http.ResponseEntity;

public class EtudiantController implements EtudiantsApi {

    @Override
    public ResponseEntity<PEtudiant> creerEtudiant(PEtudiant petudiant) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteEtudiant200Response> deleteEtudiant(String noEtudiant) {
        return null;
    }

    @Override
    public ResponseEntity<PEtudiant> getEtudiantById(String noEtudiant) {
        return null;
    }

    @Override
    public ResponseEntity<ListEtudiants> getEtudiants(Integer page, Integer size, String formation, String anneUniv) {
        return null;
    }

    @Override
    public ResponseEntity<PEtudiant> updateEtudiant(String noEtudiant, PEtudiant petudiant) {
        return null;
    }
}
