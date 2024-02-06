package org.api.miprimeraapirest.projection;

import org.api.miprimeraapirest.entity.Driver;

import java.util.List;
import java.util.Set;

public interface ConstructorDetails {
    String getId();
    String getName();
    List<DriverDetails> getDrivers();
}
