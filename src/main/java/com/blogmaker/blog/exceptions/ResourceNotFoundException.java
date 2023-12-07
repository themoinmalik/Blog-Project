package com.blogmaker.blog.exceptions;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{


    String resourceName;
    String filedName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String filedName, Long fieldValue) {
        super(String.format("%s not found with %s : %l", resourceName,filedName,fieldValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
