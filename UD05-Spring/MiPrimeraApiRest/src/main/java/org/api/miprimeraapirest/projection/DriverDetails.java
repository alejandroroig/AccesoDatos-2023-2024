package org.api.miprimeraapirest.projection;

import org.springframework.beans.factory.annotation.Value;

public interface DriverDetails {
    Long getId();
    String getCode();

    // Dentro de la anotación “@Value” se utiliza Spring Expression Language(SpEL)
    @Value("#{target.forename + ' ' + target.surname}")
    String getFullname();
}
