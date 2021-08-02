package com.blz.selenium.test;

import com.blz.selenium.base.BaseClass;
import com.blz.selenium.pages.News_Heading_Page;
import org.testng.annotations.Test;

public class NewsHeading_Test extends BaseClass {

    @Test
    public void news_headings_test() throws InterruptedException {
        News_Heading_Page news_page = new News_Heading_Page(driver);
        news_page.news_headings();
    }
}
