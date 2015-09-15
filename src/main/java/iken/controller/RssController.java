package iken.controller;

import iken.domain.Article;
import iken.domain.RssService;
import iken.domain.RssServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ken on 15/8/27.
 */

@Controller
public class RssController extends RestfulController {

    /**
     * List articles.  GET /articles
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ResponseEntity<String> listArticles(ModelMap model) {

        JSONObject jsonResponse = new JSONObject();
        RssService rssService = new RssServiceImpl();
        try {
            List<Article> articles = rssService.getArticles();

            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_SUCCESS);
            jsonResponse.put(RESPONSE_DATA, new JSONArray(articles.toArray()));
            System.out.println("Article List=" + articles);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.ACCEPTED);
    }


    /**
     * Fecture aritlces from RSSsite and save to Database
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public ResponseEntity<String> catchArticles(ModelMap model, @RequestBody String siteId) {
        JSONObject jsonResponse = new JSONObject();
        RssService rssService = new RssServiceImpl();

        try {
            int articleCount = rssService.catchArticles(siteId);

            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_SUCCESS);
            jsonResponse.put(RESPONSE_DATA, new JSONObject("{ArticleCount:" + articleCount + "}"));
            System.out.println("Article count=" + articleCount);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.ACCEPTED);

    }
}