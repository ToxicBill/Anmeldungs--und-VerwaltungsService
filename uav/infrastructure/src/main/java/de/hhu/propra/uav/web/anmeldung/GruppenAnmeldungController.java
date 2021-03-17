package de.hhu.propra.uav.web.anmeldung;

import de.hhu.propra.uav.domains.applicationservices.AnmeldungService;
import de.hhu.propra.uav.domains.applicationservices.StudentService;
import de.hhu.propra.uav.domains.applicationservices.UebungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GruppenAnmeldungController {

  @Autowired
  private UebungService uebungService;
  @Autowired
  private AnmeldungService anmeldungService;
  @Autowired
  private StudentService studentService;

  @Secured("ROLE_USER")
  @GetMapping("/anmeldung/{uebungId}/{terminId}")
  public String gruppenAnmeldung(final Model model, @PathVariable("uebungId") final Long uebungId,
                                 @PathVariable("terminId") final Long terminId) {
    model.addAttribute("uebung",uebungService.findById(uebungId));
    return "anmeldung/gruppenAnmeldung";
  }

  @Secured("ROLE_USER")
  @PostMapping("/anmeldung/{uebungId}/{terminId}")
  public String gruppenAnmeldungAnmelden(final Model model, @PathVariable("uebungId") final Long uebungId,
                                         @PathVariable("terminId") final Long terminId,
                                         final String gruppenname, final String mitglieder) throws Exception {
    anmeldungService.gruppenAnmeldung(uebungId, terminId, gruppenname, mitglieder);
    return "anmeldung/gruppenAnmeldung";
  }

  @Secured("ROLE_USER")
  @GetMapping("/anmeldung/{uebungId}/restplaetze")
  public String restplaetze(final Model model, @PathVariable("uebungId") final Long uebungId) {
    model.addAttribute("uebung",uebungService.findById(uebungId));
    model.addAttribute("restplaetze", uebungService.findById(uebungId).getRestplaetze());
    model.addAttribute("studenten", studentService.findAllAsMap());
    return "anmeldung/gruppenAnmeldungRestplaetze";

  }

  @Secured("ROLE_USER")
  @PostMapping("/anmeldung/{uebungId}/{terminId}/restplaetze/anmelden")
  public String restplatzAnmeldung(@PathVariable("uebungId") final Long uebungId,
                                   @PathVariable("terminId") final Long terminId,
                                   final @AuthenticationPrincipal OAuth2User principal) {
    anmeldungService.restAnmeldung(uebungId,terminId, principal.getAttribute("login"));
    return "redirect:/anmeldung/{uebungId}/restplaetze";
  }
}