package com.prkw.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


/**
 * Created by SG0210921 on 10.03.14.
 * Deprecated - this is funny how complicated code you may create without using selectors
 */
class CustomFieldsHelperDeprecated {
    static final CONTROL_GROUP_CLASS = 'control-group'
    static final CONTROL_LABEL_CLASS = 'control-label'
    static final LABEL_MATCHER = "(\\w+).*\\(([^)]+)\\)"

    def public static Map<String,String> getCustomFieldsFromDocument(String inputDocument){
        def resultsMap = [:]
        Document doc = Jsoup.parse(inputDocument)
        Elements controlGroups = doc.getElementsByClass(CONTROL_GROUP_CLASS)
        controlGroups.each{
            Elements controlLabels = it.getElementsByClass(CONTROL_LABEL_CLASS)
            controlLabels.each{
                Elements h4 = it.getElementsByTag("h4")
                h4.each{
                    String linkText = it.text()
                    def matcher = linkText =~ /$LABEL_MATCHER/
                    if (matcher.size()>0){
                        resultsMap.put(matcher[0][1], matcher[0][2])
                    }
                }
            }
        }
        return resultsMap
    }
}
