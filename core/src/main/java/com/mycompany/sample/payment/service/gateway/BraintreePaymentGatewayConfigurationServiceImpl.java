package com.mycompany.sample.payment.service.gateway;

import org.broadleafcommerce.common.payment.service.*;
import org.broadleafcommerce.common.web.payment.expression.PaymentGatewayFieldExtensionHandler;
import org.broadleafcommerce.common.web.payment.processor.CreditCardTypesExtensionHandler;
import org.broadleafcommerce.common.web.payment.processor.TRCreditCardExtensionHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayConfigurationService")
public class BraintreePaymentGatewayConfigurationServiceImpl implements PaymentGatewayConfigurationService {

  @Resource(name = "blBraintreePaymentGatewayConfiguration")
  protected BraintreePaymentGatewayConfigurationImpl configuration;

  @Resource(name = "blBraintreePaymentGatewayRollbackService")
  protected PaymentGatewayRollbackService rollbackService;

  @Resource(name = "blBraintreePaymentGatewayWebResponseService")
  protected PaymentGatewayWebResponseService webResponseService;

  @Resource(name = "blBraintreePaymentGatewayTransparentRedirectService")
  protected PaymentGatewayTransparentRedirectService transparentRedirectService;

  @Resource(name = "blBraintreePaymentGatewayTransactionService")
  protected PaymentGatewayTransactionService transactionService;

  @Resource(name = "blBraintreePaymentGatewayTRExtensionHandler")
  protected TRCreditCardExtensionHandler creditCardExtensionHandler;

  @Resource(name = "blBraintreePaymentGatewayFieldExtensionHandler")
  protected PaymentGatewayFieldExtensionHandler fieldExtensionHandler;

  public PaymentGatewayConfiguration getConfiguration() {
    return configuration;
  }

  public PaymentGatewayTransactionConfirmationService getTransactionConfirmationService() {
    return null;
  }

  public PaymentGatewayReportingService getReportingService() {
    return null;
  }

  public PaymentGatewayCreditCardService getCreditCardService() {
    return null;
  }

  public PaymentGatewayCustomerService getCustomerService() {
    return null;
  }

  public PaymentGatewaySubscriptionService getSubscriptionService() {
    return null;
  }

  public PaymentGatewayFraudService getFraudService() {
    return null;
  }

  public PaymentGatewayHostedService getHostedService() {
    return null;
  }

  public PaymentGatewayRollbackService getRollbackService() {
    return rollbackService;
  }

  public PaymentGatewayWebResponseService getWebResponseService() {
    return webResponseService;
  }

  public PaymentGatewayTransparentRedirectService getTransparentRedirectService() {
    return transparentRedirectService;
  }

  public PaymentGatewayTransactionService getTransactionService() {
    return transactionService;
  }

  public TRCreditCardExtensionHandler getCreditCardExtensionHandler() {
    return creditCardExtensionHandler;
  }

  public PaymentGatewayFieldExtensionHandler getFieldExtensionHandler() {
    return fieldExtensionHandler;
  }

  public CreditCardTypesExtensionHandler getCreditCardTypesExtensionHandler() {
    return null;
  }
}
