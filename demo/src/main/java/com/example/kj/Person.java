package com.example.kj;

public class Person {
    private Long personId ;
    private String name ;
    Person( long personId, String name ) {
        this.personId = personId ;
        this.name = name ;
    }

    public Long getPersonId() {
        return this.personId ;
    }
}
