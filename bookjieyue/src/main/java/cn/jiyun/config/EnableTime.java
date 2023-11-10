package cn.jiyun.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EnableTime {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void beginOk() {
        System.out.println("\n >>>>>>>>>>>  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS")
                .format(new Date()));
    }
}
