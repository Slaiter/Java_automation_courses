package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class SoapIssueTests extends TestBase {

    @Test
    public void testOpenIssue() throws MalformedURLException, ServiceException, RemoteException {
        String number = "1";
        skipIfNotFixed(Integer.parseInt(number));
        Issue read = app.soap().readIssue(Integer.parseInt(number));
        Assert.assertEquals(read.getId(), Integer.parseInt(number));
    }

    @Test
    public void testCloseIssue() throws MalformedURLException, ServiceException, RemoteException {
        String number = "2";
        skipIfNotFixed(Integer.parseInt(number));
        Issue read = app.soap().readIssue(Integer.parseInt(number));
        Assert.assertEquals(read.getId(), Integer.parseInt(number));
    }
}