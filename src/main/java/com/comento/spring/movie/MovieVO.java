package com.comento.spring.movie;

import lombok.Data;

@Data
public class MovieVO {
	private int movie_id;
	private String movie_name;
	private String director;
	private String type;
}
