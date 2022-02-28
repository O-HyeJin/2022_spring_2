package com.comento.spring.movie.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.comento.spring.movie.MovieVO;
import com.comento.spring.movie.dao.MovieDAO;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Inject
	private MovieDAO dao;
	
	@Override
	public List<MovieVO> selectMovie() throws Exception {
		return dao.selectMovie();
	}
	
}
