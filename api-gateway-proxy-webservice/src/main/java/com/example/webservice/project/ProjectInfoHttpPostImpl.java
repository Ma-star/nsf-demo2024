
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.example.webservice.project;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.4.2
 * 2021-03-04T14:52:39.916+08:00
 * Generated source version: 3.4.2
 *
 */

@WebService(
                      serviceName = "ProjectInfo",
                      portName = "ProjectInfoHttpPost",
                      targetNamespace = "http://tempuri.org/",
                      wsdlLocation = "classpath:wsdl/ProjectInfo.xml",
                      endpointInterface = "com.example.webservice.project.ProjectInfoHttpPost")

public class ProjectInfoHttpPostImpl implements ProjectInfoHttpPost {

    private static final Logger LOG = Logger.getLogger(ProjectInfoHttpPostImpl.class.getName());

    /* (non-Javadoc)
     * @see com.example.webservice.project.ProjectInfoHttpPost#getProjectName(java.lang.String projectID)*
     */
    public String getProjectName(String projectID) {
        LOG.info("Executing operation getProjectName");
        System.out.println(projectID);
        try {
            String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.example.webservice.project.ProjectInfoHttpPost#getProjectCode(java.lang.String projectID)*
     */
    public String getProjectCode(String projectID) {
        LOG.info("Executing operation getProjectCode");
        System.out.println(projectID);
        try {
            String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
