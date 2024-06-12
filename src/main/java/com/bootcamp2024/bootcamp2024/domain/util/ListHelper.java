package com.bootcamp2024.bootcamp2024.domain.util;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.ParameterNotValidForOrderbyException;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;


public class ListHelper {


    private ListHelper() {
    }

    public static boolean hasDuplicatesCapacity(List<Capacity> capacityList){

        Set<String> uniqueCapacity = new HashSet<>(Set.copyOf(capacityList.stream().map(Capacity::getName).toList()));

        return  uniqueCapacity.size() != capacityList.size();
    }

    public static boolean hasDuplicatesTechnology(List<Technology> technologyList) {
        Set<String> uniqueTechnology = new HashSet<>(Set.copyOf(technologyList.stream().map(Technology::getName).toList()));
        return uniqueTechnology.size() != technologyList.size();
    }

    public static boolean isValidField(String field, String type) {
        List<String> validFields = new ArrayList<>();
        if (type.equals("type1")) {
            validFields = Arrays.asList("id", "name", "description");
        } else if (type.equals("type2")) {
            validFields = Arrays.asList("id", "maximumCapacity", "startDate", "endDate",  "bootcamp");
        }
        return validFields.contains(field);
    }


    public static Pageable createPageable(Integer page, Integer size, String sortBy, String field) {
        Sort.Direction direction = Sort.Direction.ASC;


        if (sortBy != null) {
            if ("desc".equalsIgnoreCase(sortBy)) {
                direction = Sort.Direction.DESC;
            } else if (!"asc".equalsIgnoreCase(sortBy)) {
                throw new ParameterNotValidForOrderbyException(sortBy);
            }
        }

        return PageRequest.of(page, size, Sort.by(direction, field));
    }
}
