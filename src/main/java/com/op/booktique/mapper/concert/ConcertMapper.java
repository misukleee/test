package com.op.booktique.mapper.concert;

import java.util.List;

import com.op.booktique.vo.concert.ConcertVO;

public interface ConcertMapper {
	public int add(ConcertVO concertVO);
	public List<ConcertVO> list();
}
