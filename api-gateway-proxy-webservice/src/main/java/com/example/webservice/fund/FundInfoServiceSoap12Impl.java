
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.example.webservice.fund;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:52:00.829+08:00
 * Generated source version: 3.4.2
 */

@WebService(
        serviceName = "FundInfoService",
        portName = "FundInfoServiceSoap12",
        targetNamespace = "http://tempuri.org/",
        wsdlLocation = "classpath:wsdl/FundInfoService.xml",
        endpointInterface = "com.example.webservice.fund.FundInfoServiceSoap")

public class FundInfoServiceSoap12Impl implements FundInfoServiceSoap {

    private static final Logger LOG = Logger.getLogger(FundInfoServiceSoap12Impl.class.getName());

    /* (non-Javadoc)
     * @see com.example.webservice.fund.FundInfoServiceSoap#searchContractsByProjectcode(java.lang.String projectNUMBER, java.lang.String masterNO)*
     */
    public String searchContractsByProjectcode(String projectNUMBER, String masterNO) {
        LOG.info("Executing operation searchContractsByProjectcode");
        System.out.println(projectNUMBER);
        System.out.println(masterNO);
        try {
            String _return = String.format("projectNUMBER:[%s], masterNO:[%s]", projectNUMBER, masterNO);
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.example.webservice.fund.FundInfoServiceSoap#helloWorld()*
     */
    public String helloWorld() {
        LOG.info("Executing operation helloWorld");
        try {
            String _return = "helloWorld";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.example.webservice.fund.FundInfoServiceSoap#fundInfoConfirm(java.lang.String projectNUMBER, java.lang.String fundSTATUS)*
     */
    public String fundInfoConfirm(String projectNUMBER, String fundSTATUS) {
        LOG.info("Executing operation fundInfoConfirm");
        System.out.println(projectNUMBER);
        System.out.println(fundSTATUS);
        try {
            String _return = String.format("projectNUMBER:[%s], fundSTATUS:[%s]", projectNUMBER, fundSTATUS);
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
