package com.op.booktique.vo.show;

import java.util.Date;

import lombok.Data;

@Data
public class ShowVo {


	private String showSq;           // 공연일련번호
	private int hallSq;              // 공연장일련번호
	private String agencyNo;         // 기획사일련번호
	private int discountNo;          // 할인번호
	private String mainImg;          // 메인이미지
	private String subImg;           // 서브이미지
	private String detailImg;        // 상세이미지
	private Date startDate;          // 공연시작일
	private Date endDate;            // 공연종료일
	private Date reserveDate;        // 예매오픈일
	private String showCat;          // 공연구분
	private String showName;         // 공연제목
	private String director;         // 연출
	private String runningtime;      // 공연시간
	private String viewAge;          // 관람연령
	private String showStatus;       // 공연상태
}
