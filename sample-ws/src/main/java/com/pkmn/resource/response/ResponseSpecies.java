package com.pkmn.resource.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.pkmn.dto.FormeDto;
import com.pkmn.dto.SpeciesDto;
import com.pkmn.resource.common.ResourceEndpoints;
import com.pkmn.resource.utils.SafeURI;

@XmlRootElement(name = "species")
public class ResponseSpecies extends ResponseObject {

    private final String type = "";

    private List<ResponseForme> formes;
    private List<ResponseForme> defaultFormes;

    public ResponseSpecies() {

    }

    public ResponseSpecies(SpeciesDto dto, String baseUri) {
        super(SafeURI.buildURI(baseUri, ResourceEndpoints.SPECIES_ENDPOINT, dto.getId()), dto.getName(), dto.getId());

        if (dto.getFormes() != null) {
            formes = new ArrayList<ResponseForme>();
            for (FormeDto formeDto : dto.getFormes()) {
                formes.add(new ResponseForme(formeDto, baseUri));
            }
        }

        if (dto.getDefaultForme() != null) {
            defaultFormes = new ArrayList<ResponseForme>();
            for (FormeDto defFormeDto : dto.getDefaultForme()) {
                defaultFormes.add(new ResponseForme(defFormeDto, baseUri));
            }
        }
    }

    @XmlElementWrapper(name = "formes")
    @XmlElement(name = "forme")
    public List<ResponseForme> getFormes() {
        return formes;
    }

    public void setFormes(List<ResponseForme> formes) {
        this.formes = formes;
    }

    @XmlElementWrapper(name = "defaultFormes")
    @XmlElement(name = "forme")
    public List<ResponseForme> getDefaultFormes() {
        return defaultFormes;
    }

    public void setDefaultFormes(List<ResponseForme> defaultFormes) {
        this.defaultFormes = defaultFormes;
    }

    public String getType() {
        return type;
    }

}
