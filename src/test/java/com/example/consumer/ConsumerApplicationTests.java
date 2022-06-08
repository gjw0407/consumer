package com.example.consumer;

import com.example.consumer.chartdata.ChartData;
import com.example.consumer.dao.ChartDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ConsumerApplicationTests {

	@Autowired
	private ChartDao chartDao;

	@Test
	void contextLoads() {
	}

	@Test
	void saveData() {
		ChartData chartData = new ChartData(1, 1, "A", "20220608", 5);
		ChartData chartData1 = new ChartData(2, 1, "B", "20220608", 2);
		ChartData chartData2 = new ChartData(3, 2, "A", "20220609", 14);
		ChartData chartData3 = new ChartData(4, 2, "B", "20220609", 8);

		System.out.println(chartDao.save(chartData));
		System.out.println(chartDao.save(chartData1));
		System.out.println(chartDao.save(chartData2));
		System.out.println(chartDao.save(chartData3));

		System.out.println(chartDao.findAll());
	}
}
