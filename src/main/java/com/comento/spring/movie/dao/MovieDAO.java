package com.comento.spring.movie.dao;

import java.util.List;

import com.comento.spring.movie.MovieVO;

public interface MovieDAO {
	public List<MovieVO> selectMovie() throws Exception;
}
