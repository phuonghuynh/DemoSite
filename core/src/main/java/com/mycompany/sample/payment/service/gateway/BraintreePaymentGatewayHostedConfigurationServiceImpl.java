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
@Service("blBraintreePaymentGatewayHostedConfigurationService")
public class BraintreePaymentGatewayHostedConfigurationServiceImpl implements PaymentGatewayConfigurationService {

  @Resource(name = "blBraintreePaymentGatewayHostedConfiguration")
  protected BraintreePaymentGatewayHostedConfigurationImpl configuration;

  @Resource(name = "blBraintreePaymentGatewayHostedRollbackService")
  protected PaymentGatewayRollbackService rollbackService;

  @Resource(name = "blBraintreePaymentGatewayHostedService")
  protected PaymentGatewayHostedService hostedService;

  @Resource(name = "blBraintreePaymentGatewayHostedTransactionConfirmationService")
  protected PaymentGatewayTransactionConfirmationService transactionConfirmationService;

  @Resource(name = "blBraintreePaymentGatewayHostedWebResponseService")
  protected PaymentGatewayWebResponseService webResponseService;

  public PaymentGatewayConfiguration getConfiguration() {
    return configuration;
  }

  public PaymentGatewayTransactionService getTransactionService() {
    return null;
  }

  public PaymentGatewayTransactionConfirmationService getTransactionConfirmationService() {
    return transactionConfirmationService;
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
    return hostedService;
  }

  public PaymentGatewayRollbackService getRollbackService() {
    return rollbackService;
  }

  public PaymentGatewayWebResponseService getWebResponseService() {
    return webResponseService;
  }

  public PaymentGatewayTransparentRedirectService getTransparentRedirectService() {
    return null;
  }

  public TRCreditCardExtensionHandler getCreditCardExtensionHandler() {
    return null;
  }

  public PaymentGatewayFieldExtensionHandler getFieldExtensionHandler() {
    return null;
  }

  public CreditCardTypesExtensionHandler getCreditCardTypesExtensionHandler() {
    return null;
  }
}
