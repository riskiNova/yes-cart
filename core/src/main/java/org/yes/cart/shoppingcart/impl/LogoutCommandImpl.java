package org.yes.cart.shoppingcart.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.yes.cart.domain.dto.ShoppingCart;
import org.yes.cart.shoppingcart.ShoppingCartCommand;

import java.util.Map;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class LogoutCommandImpl  implements ShoppingCartCommand {

    private static final long serialVersionUID = 20101025L;

    public static String CMD_KEY = "logoutCmd";

    /**
     * Execute command on shopping cart to perform changes.
     *
     * @param shoppingCart the shopping cart
     */
    public void execute(final ShoppingCart shoppingCart) {
        shoppingCart.setCustomerName(null);
        //TODO  shoppingCart.setAuthentication(null);
        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * @return command key
     */
    public String getCmdKey() {
        return CMD_KEY;
    }

    /**
     *
     * @param applicationContext application context
     * @param parameters page parameters
     */
    public LogoutCommandImpl(final ApplicationContext applicationContext, final Map parameters) {
        super();
    }

}