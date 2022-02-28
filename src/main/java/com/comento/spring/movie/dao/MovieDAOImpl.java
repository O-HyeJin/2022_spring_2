package com.comento.spring.movie.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.comento.spring.movie.MovieVO;

@Repository
public class MovieDAOImpl implements MovieDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.comento.mybatis.sql.test";

	@Override
	public List<MovieVO> selectMovie() throws Exception {
		return sqlSession.selectList(namespace+".selectMovie");
	}

}
