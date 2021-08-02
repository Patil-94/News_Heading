package com.blz.selenium.pages;

import com.blz.selenium.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class News_Heading_Page extends BaseClass {

    public static List<String> headlines = new ArrayList();
    public static List<Integer> points = new ArrayList<>();
    public static Map<String, Integer> newsPointMap = new HashMap<>();

    @FindBy(xpath = "//a[@class = 'storylink']")
    List<WebElement> Title;

    @FindBy(xpath = "//span[@class = 'score']")
    List<WebElement> Points;

    public News_Heading_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void news_headings() {
        //used for loop for getting news in the console
        for (WebElement webElementTitle : Title) {
            System.out.println(webElementTitle.getText());
            headlines.add(webElementTitle.getText());
        }

        //used for loop for getting points in the console
        for (WebElement webElementPoints : Points) {
            System.out.println(webElementPoints.getText().substring(0, webElementPoints.getText().length() - 7));
            points.add(Integer.parseInt(webElementPoints.getText().substring(0, webElementPoints.getText().length() - 7)));
        }

        Iterator<String> newsIterator = headlines.iterator();
        Iterator<Integer> scorePointIterator = points.iterator();
        //used while loop so that if one news get completed it takes to next news
        while (newsIterator.hasNext() && scorePointIterator.hasNext()) {
            newsPointMap.put(newsIterator.next(), scorePointIterator.next());
        }

       // each news should get print using entrySet
        newsPointMap.entrySet().stream().forEach(System.out::println);

        //created wordlist which will get all the news headlines
        List<String> wordList = listOfWords(headlines);
        wordList.stream().forEach(System.out::println);

        // find words having maximum count using map
        Map<String, Integer> wordCountMap = findMaxCountWord(wordList);
        String countWordInMap = getMaxCountWordInMap(wordCountMap);

        System.out.println("Word which repeating More Times:" + countWordInMap);
        String popularNewsHeading = getPopularNewsAmongAll(newsPointMap, countWordInMap);

        System.out.println(popularNewsHeading);


    }

    //Created private method for getting popular news among all
    private String getPopularNewsAmongAll(Map<String, Integer> newsHeaderMap, String countWordInMap) {
        int value = 0;
        String mostPopular="";
        for (Map.Entry<String, Integer> val : newsHeaderMap.entrySet()) {
            if (val.getKey().contains(countWordInMap )) {
                if (val.getValue() > value) {
                    value = val.getValue();
                    mostPopular = val.getKey();
                }
            }
        }
        System.out.println("Most popular  DATA:"+mostPopular);
        return mostPopular;
    }


    //Created private method which will take max count word
    private String getMaxCountWordInMap(Map<String, Integer> wordCountMap) {
        String key = "";
        Integer value = 0;

        for (Map.Entry<String, Integer> val : wordCountMap.entrySet()) {
            if (val.getValue() > value) {
                value = val.getValue();
                key = val.getKey();
            }
            System.out.println("Word " + val.getKey() + " "
                    + "repeated"
                    + ": " + val.getValue() + " times");
        }
        return key;
    }

    //Created private method find word having maximum count
    private Map<String, Integer> findMaxCountWord(List<String> wordList) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String i : wordList) {
            Integer j = wordMap.get(i);
            wordMap.put(i, (j == null) ? 1 : j + 1);
        }
        wordMap.entrySet().stream().forEach(System.out::println);
        return wordMap;
    }

    //Created method which will take list of all words
        private List<String> listOfWords(List<String> headlines) {
            List<String> words = new ArrayList<>();

            for (String s1 : headlines) {
                String[] arrOfWords = s1.split(" ");
                List<String> l1 = Arrays.asList(arrOfWords);
                words.addAll(l1);

            }
            return words;
        }
    }

