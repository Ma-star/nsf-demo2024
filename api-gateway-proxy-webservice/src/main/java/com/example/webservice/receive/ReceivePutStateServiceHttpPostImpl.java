
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.example.webservice.receive;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:53:04.204+08:00
 * Generated source version: 3.4.2
 *
 */

@WebService(
                      serviceName = "ReceivePutStateService",
                      portName = "ReceivePutStateServiceHttpPost",
                      targetNamespace = "http://tempuri.org/",
                      wsdlLocation = "classpath:wsdl/ReceivePutStateService.xml",
                      endpointInterface = "com.example.webservice.receive.ReceivePutStateServiceHttpPost")

public class ReceivePutStateServiceHttpPostImpl implements ReceivePutStateServiceHttpPost {

    private static final Logger LOG = Logger.getLogger(ReceivePutStateServiceHttpPostImpl.class.getName());

    /* (non-Javadoc)
     * @see com.example.webservice.receive.ReceivePutStateServiceHttpPost#receiveState(java.lang.String launchPayBillNo, java.lang.String confirmLaunchDate, java.lang.String launchStatus)*
     */
    public String receiveState(String launchPayBillNo, String confirmLaunchDate, String launchStatus) {
        LOG.info("Executing operation receiveState");
        System.out.println(launchPayBillNo);
        System.out.println(confirmLaunchDate);
        System.out.println(launchStatus);
        try {
            String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
