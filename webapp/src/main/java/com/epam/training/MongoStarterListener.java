package com.epam.training;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Uncompleted implementation.
 * <p/>
 * May be used this only for test purposes! Configuration from https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo.
 * <p/>
 * Now isn't used. All data saved in memory.
 *
 * @author vkrasovsky
 */
//@WebListener
public class MongoStarterListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(Run.class);

    private MongodExecutable mongodExecutable;
    private static final int MONGO_PORT = 27017;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            MongodStarter starter = MongodStarter.getDefaultInstance();
            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(MONGO_PORT, Network.localhostIsIPv6()))
                    .build();

            mongodExecutable = starter.prepare(mongodConfig);
            MongodProcess mongod = mongodExecutable.start();
            LOGGER.debug("----------------------------------------------------------");
            LOGGER.debug("mongo process id: " + mongod.getProcessId());
            LOGGER.debug("----------------------------------------------------------");

        } catch (Exception e) {
            if (mongodExecutable != null)
                mongodExecutable.stop();
            throw new RuntimeException("Mongo isn't started correctly", e.getCause());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (mongodExecutable != null)
            mongodExecutable.stop();
    }
}