package de.hhu.propra.uav.authorization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class AuthorityService {

  @Value("${arrayOfOrga}")
  private List<String> organisation;

  public boolean isAuthorized(String username) {
    return organisation.contains(username);
  }

  public void checkAuthorization(String username) {
    if(!isAuthorized(username)) {
      throw new HttpClientErrorException(HttpStatus.FORBIDDEN,"fehlende Berechtigungen für diesen Link!");
    }
  }
}
