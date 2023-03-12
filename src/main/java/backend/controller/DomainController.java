package backend.controller;

import backend.service.DomainService;
import lombok.RequiredArgsConstructor;
import openAPI.api.DomainsApi;
import openAPI.model.SDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class DomainController implements DomainsApi{
    @Autowired
    DomainService domainService;
    @Override
    public ResponseEntity<List<SDomain>> getDomains(String name) {
        return ResponseEntity.ok(domainService.getAllDomains(name));
    }
}
