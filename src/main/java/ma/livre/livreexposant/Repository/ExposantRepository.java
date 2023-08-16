package ma.livre.livreexposant.Repository;

import ma.livre.livreexposant.Model.Exposant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ExposantRepository extends JpaRepository<Exposant, Integer> {
    @RestResource(path = "/exposantByKeyword")
    public List<Exposant> findByNomContains(@Param("mc") String mc);
}



