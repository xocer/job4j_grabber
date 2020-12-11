package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            String url = cfg.getProperty("jdbc.url");
            String username = cfg.getProperty("jdbc.username");
            String password = cfg.getProperty("jdbc.password");

            cnn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "insert into post(name, text, link, created) values (?, ?, ?, ?)")) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getUrl());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getDate()));
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "select * from post")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Post(
                            resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
        return list;
    }

    @Override
    public Post findById(String id) {
        Post result = null;
        try (PreparedStatement preparedStatement = cnn.prepareStatement(
                "select * from post where id = ?")) {
            int i = Integer.parseInt(id);
            preparedStatement.setInt(1, i);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = new Post(
                            resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ClassLoader loader = PsqlStore.class.getClassLoader();
        Properties properties = new Properties();
        InputStream io = loader.getResourceAsStream("rabbit.properties");
        properties.load(io);

        Post post1 = new Post("Название1", "Текст1", "Урл1", LocalDateTime.now());
        Post post2 = new Post("Название2", "Текст2", "Урл2", LocalDateTime.now());
        Post post3 = new Post("Название3", "Текст3", "Урл3", LocalDateTime.now());

        PsqlStore store = new PsqlStore(properties);
        store.save(post1);
        store.save(post2);
        store.save(post3);
        List<Post> list = store.getAll();
        Post result = store.findById("2");

        list.forEach(System.out::println);
        System.out.println();
        System.out.println(result);
    }
}
