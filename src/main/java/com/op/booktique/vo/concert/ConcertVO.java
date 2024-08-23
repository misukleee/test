package com.op.booktique.vo.concert;

import java.util.Date;

import lombok.Data;

@Data
public class ConcertVO {
	private String showSq;				//콘서트번호 (ex.C2024081400001)
	private int hallSq;					//공연장번호
	private String agencyNo;
	private int discountNo;
	private String mainImg;				//콘서트포스터
	private String subImg;				//잠깐 공연장명으로 쓰겠음...
	private String detailImg;			//잠깐 공연기간으로 쓰겠음...
	private Date startDate;				//시작일
	private Date endDate;				//종료일
	private Date reserveDate;
	private String showCat = "콘서트";	//공연구분
	private String showName;			//콘서트명
	private String director;
	private String runningtime;
	private String viewAge;
	private String showStatus;

}