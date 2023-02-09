package com.gtsoft.works.lotto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LottoController {

	private static final String GET_URL = "http://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";
			
	@RequestMapping("/api/getLottoHistory")
	@ResponseBody
	public ResponseEntity<Object> getLottoHistory() {
		List<String> numList = new ArrayList<String>();
		String json = "";
				
		// 1. 최종 회차 조회
		int finalDrwNo = 10;
		CloseableHttpClient httpClient = null;
		try {
			
			// 2. API 호출
			for(int i=1; i<=finalDrwNo; i++) {

				//http client 생성
		        httpClient = HttpClients.createDefault();
		        
		        //get 메서드와 URL 설정
		        HttpGet httpGet = new HttpGet(GET_URL + String.valueOf(i));
		        
		        //agent 정보 설정
		        httpGet.addHeader("User-Agent", "Mozila/5.0");
		        httpGet.addHeader("Content-type", "application/json");
		        
		        //get 요청
		        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		        
		        log.debug("GET Response Status [" + httpResponse.getStatusLine().getStatusCode() + "]");
		        json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		        
		        log.debug(json);
		        numList.add(json);
		        httpClient.close();
			}
		} catch (ClientProtocolException cpe) {
			log.error(cpe.getMessage());
		} catch (IOException ioe) {
			log.error(ioe.getMessage());
		} finally {
			if(null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		
		return new ResponseEntity<>(numList, HttpStatus.OK);
	}
}
