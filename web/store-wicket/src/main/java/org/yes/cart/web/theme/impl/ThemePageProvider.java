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

package org.yes.cart.web.theme.impl;

import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.util.ClassProvider;
import org.apache.wicket.util.IProvider;
import org.springframework.util.Assert;
import org.yes.cart.web.application.ApplicationDirector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: denispavlov
 * Date: 13-03-21
 * Time: 10:28 AM
 */
public class ThemePageProvider extends ClassProvider<IRequestablePage> {

    private final Map<String, IProvider<Class<IRequestablePage>>> pages = new HashMap<String, IProvider<Class<IRequestablePage>>>();

    public ThemePageProvider(final Map<String, Class<IRequestablePage>> pages) {
        super(null);

        Assert.notEmpty(pages, "Must have pages mapping");
        Assert.isTrue(pages.containsKey("default"), "Must have mapping for default theme");

        for (final Map.Entry<String, Class<IRequestablePage>> entry : pages.entrySet()) {
            this.pages.put(entry.getKey(), ClassProvider.of(entry.getValue()));
        }
    }

    /** {@inheritDoc} */
    public Class<IRequestablePage> get() {
        List<String> themeChain = ApplicationDirector.getCurrentThemeChain();
        for (final String theme : themeChain) {
            if (this.pages.containsKey(theme)) {
                return this.pages.get(theme).get();
            }
        }
        return this.pages.get("default").get();
    }
}
