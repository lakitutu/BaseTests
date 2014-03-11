package com.prkw.utils

import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by SG0210921 on 10.03.14.
 */
class CustomFieldsHelperTest {

    @Test
    public void  customFieldsXmlStyle(){
        String input = """
                        <div class="control-group item" data-named-object-id="51286">
                        \t<div class="drag-handle">
                        \t\t<span/>
                        \t</div>
                        \t<label class="control-label">
                        \t\t<h4>xxx1&nbsp;<span>(Single Line Text)</span>
                        \t\t</h4>
                        \t</label>
                        \t<div class="controls">
                        \t\t<div class="btn-toolbar">
                        \t\t\t<button class="btn btn-mini edit" href="#">Edit</button>
                        \t\t\t<a href="#" class="remove destroy">
                        \t\t\t\t<i class="icon-trash"/>
                        \t\t\t</a>
                        \t\t\t<label class="filterable">
                        \t\t\t\t<input data-toggle-filterable="" type="checkbox" checked="">
                                    Filterable
                                  </input>
                        \t\t\t</label>
                        \t\t\t<span class="sort">
                        \t\t\t\t<a class="move-down" href="#">
                        \t\t\t\t\t<i class="icon-arrow-down"/>
                        \t\t\t\t</a>
                        \t\t\t</span>
                        \t\t</div>
                        \t</div>
                        </div>

                    """
        def map = CustomFieldsHelper.getCustomFieldsNamesTypesFromCustomFieldsUrl(input)
        Assert.assertEquals(map.size(),1)
        Assert.assertEquals(map.get("xxx1"),"Single Line Text")
    }

    @Test
    public void  customFieldHtmlStyle(){
        String input = """
                    <div class="control-group item" data-named-object-id="51286">
                        <div class="drag-handle">
                          <span><hr><hr></span>
                        </div>
                      <label class="control-label">
                        <h4>xxx1&nbsp;<span>(Single Line Text)</span></h4>
                      </label>
                      <div class="controls">
                        <div class="btn-toolbar">
                          <button class="btn btn-mini edit" href="#">Edit</button>
                          <a href="#" class="remove destroy"><i class="icon-trash"></i></a>
                            <label class="filterable">
                              <input data-toggle-filterable="" type="checkbox" checked="">
                                Filterable
                            </label>
                          <span class="sort">
                                <a class="move-down" href="#"><i class="icon-arrow-down"></i></a>
                          </span>
                        </div>
                      </div>
                    </div>

                    """
        def map = CustomFieldsHelper.getCustomFieldsNamesTypesFromCustomFieldsUrl(input)
        Assert.assertEquals(map.size(),1)
        Assert.assertEquals(map.get("xxx1"),"Single Line Text")
    }

    @Test
    public void  leadsNew(){
        String input = """
                       <div class="custom-fields-items"><div><div class="control-group">
                          <label class="control-label">xxx1</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge" value="" name="custom_fields[xxx1]">
                          </div>
                        </div>
                        </div><div><div class="control-group">
                          <label class="control-label">xxx2</label>
                          <div class="controls">
                            <textarea class="input-xlarge" name="custom_fields[xxx2]"></textarea>
                          </div>
                        </div>
                        </div><div><div class="control-group">
                          <label class="control-label">xxx3</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge" value="" name="custom_fields[xxx3]">
                          </div>
                        </div>
                        </div><div><div class="control-group">
                          <label class="control-label">testField1</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge" value="" name="custom_fields[testField1]">
                          </div>
                        </div>
                        </div><div><div class="control-group">
                          <label class="control-label">Test Field 2</label>
                          <div class="controls">
                            <input type="checkbox" name="custom_fields[Test Field 2]">
                          </div>
                        </div>
                        </div><div><div class="control-group">
                          <label class="control-label"> tstfield003</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge" value="" name="custom_fields[ tstfield003]">
                          </div>
                        </div>
                        </div><div><div class="control-group">
                          <label class="control-label">dsfdsf</label>
                          <div class="controls">
                            <input type="text" class="input-xlarge" value="" name="custom_fields[dsfdsf]">
                          </div>
                        </div>
                        </div></div>
                    """
        def set = CustomFieldsHelper.getCustomFieldsNamesFromLeadsNewUrl(input)
        Assert.assertEquals(set.size(),7)
    }
}
