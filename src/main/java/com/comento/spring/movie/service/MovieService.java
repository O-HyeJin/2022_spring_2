package com.comento.spring.movie.service;

import java.util.List;

import com.comento.spring.movie.MovieVO;

public interface MovieService {
	public List<MovieVO> selectMovie() throws Exception;
}
