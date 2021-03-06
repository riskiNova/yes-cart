/*
 * Copyright 2009 Denys Pavlov, Igor Azarnyi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.web.page.component.product;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.yes.cart.domain.misc.Pair;
import org.yes.cart.web.page.component.BaseComponent;

import java.util.List;

/**
 * Igor Azarny iazarny@yahoo.com
 * Date: 16-Sep-2011
 * Time: 13:10:19
 */
public class SkuAttributesSectionView extends BaseComponent {

    // ------------------------------------- MARKUP IDs BEGIN ---------------------------------- //
    private final static String HEAD = "head";
    private final static String KEY_VALUES_LIST = "keyValues";
    private final static String KEY = "key";
    private final static String VALUE = "value";
    // ------------------------------------- MARKUP IDs BEGIN ---------------------------------- //

    private final Pair<String, List<Pair<String, String>>> headKeyValues;


    /**
     * Construct head , key- values section.
     * @param id component id
     * @param headKeyValues pair of head and key-values list
     */
    public SkuAttributesSectionView(final String id, Pair<String, List<Pair<String, String>>> headKeyValues) {
        super(id);
        this.headKeyValues = headKeyValues;
    }

    /**
     *
     * Some times need additional format for value (keyValue.getSecond()).
     *
     */
    @Override
    protected void onBeforeRender() {

        add(
                new Label(HEAD, headKeyValues.getFirst())
        );

        add(
                new ListView<Pair<String, String>>(KEY_VALUES_LIST, headKeyValues.getSecond()) {
                    protected void populateItem(ListItem<Pair<String, String>> pairListItem) {
                        final Pair<String, String> keyValue = pairListItem.getModelObject();
                        pairListItem.add(
                                new Label(KEY, keyValue.getFirst())
                        ).add(
                                new Label(VALUE, StringUtils.defaultIfEmpty(keyValue.getSecond(), StringUtils.EMPTY) )
                        );
                    }
                }
        );
        super.onBeforeRender();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isVisible() {
        return (headKeyValues != null)
                &&
               (headKeyValues.getSecond() != null)
                &&
               (!headKeyValues.getSecond().isEmpty());
    }


}
