package com.op.booktique.service.impl.camp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.booktique.mapper.camp.CampMapper;
import com.op.booktique.service.camp.CampService;
import com.op.booktique.vo.camp.CampVO;

@Service
public class CampServiceImpl implements CampService {
	
	@Autowired
	private CampMapper campMapper;

	@Override
	public void crawlingData(int startPage, int endPage) {
		
		try {
			//이미지 경로
			String baseUrl = "https://www.gocamping.or.kr";
			
			for (int pageIndex = startPage; pageIndex <= endPage; pageIndex++) {
				String url = String.format("https://www.gocamping.or.kr/bsite/camp/info/list.do?pageUnit=10&searchKrwd=&listOrdrTrget=c_rdcnt&pageIndex=%d", pageIndex);
				System.out.println("URL: " + url);
			
				// Jsoup 사용해서 크롤링해오기!
				Document doc = Jsoup.connect(url).get();
				
				Elements campElements = doc.select("div.camp_search_list > ul > li");
				
				for(Element element : campElements) {
					CampVO campVO = new CampVO();
					
					//campVO 객체에 데이터 넣기!
					String campName = element.select("h2.camp_tt > a").text(); // 캠핑장 이름
					String campAddr = element.select("ul.camp_info01 > li.addr").text(); // 캠핑장 주소
					String campPhone = element.select("ul.camp_info01 > li.call_num").text(); // 캠핑장 전화번호
					String campEtc = element.select("span.camp_stt").text(); // 기타정보
					String campMainImg = element.select("div.img_box img").attr("src"); // 캠핑장 이미지
					
					// 이미지 경로가 상대경로일 경우, 절대경로로 변환
					if (campMainImg != null && !campMainImg.isEmpty() && !campMainImg.startsWith("http")) {
						campMainImg = baseUrl + campMainImg;
					}
					
					//[]뒤의 글씨만 크롤링해오기!
					String selectCampName = campNameText(campName);
					
					campVO.setCampName(selectCampName);
					campVO.setCampAddr(campAddr);
					campVO.setCampPhone(campPhone);
					campVO.setCampEtc(campEtc);
					campVO.setCampMainImg(campMainImg);
					String areaCode = areaCodeChk(campAddr);
					campVO.setAreaCode(areaCode);
					
					System.out.println("Camp Name: " + selectCampName);
					System.out.println("Camp Addr: " + campAddr);
					System.out.println("Camp Phone: " + campPhone);
					System.out.println("Camp Etc: " + campEtc);
					System.out.println("Camp MainImg: " + campMainImg);
					System.out.println("Camp areaCode: " + areaCode);
					System.out.println("-----------------------------------");
					
					campMapper.insertCamp(campVO);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러메시지");
		}
	}
	
	//[]뒤의 글씨만 크롤링해오기!
	private String campNameText(String text) {
		String regex = "\\[.*?\\]";
		String result = text.replaceAll(regex, "").trim();
		return result;
	}
	
	//지역코드 등록조건
	private String areaCodeChk(String address) {
		if (address.contains("경기")) {
			return "ac41";
		} else if (address.contains("강원")) {
			return "ac42";
		} else if (address.contains("충청남도") || address.contains("충청북도")) {
			return "ac43";
		} else if (address.contains("전라남도") || address.contains("전라북도")) {
			return "ac45";
		} else if (address.contains("제주")) {
			return "ac50";
		} else {
			return "ac51";
		}
	}
}
