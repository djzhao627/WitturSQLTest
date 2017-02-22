package com.djzhao.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * 测试。
 * 
 * @author djzhao
 * @time 2017年2月22日 下午12:32:17
 */
public class App {

	@Test
	public void testDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		String formatToday = sdf.format(today);
		String formatTomorrow = sdf.format(tomorrow);
		
		System.out.println(formatToday);
		System.out.println(formatTomorrow);
	}
	
}
