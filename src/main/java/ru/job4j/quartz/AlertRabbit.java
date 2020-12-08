package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static void main(String[] args)  {
        try (Connection connection = initConnection()) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            Properties properties = getProperties("rabbit.properties");
            JobDataMap data = new JobDataMap();
            data.put("connect", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(properties.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static Connection initConnection() throws Exception {
        Connection connection;
        Properties properties = getProperties("rabbit.properties");
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        return connection;
    }

    public static Properties getProperties(String name) {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream(name)) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static class Rabbit implements Job {

        @Override
        public void execute(JobExecutionContext context)  {
            Connection connect = ((Connection) context.getJobDetail()
                    .getJobDataMap().get("connect"));
            try (PreparedStatement statement = connect.prepareStatement(
                    "insert into rabbit (created) values (?)")) {
                statement.setDate(1, new Date(System.currentTimeMillis()));
                statement.execute();
            } catch (Exception e) {
                System.out.println("Ошибка в execute");
            }
        }
    }
}