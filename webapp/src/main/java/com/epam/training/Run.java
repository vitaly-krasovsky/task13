package com.epam.training;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * @author vkrasovsky
 */
public class Run {
    private static final Logger LOGGER = LogManager.getLogger(Run.class);

    private static User user1 = new User("Vitaly_Lenin@epam.com", "Vitaly", "Lenin", "1234567");
    private static User user2 = new User("Vitaly_Medvedev@epam.com", "Vitaly", "Medvedev", "09876");
    private static User user3 = new User("Vitaly_Putin@epam.com", "Vitaly", "Putin", "56783");
    private static User user1Updated = new User("Vitaly_Lenin@epam.com", "Vitaly_", "Lenin_", "1234567_");


    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        cleanUp(appClient, Lists.newArrayList("Vitaly_Lenin@epam.com", "Vitaly_Medvedev@epam.com", "Vitaly_Putin@epam.com"));

        LOGGER.debug(appClient.create(user1));
        LOGGER.debug(appClient.create(user2));
        LOGGER.debug(appClient.create(user3));

        LOGGER.debug(appClient.get("Vitaly_Lenin@epam.com"));
        LOGGER.debug(appClient.get("Vitaly_Medvedev@epam.com"));
        LOGGER.debug(appClient.get("Vitaly_Putin@epam.com"));

        LOGGER.debug(appClient.update(user1Updated));
        LOGGER.debug(appClient.get("Vitaly_Lenin@epam.com"));

        appClient.delete("Vitaly_Lenin@epam.com");
        LOGGER.debug(appClient.get("Vitaly_Lenin@epam.com"));

        LOGGER.debug(Arrays.toString(appClient.getAll().toArray()));

        appClient.uploadPhoto("Vitaly_Lenin@epam.com");
        appClient.downloadPhoto("Vitaly_Lenin@epam.com");
        //TODO: consider url: http://localhost:8080/webapi/users/Vitaly_Lenin@epam.com/downloadphoto or target folder
    }

    private static void cleanUp(AppClient appClient, List<String> ids) {
        for (String id : ids) {
            appClient.delete(id);
        }
    }
}
