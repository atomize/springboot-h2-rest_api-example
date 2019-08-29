package com.berti.notes.model;

public class Notes {

    private Long id;
    private String body;

    public Notes() {
    }

    public Notes(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Notes [id=" + id + ", body=" + body + "]";
    }
}
