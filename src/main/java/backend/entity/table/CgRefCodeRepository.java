package backend.entity.table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CgRefCodeRepository extends JpaRepository<CgRefCode, Long> {
    List<CgRefCode> findByRvDomainIgnoreCase(String rvDomain);

}