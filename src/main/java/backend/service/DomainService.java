package backend.service;

import backend.entity.table.CgRefCode;
import backend.entity.table.CgRefCodeRepository;
import backend.exceptions.definition.Domain.DomainNotFoundException;
import openAPI.model.DomainValue;
import openAPI.model.SDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainService {
    @Autowired
    private CgRefCodeRepository cgRefCodeRepository;

    public List<SDomain>  getAllDomains(String name) {
        List<CgRefCode> listCg;
        if(name != null) {
            listCg = cgRefCodeRepository.findByRvDomainIgnoreCase(name);
        }else{
            listCg = cgRefCodeRepository.findAll();
        }
        if (listCg.isEmpty())  throw new  DomainNotFoundException();



        List<SDomain> domains = new ArrayList<>();
        listCg.forEach(cgRefCode -> {
            if(!hasDomainWithName(domains, cgRefCode.getRvDomain())) {
                SDomain domain = new SDomain();
                domain.setDomainName(cgRefCode.getRvDomain());
                domains.add(domain);
            }
            // get the sDomain from domain where domainName = cgRefCode.getRvDomain()
            SDomain sDomain = domains.stream().filter(domain -> domain.getDomainName().equals(cgRefCode.getRvDomain())).findFirst().get();
            DomainValue domainValue = new DomainValue().valeur(cgRefCode.getRvLowValue()).désignation(cgRefCode.getRvMeaning());
            sDomain.addValuesItem(domainValue);

        });
        return domains;
    }
    // check if List of SDomain has any domain with name in parameter
    boolean hasDomainWithName(List<SDomain> domains, String name) {
        return domains.stream().anyMatch(domain -> domain.getDomainName().equals(name));
    }
}
