package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    private MantisConnectPortType getMantisConnectPortType() throws MalformedURLException, ServiceException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("http://localhost/mantisbt-1.3.4/api/soap/mantisconnect.php")));
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnectPortType();
        IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        if (issue.getStatus().toString() == "resolved") {
            return false;
        } else {
            return true;
        }
    }
}