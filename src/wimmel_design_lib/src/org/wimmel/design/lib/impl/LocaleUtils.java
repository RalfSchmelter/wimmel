/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib.impl;

import java.util.Locale;
import java.util.Map;

/**
 * Holds some utility methods for locales.
 * 
 * @author Ralf Schmelter
 */
public class LocaleUtils {

    /**
     * Returns a localized value from the map. If every attempt failed (which happens only if the map
     * is empty), <code>null</code> is returned.
     * 
     * @param <T> The type of the value in the map.
     * @param locale The locale.
     * @param map The map.
     * @return The localized value.
     */
    public static <T> T getFromLocaleMap(Locale locale, Map<Locale, T> map) {
        if (map.containsKey(locale)) {
            return map.get(locale);
        }
        
        if (locale.getVariant() != null) {
            Locale noVariantLocale = new Locale(locale.getLanguage(), locale.getCountry());
            
            if (map.containsKey(noVariantLocale)) {
                return map.get(noVariantLocale);
            }
        }
        
        if (locale.getCountry() != null) {
            Locale noCountryLocale = new Locale(locale.getLanguage());
            
            if (map.containsKey(noCountryLocale)) {
                return map.get(noCountryLocale);
            }
        }
        
        // Try english first.
        Locale englishLocale = new Locale("en");
        
        if (map.containsKey(englishLocale)) {
            return map.get(englishLocale);
        }
        
        // Now try any random locale.
        if (!map.isEmpty()) {
            return map.values().iterator().next();
        }
        
        return null;
    }
}
