package com.bootcamp2024.bootcamp2024.domain.util;

import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;

import java.util.*;


public class ListHelper {



    /**
     * Verifica si hay elementos duplicados en una lista de capacidades.
     *
     * @param capacityList  la lista de la cual se quiere verificar la presencia de duplicados.
     * @return true si hay duplicados, false en caso contrario.
     */
    public static boolean hasDuplicatesCapacity(List<Capacity> capacityList){

        Set<String> uniqueCapacity = new HashSet<>(Set.copyOf(capacityList.stream().map(Capacity::getName).toList()));

        return  uniqueCapacity.size() != capacityList.size();
    }

    /**
     * Verifica si hay elementos duplicados en una lista de tecnologias.
     *
     * @param technologyList  la lista de la cual se quiere verificar la presencia de duplicados.
     * @return true si hay duplicados, false en caso contrario.
     */
    public static boolean hasDuplicatesTechnology(List<Technology> technologyList) {
        // Crear un conjunto (Set) a partir de la lista para eliminar duplicados
        Set<String> uniqueTechnology = new HashSet<>(Set.copyOf(technologyList.stream().map(Technology::getName).toList()));
        // Si el tamaño del conjunto es diferente al tamaño de la lista, significa que hay duplicados
        return uniqueTechnology.size() != technologyList.size();
    }

    public static boolean isValidField(String field) {
        List<String> validFields = Arrays.asList("id", "maximumCapacity", "startDate", "endDate",  "bootcamp");
        return validFields.contains(field);
    }

}
