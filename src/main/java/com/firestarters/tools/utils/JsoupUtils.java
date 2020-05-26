package com.firestarters.tools.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {
    public static String extractElementAttributeFromHtml(String html, String selector, String attributeKey) {
        Document page = Jsoup.parse(html);
        Element pageElement = page.select(selector).first();
        return pageElement.attr(attributeKey);
    }

    public static Elements extractElementsAttributesFromHtml(String html, String selector) {
        Document page = Jsoup.parse(html);
        return page.select(selector);
    }
    public static String extractElementTextFromHtml(String html, String selector) {
        Document page = Jsoup.parse(html);
        Element pageElement = page.select(selector).first();
        return pageElement.text();
    }
}
