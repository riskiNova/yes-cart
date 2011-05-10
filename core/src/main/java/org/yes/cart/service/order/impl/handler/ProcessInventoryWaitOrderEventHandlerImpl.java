package org.yes.cart.service.order.impl.handler;

import org.yes.cart.domain.entity.CustomerOrderDelivery;
import org.yes.cart.service.order.OrderEvent;
import org.yes.cart.service.order.OrderEventHandler;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class ProcessInventoryWaitOrderEventHandlerImpl implements OrderEventHandler {

    /**
     * {@inheritDoc}
     */
    public boolean handle(final OrderEvent orderEvent) {
        orderEvent.getCustomerOrderDelivery().setDeliveryStatus(CustomerOrderDelivery.DELIVERY_STATUS_INVENTORY_WAIT);
        return true;
    }

}