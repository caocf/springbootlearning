package com.sos.fleet.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sos.fleet.common.dto.LocationDto;
import com.sos.fleet.common.util.bean.ResultHolder;

@ConditionalOnProperty(name="mock.ws.enable",havingValue="true",matchIfMissing=false)
@RestController
@RequestMapping("mock/lvds")
public class MockWsRestController {
	
	private static double minLat =  28d;
	private static double maxLat = 40d;
	private static double minLon =  90d;
	private static double maxLon = 125d;
	private static Double[] directions = {111d,222d,333d,344d};
	@RequestMapping("getLocationByVin")
	public Object getLocationByVin(@RequestParam("vin") String vin){
		
		ResultHolder<List<LocationDto>> responseData = new ResultHolder<List<LocationDto>>();
		List<LocationDto> list = new ArrayList<LocationDto>(1);
		responseData.setData(list);
		list.add(new LocationDto(vin, getRandomSpeed(), getRandomLat(), getRandomLon(), getRandomDirection(),( System.currentTimeMillis()%2==0?1:0), new Date()));
		return responseData;
	}
	public static Double getRandomDirection(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int index = rand.nextInt(4);
		return directions[index%3];
	}
	public static double getRandomSpeed(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		return rand.nextInt(350);
	}
	public static double getRandomLat(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int base = rand.nextInt((int)maxLat);
		double randDouble = ((double)base)+rand.nextDouble();
		return randDouble%(maxLat-minLat)+minLat;
	}
	public static double getRandomLon(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int base = rand.nextInt((int)maxLon);
		double randDouble = ((double)base)+rand.nextDouble();
		return randDouble%(maxLon-minLon)+minLon;
	}
	
}
