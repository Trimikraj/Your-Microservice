package your.microservice.core.controllers.system;

import org.springframework.security.core.Authentication;
import your.microservice.core.system.SystemInstanceStatusService;
import your.microservice.core.system.messaging.model.YourMSBulletinBroadcastNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * YourSystemBulletinController
 *
 * Provides a method for the Front-End Cloud Application
 * to obtain the current System related
 * Your Microservice Bulletin Status for Front-End  to perform
 * interrogation on that status to determine Front-End
 * behaviour.
 *
 * @author jeff.a.schenk@gmail.com on 9/12/15.
 */
@RestController
@RequestMapping("/api/{serviceName}/{version}/system/bulletin")
public class YourSystemBulletinController {
    /**
     * Logging
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(YourSystemBulletinController.class);
    /**
     * SystemInstanceStatusService Reference.
     */
    @Autowired
    private SystemInstanceStatusService systemInstanceStatusService;
    /**
     * Constants
     */
    public static final String APPLICATION_JSON_WITH_UTF8_ENCODING_VALUE = "application/json;charset=UTF-8";

    /**
     * getBulletin
     * Obtain Current System Bulletin.
     *
     * @param authentication Reference to Authenticated Entity.
     * @param serviceName Service Name Associated to this Instance.
     * @param version API Version
     * @return YourMSBulletinBroadcastNotification Current System Bulletin Information of current Cloud State for Consumer.
     */
    @RequestMapping(
            value = {""},
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_WITH_UTF8_ENCODING_VALUE
    )
    @ResponseBody
    public YourMSBulletinBroadcastNotification getBulletin(Authentication authentication, @PathVariable String serviceName,
                                                           @PathVariable String version) {
        /**
         * Log and return Bulletin
         */
        LOGGER.info("Your Microservice {} {} Bulletin sent to Consumer:[{}] ==> {} ",
                    serviceName, version, authentication.getName(),
                    systemInstanceStatusService.getCurrentSystemInstanceStatusBulletin().getYourBulletin());
        return systemInstanceStatusService.getCurrentSystemInstanceStatusBulletin();
    }
}
