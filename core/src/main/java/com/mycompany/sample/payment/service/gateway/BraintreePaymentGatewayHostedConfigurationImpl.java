package com.mycompany.sample.payment.service.gateway;

import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayType;
import org.broadleafcommerce.common.payment.PaymentGatewayType;
import org.springframework.stereotype.Service;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayHostedConfiguration")
public class BraintreePaymentGatewayHostedConfigurationImpl implements NullPaymentGatewayHostedConfiguration {

  protected int failureReportingThreshold = 1;

  protected boolean performAuthorizeAndCapture = true;

  @Override
  public String getHostedRedirectUrl() {
    return "/hosted/braintree-checkout";
  }

  @Override
  public String getHostedRedirectReturnUrl() {
    return "/braintree-checkout/hosted/return";
  }

  @Override
  public boolean isPerformAuthorizeAndCapture() {
//    return false;
    return true;
  }

  @Override
  public void setPerformAuthorizeAndCapture(boolean performAuthorizeAndCapture) {
    this.performAuthorizeAndCapture = performAuthorizeAndCapture;
  }

  @Override
  public int getFailureReportingThreshold() {
    return failureReportingThreshold;
  }

  @Override
  public void setFailureReportingThreshold(int failureReportingThreshold) {
    this.failureReportingThreshold = failureReportingThreshold;
  }

  @Override
  public boolean handlesAuthorize() {
    return true;
  }

  @Override
  public boolean handlesCapture() {
    return false;
  }

  @Override
  public boolean handlesAuthorizeAndCapture() {
    return true;
  }

  @Override
  public boolean handlesReverseAuthorize() {
    return false;
  }

  @Override
  public boolean handlesVoid() {
    return false;
  }

  @Override
  public boolean handlesRefund() {
    return false;
  }

  @Override
  public boolean handlesPartialCapture() {
    return false;
  }

  @Override
  public boolean handlesMultipleShipment() {
    return false;
  }

  @Override
  public boolean handlesRecurringPayment() {
    return false;
  }

  @Override
  public boolean handlesSavedCustomerPayment() {
    return false;
  }

  @Override
  public boolean handlesMultiplePayments() {
    return false;
  }

  @Override
  public PaymentGatewayType getGatewayType() {
    return BraintreePaymentGatewayType.BRAINTREE_HOSTED_GATEWAY;
  }
}
