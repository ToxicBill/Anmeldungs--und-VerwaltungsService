package de.hhu.propra.uav.repositories;

import de.hhu.propra.uav.domains.uebung.Uebung;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UebungRepository extends CrudRepository<Uebung,Long> {

    public List<Uebung> findAll();

    public Optional<Uebung> findByName(String name);

}
