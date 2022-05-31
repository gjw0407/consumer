package com.example.consumer.listener;

import com.example.consumer.dao.BinaryChartDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SampleListener {
    @Autowired
    BinaryChartDao binaryChartDao;

    private static final Logger log = LoggerFactory.getLogger(SampleListener.class);
    @RabbitListener(queues = "sample.queue")
    public void receiveMessage(final Message message) {

        String res = new String(message.getBody());
        log.info(message.toString());
        log.info(res);

        //TODO Python API
        //result = PythonAPI(message)

        //TODO AOP 적용
        //binaryChartDao.addChartData(new BinaryChartDao(result.data, result.date));
    }
}
