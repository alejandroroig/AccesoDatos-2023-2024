package org.ficheros.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private long id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;
    private ZonedDateTime created;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", residents=" + residents +
                ", url=" + url +
                ", created=" + created +
                '}';
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
