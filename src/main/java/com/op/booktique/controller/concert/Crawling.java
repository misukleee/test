package com.op.booktique.controller.concert;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.op.booktique.vo.concert.ConcertVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Crawling {
	private WebDriver driver;
	private WebElement element;
	private String url;
	
 	// 1. 드라이버 설치 경로
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "C:/Users/PC-08/chromedriver-win64/chromedriver.exe"; // 크롬드라이버 위치
	
	public Crawling (){
		log.debug("크롤링 생성자 실행");
		// WebDriver 경로 설정
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
				
		// 2. WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
        
		driver = new ChromeDriver(options);
		
		url = "https://tickets.interpark.com/contents/genre/concert";	// 크롤링 할 페이지 경로
		
	}
	
	public void activateBot() {
		log.debug("크롤링 activateBot 실행");
		
		List<ConcertVO> concertList = new ArrayList<ConcertVO>();
		
		try {
			driver.get(url);
			Thread.sleep(3000); // 3. 페이지 로딩 대기 시간
			
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("document.body.style.zoom='33%'");	// 화면비율 줄임
	        Thread.sleep(1000);
			
			// 4. 전체 탭 클릭
			element = driver.findElement(By.xpath("/html/body/div[1]/div/main/article[5]/section/div[2]/div[1]/div/button[1]"));
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			
			element = driver.findElement(By.xpath("/html/body/div[1]/div/main/article[5]/section/div[2]/div[2]"));
			
			WebElement pageElement;
			WebElement concertName;
			WebElement concertPlace;
			WebElement concertDate;
			WebElement concertImage;
			while (true) {
				long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
				List<WebElement> concerts = element.findElements(By.tagName("a"));
				
				for (WebElement concert : concerts) {
					ConcertVO concertVO = new ConcertVO();
					concertName = concert.findElement(By.xpath("./ul/li[1]"));
					concertPlace = concert.findElement(By.xpath("./ul/li[2]"));
					concertDate = concert.findElement(By.xpath("./ul/li[3]"));
					concertImage = concert.findElement(By.xpath("./div/img"));
					String imgSrc = concertImage.getAttribute("src");
					concertVO.setShowName(concertName.getText());
					concertVO.setSubImg(concertPlace.getText());
					concertVO.setDetailImg(concertDate.getText());
					concertVO.setMainImg(imgSrc);
					
					if(concertList.contains(concertVO)) {
						System.out.println("중복!"+concertVO);
					}else {
						System.out.println("안중복!");
						concertList.add(concertVO);
					}
				}
				
				pageElement = driver.findElement(By.xpath("/html/body"));
				pageElement.sendKeys(Keys.END);
				Thread.sleep(5000);	// 중간에 조금 슬립 걸기
				
				long newHeight = (long) js.executeScript("return document.body.scrollHeight");
				
				if(newHeight == lastHeight) break;
			}
			
//			for (ConcertVO concertVO2 : concertList) {
//				System.out.println(concertVO2);
//				System.out.println("=============");
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();	// 5. 브라우저 종료
		}
		
		String fileName = "output.csv";
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
		    for (int i=0; i<concertList.size(); i++) {
		        writer.write((i+1)+";");
		        writer.write(concertList.get(i).getShowName()+";");
		        writer.write(concertList.get(i).getSubImg()+";");
		        writer.write(concertList.get(i).getDetailImg()+";");
		        writer.write(concertList.get(i).getMainImg()+";");
		        writer.write(concertList.get(i).getShowCat()+";");
		        writer.newLine();
		    }
		    String currentDir = System.getProperty("user.dir");
		    System.out.println("파일 저장 디렉토리: " + currentDir);
		    System.out.println("파일에 데이터를 성공적으로 저장했습니다.");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Crawling bot1 = new Crawling();
		bot1.activateBot();
	}
}
