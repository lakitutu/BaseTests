package com.prkw.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


/**
 * Created by SG0210921 on 10.03.14.
 */
class CustomFieldsHelper {
    static final NAMES_TYPES_CUSTOM_FIELDS_SELECTOR = 'div[class=control-group item]>label[class=control-label]>h4'
    static final NAMES_LEADS_NEW_SELECTOR = 'div[class=custom-fields-items] div[class=control-group]>label[class=control-label]'
    static final CUSTOM_FIELDS_MATCHER = /(\w+).*\(([^)]+)\)/
    static final LEADS_NEW_MATCHER = /(\w+)/


    def public static Map<String,String> getCustomFieldsNamesTypesFromCustomFieldsUrl(String inputDocument){
        def resultsMap = getCustomFieldsNamesTypesFromDocument(inputDocument, NAMES_TYPES_CUSTOM_FIELDS_SELECTOR, CUSTOM_FIELDS_MATCHER)
        return resultsMap
    }

    def static Map<String,String> getCustomFieldsNamesTypesFromDocument(String inputDocument, String inputSelector, String inputMatcher){
        def resultsMap = [:]
        Document doc = Jsoup.parse(inputDocument)
        Elements controlGroups = doc.select(inputSelector)
        controlGroups.each{
            def linkText = it.text()
            def matcher = linkText =~ /$inputMatcher/
            if (matcher.size()>0){
                resultsMap.put(matcher[0][1], matcher[0][2])
            }
        }
        return resultsMap
    }

    def public static Set<String> getCustomFieldsNamesFromLeadsNewUrl(String inputDocument){
        getCustomFieldsNamesFromDocument(inputDocument, NAMES_LEADS_NEW_SELECTOR, LEADS_NEW_MATCHER)
    }

    def static Set<String> getCustomFieldsNamesFromDocument(String inputDocument, String inputSelector, String inputMatcher){
        def result = []
        Document doc = Jsoup.parse(inputDocument)
        Elements controlGroups = doc.select(inputSelector)
        controlGroups.each{
            def linkText = it.text()
            def matcher = linkText =~ /$inputMatcher/
            if (matcher.size()>0){
                result.add(matcher[0][1])
            }
        }
        return result as Set
    }
}
