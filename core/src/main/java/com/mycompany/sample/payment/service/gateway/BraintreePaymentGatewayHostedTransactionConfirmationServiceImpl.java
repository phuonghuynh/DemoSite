package com.mycompany.sample.payment.service.gateway;

import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.common.payment.PaymentTransactionType;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.payment.dto.PaymentRequestDTO;
import org.broadleafcommerce.common.payment.dto.PaymentResponseDTO;
import org.broadleafcommerce.common.payment.service.PaymentGatewayTransactionConfirmationService;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayHostedTransactionConfirmationService")
public class BraintreePaymentGatewayHostedTransactionConfirmationServiceImpl implements PaymentGatewayTransactionConfirmationService {
  protected static final Log LOG = LogFactory.getLog(BraintreePaymentGatewayHostedTransactionConfirmationServiceImpl.class);

  @Resource(name = "blBraintreePaymentGatewayHostedConfiguration")
  protected BraintreePaymentGatewayHostedConfigurationImpl configuration;

  @Override
  public PaymentResponseDTO confirmTransaction(PaymentRequestDTO paymentRequestDTO) throws PaymentException {
    if (LOG.isTraceEnabled()) {
      LOG.trace("Null Payment Hosted Gateway - Confirming Transaction with amount: " + paymentRequestDTO.getTransactionTotal());
    }

    PaymentTransactionType type = PaymentTransactionType.AUTHORIZE_AND_CAPTURE;
    if (!configuration.isPerformAuthorizeAndCapture()) {
      type = PaymentTransactionType.AUTHORIZE;
    }

    return new PaymentResponseDTO(PaymentType.THIRD_PARTY_ACCOUNT,
      BraintreePaymentGatewayType.BRAINTREE_HOSTED_GATEWAY)
      .rawResponse("confirmation - successful")
      .successful(true)
      .paymentTransactionType(type)
      .amount(new Money(paymentRequestDTO.getTransactionTotal()));
  }
}
