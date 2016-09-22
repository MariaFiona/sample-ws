package com.pkmn.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpeciesDto extends Dto {
    public List<FormeDto> defaultForme;
    public List<FormeDto> formes;

    public List<FormeDto> getDefaultForme() {
        return defaultForme;
    }

    public void setDefaultForme(List<FormeDto> defaultForme) {
        this.defaultForme = defaultForme;
    }

    public List<FormeDto> getFormes() {
        return formes;
    }

    public void setFormes(List<FormeDto> formes) {
        this.formes = formes;
    }

    public static SpeciesDto buildSpeciesDto(String id, String name) {
        SpeciesDto dto = new SpeciesDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}
