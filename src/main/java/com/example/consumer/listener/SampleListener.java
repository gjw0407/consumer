package com.example.consumer.listener;

import com.example.consumer.dao.ChartDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleListener {

    @Autowired
    ChartDao chartDao;

    private static final Logger log = LoggerFactory.getLogger(SampleListener.class);
    @RabbitListener(queues = "sample.queue")
    public void receiveMessage(final Message message) {

        String res = new String(message.getBody());
        log.info(message.toString());
        log.info(res);

        //TODO Python API
        //result = PythonAPI(message)
        // keyword, 1: $1, 2: $2, 3: $3, 4: $4, 5: $5

        //TODO AOP 적용
        //binaryChartDao.addChartData(new BinaryChartDao(result.data, result.date));
    }
}
