package ru.stqa.pft.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        BBHLocation geoIP =  new GeoIP().getGeoIPSoap12().getUserLocation("192.168.0.67");
        assertEquals(geoIP.getCountryCode(), "US");
    }

    @Test
    public void testInvalidIp() {
        BBHLocation geoIP =  new GeoIP().getGeoIPSoap12().getUserLocation("192.168.0.xx");
        assertEquals(geoIP.getCountryCode(), "US");
    }
}
