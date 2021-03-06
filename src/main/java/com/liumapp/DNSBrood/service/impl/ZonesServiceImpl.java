package com.liumapp.DNSBrood.service.impl;

import com.liumapp.DNSBrood.dao.ZonesDAO;
import com.liumapp.DNSBrood.model.Zones;
import com.liumapp.DNSBrood.service.ZonesService;
import org.springframework.stereotype.Service;
import org.xbill.DNS.Zone;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liumapp on 7/19/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Service
public class ZonesServiceImpl implements ZonesService {

    @Resource
    private ZonesDAO zonesDAO;

    /**
     * add a new zone
     * @param zone
     * @return
     */
    @Override
    public int addZones(Zones zone) {

        return zonesDAO.insert(zone);

    }

    @Override
    public int updateZones(Zones zone) {
        return zonesDAO.update(zone);
    }

    /**
     * is a zone exist , judging from domain
     * @param zone
     * @return
     */
    @Override
    public boolean isExist(Zones zone) {

        return zonesDAO.getByDomain(zone.getDomain()) != null;

    }

    @Override
    public Zones getZone(String domain) {
        return zonesDAO.getByDomain(domain);
    }

    @Override
    public List<Zones> getZonesByUserNumber(String userNumber) {
        return zonesDAO.getByUserNumber(userNumber);
    }

    @Override
    public List<Zones> findAll() {
        return zonesDAO.findAll();
    }

    @Override
    public Zones getVerifiedZone(String domain, String userNumber) {
        return zonesDAO.getVerified(domain , userNumber);
    }

    @Override
    public boolean deleteZone(Zones zone) {
        if (zonesDAO.verifyDelete(zone) == 1) {
            return true;
        } else {
            return false;
        }
    }

}
