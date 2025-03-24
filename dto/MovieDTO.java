package com.tdp.popcorn_palace.dto;

import lombok.Data;

@Data
public class MovieDTO {
    private String title;
    private String genre;
    private int duration; 
    private String rating; 
    private int releaseYear;
}
