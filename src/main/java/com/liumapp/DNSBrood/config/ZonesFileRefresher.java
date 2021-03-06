package com.liumapp.DNSBrood.config;

import com.liumapp.DNSBrood.record.Manager;
import com.liumapp.DNSQueen.worker.ready.StandReadyWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liumapp on 7/15/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class ZonesFileRefresher extends StandReadyWorker implements InitializingBean {

    @Autowired
    private Configure configure;

    @Autowired
    private ZonesFileLoader zonesFileLoader;

    @Resource(name = "AddZoneManager")
    private Manager addZoneManager;

    @Resource(name = "DelZoneManager")
    private Manager delZoneManager;

    @Resource(name = "MultyDelManager")
    private Manager multyDelZoneManager;

    @Resource(name = "UpdateZoneManager")
    private Manager updateZoneManager;

    @Resource(name = "SelectZoneManager")
    private Manager selectZoneManager;

    private ScheduledExecutorService scheduledExecutorService = Executors
            .newScheduledThreadPool(1);

    private long lastFileModifiedTime;

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {

        File zonesFile = new File(Configure.getZonesFilename());
        lastFileModifiedTime = zonesFile.lastModified();

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {

            public void run() {
                File zonesFile = new File(Configure.getZonesFilename());
                // When two files' last modify time not equal, we consider it is
                // changed.
                synchronized (this) {
                    if (zonesFile.lastModified() != lastFileModifiedTime) {
                        lastFileModifiedTime = zonesFile.lastModified();
                        zonesFileLoader.reload();
                    }
                }
            }

        }, 500, 500, TimeUnit.MILLISECONDS);

    }

    @Override
    public String doWhatYouShouldDo(String whatQueenSays) {

        if (StringUtils.startsWithIgnoreCase(whatQueenSays, configure.getAddZonesIp())) {

            return addZoneManager.handle(whatQueenSays);

        } else if (StringUtils.startsWithIgnoreCase(whatQueenSays, configure.getDeleteZonesIp())) {

            if (whatQueenSays.contains(":")) {
                return delZoneManager.handle(whatQueenSays);
            } else {
                return multyDelZoneManager.handle(whatQueenSays);
            }

        } else if (StringUtils.startsWithIgnoreCase(whatQueenSays , configure.getUpdateZonesIp())) {

            return updateZoneManager.handle(whatQueenSays);

        } else if (StringUtils.startsWithIgnoreCase(whatQueenSays , configure.getSelectZonesIp())) {

            return selectZoneManager.handle(whatQueenSays);

        }

        return null;

    }


}
