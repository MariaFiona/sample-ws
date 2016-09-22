package com.pkmn.service;

import java.util.List;

import com.pkmn.dto.FormeDto;
import com.pkmn.resource.common.ExpandedProperties;

public interface FormeService {

    public List<FormeDto> getFormeDtos(Long speciesId, ExpandedProperties expandedProperties);

    public List<FormeDto> getDefaultFormeDtos(Long speciesId, ExpandedProperties expandedProperties);

}
