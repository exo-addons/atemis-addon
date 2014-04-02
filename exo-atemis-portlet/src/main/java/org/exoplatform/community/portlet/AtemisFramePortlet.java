package org.exoplatform.community.portlet;

import javax.portlet.PortletPreferences;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;


@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/templates/AtemisFramePortlet.gtmpl")
public class AtemisFramePortlet extends UIPortletApplication {

	String url = "specialpages/exoplatform.aspx?control=%pageId%&user=%email%";
    String username = "";
    String atemisHostName = "";
    String atemisPageId = "";
    
	public AtemisFramePortlet() throws Exception {
		super();	
	}
    
	public  String getPortletPreference(String preferenceName) {
	    PortletRequestContext portletRequestContext = WebuiRequestContext.getCurrentInstance();
	    PortletPreferences preferences = portletRequestContext.getRequest().getPreferences();
	    return preferences.getValue(preferenceName, null);
	}
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String initUrl() throws Exception{
		// get the current email address
		String email = this.getCurrentUserEmail();
		// get pref hostname
		this.atemisHostName = this.getPortletPreference("atemis.host");
		this.atemisPageId = this.getPortletPreference("atemis.page.holidayRequest");
	
		// replace in the atemis URL
		this.url  = this.url.replace("%email%", email);	
		this.url  = this.url.replace("%pageId%", this.atemisPageId);
		
		return this.atemisHostName.concat(this.url);
	}
	
    private String getCurrentUserEmail() throws Exception{
        String email = "";
        String userName = Util.getPortalRequestContext().getRemoteUser();
        OrganizationService orgService_ = (OrganizationService) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(OrganizationService.class);
        UserHandler userHandler = orgService_.getUserHandler();
        
        try {
          User aUser = userHandler.findUserByName(userName);
          if(aUser != null){
            email = aUser.getEmail();
          }
        }catch(Exception e){ }
    	
        return email; 
    }
	
}
