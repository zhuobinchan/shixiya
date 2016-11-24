package com.dayee.wintalent.service.v8;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2016-09-21T12:51:55.093+08:00
 * Generated source version: 3.1.7
 * 
 */
@WebServiceClient(name = "externalResumeService", 
                  wsdlLocation = "http://api.wintalent.cn/wt/xwebservices/externalResumeService?wsdl",
                  targetNamespace = "http://v8.service.wintalent.dayee.com") 
public class ExternalResumeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://v8.service.wintalent.dayee.com", "externalResumeService");
    public final static QName ExternalResumeServiceHttpPort = new QName("http://v8.service.wintalent.dayee.com", "externalResumeServiceHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://api.wintalent.cn/wt/xwebservices/externalResumeService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ExternalResumeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://api.wintalent.cn/wt/xwebservices/externalResumeService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ExternalResumeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ExternalResumeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ExternalResumeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ExternalResumeService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ExternalResumeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ExternalResumeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ExternalResumeServicePortType
     */
    @WebEndpoint(name = "externalResumeServiceHttpPort")
    public ExternalResumeServicePortType getExternalResumeServiceHttpPort() {
        return super.getPort(ExternalResumeServiceHttpPort, ExternalResumeServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExternalResumeServicePortType
     */
    @WebEndpoint(name = "externalResumeServiceHttpPort")
    public ExternalResumeServicePortType getExternalResumeServiceHttpPort(WebServiceFeature... features) {
        return super.getPort(ExternalResumeServiceHttpPort, ExternalResumeServicePortType.class, features);
    }

}
