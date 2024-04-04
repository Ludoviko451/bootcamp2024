package com.bootcamp2024.bootcamp2024.domain.spi;
import com.bootcamp2024.bootcamp2024.domain.model.Version;

import java.util.List;

public interface IVersionPersistencePort {

    void saveVersion (Version version);

    List<Version> getAllVersion(Integer page, Integer size, String field, String sortBy, List<Long> bootcampIds);

}
