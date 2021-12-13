package com.lb.ecommerce.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    private ModelMapper modelMapper;
    private static MapperUtils instance;

    public MapperUtils() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    static MapperUtils getInstance(){
       if (instance == null)
           instance = new MapperUtils();
       return instance;
    }
    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> getInstance().modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return getInstance().modelMapper.map(source, targetClass);
    }
}
