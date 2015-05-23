package com.mycompany.sample.vendor.braintree.service.payment;

import org.broadleafcommerce.common.payment.PaymentGatewayType;
import org.springframework.stereotype.Component;

/**
 * Created by phuonghqh on 5/19/15.
 */
@Component
public class BraintreePaymentGatewayType extends PaymentGatewayType {

  public static final PaymentGatewayType BRAINTREE_GATEWAY  = new PaymentGatewayType("BRAINTREE_GATEWAY", "Braintree Payment Gateway Implementation");
  public static final PaymentGatewayType BRAINTREE_HOSTED_GATEWAY  = new PaymentGatewayType("BRAINTREE_HOSTED_GATEWAY", "Braintree Hosted Payment Gateway Implementation");


}
