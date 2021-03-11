package de.hhu.propra.uav.domains.services;


import de.hhu.propra.uav.domains.annotations.ApplicationService;
import de.hhu.propra.uav.domains.model.uebung.Modus;
import de.hhu.propra.uav.domains.model.uebung.Uebung;
import de.hhu.propra.uav.domains.model.uebung.UebungRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationService
public class UebungService {

  @SuppressWarnings("PMD")
  private final UebungRepository uebungRepository;

  @SuppressWarnings("PMD")
  public UebungService(final UebungRepository uebungRepository){
    this.uebungRepository = uebungRepository;
  }

  @SuppressWarnings("PMD")
  public Uebung createDefault() {
    return new Uebung("DEFAULT", Modus.DEFAULT, 0, 0,
        LocalDateTime.now().plus(5,ChronoUnit.MINUTES),
        LocalDateTime.now().plus(1, ChronoUnit.WEEKS));
  }

  public void save(final Uebung uebung) {
    uebungRepository.save(uebung);
  }

  @SuppressWarnings("PMD")
  public Uebung findById(final Long id) {
    return uebungRepository.findById(id).orElseThrow(() ->
            new HttpClientErrorException(HttpStatus.NOT_FOUND,"Keine Uebung mit " + id + " vorhanden!"));
  }

  @SuppressWarnings("PMD")
  public Uebung findByName(final String name) {
    return uebungRepository.findByName(name).orElseThrow(() ->
        new HttpClientErrorException(HttpStatus.NOT_FOUND,"Keine Uebung mit " + name + " vorhanden!"));
  }

  public List<Uebung> findAll() {
    return uebungRepository.findAll();
  }

  public void addGruppe(final Long uebungId, final Long terminId, final String gruppenname) {
    Uebung uebung = findById(uebungId);
    uebung.addGruppe(gruppenname, terminId);
    save(uebung);
  }

  public void deleteGruppe(final Long uebungId, final Long terminId) {
    Uebung uebung = findById(uebungId);
    uebung.deleteGruppe(terminId);
    save(uebung);
  }

  public void addTermin(final long uebungId, final String tutor, final LocalDateTime zeitpunkt) {
    Uebung uebung = findById(uebungId);
    uebung.addTermin(tutor, zeitpunkt);
    save(uebung);
  }

}
