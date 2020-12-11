package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse  implements Parse {
    public static void main(String[] args) throws Exception {
        for (int j = 1; j < 6; j++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + j).get();
            Elements row = doc.select(".postslisttopic");
            Elements date = doc.select(".altCol");
            Elements newDate = new Elements();
            for (int i = 0; i < date.size(); i++) {
                if (i % 2 != 0) {
                    newDate.add(date.get(i));
                }
            }
            for (int i = 0; i < row.size(); i++) {
                System.out.println(row.get(i).child(0).attr("href"));
                System.out.println(row.get(i).text());
                System.out.println(newDate.get(i).text());
            }
        }
    }

    public static Post getPost(String url) throws IOException, ParseException {
        Post post;
        String text;
        LocalDateTime date;
        Document doc = Jsoup.connect(url).get();
        Elements texts = doc.select(".msgBody");
        text = texts.get(1).text();
        Elements dates = doc.select(".msgFooter");
        List<TextNode> q = dates.get(0).textNodes();
        String s = q.get(0).text();
        s = s.substring(0, s.lastIndexOf(" "));
        DateTimeUtils dtu = new DateTimeUtils();
        date = dtu.getLocalDateTime(s);
        post = new Post(text, date);
        return post;
    }

    @Override
    public List<Post> list(String link) throws IOException, ParseException {
        List<Post> list = new ArrayList<>();
        Document doc = Jsoup.connect(link).get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            list.add(detail(td.child(0).attr("href")));
        }
        return list;
    }

    @Override
    public Post detail(String link) throws IOException, ParseException {
        return getPost(link);
    }
}