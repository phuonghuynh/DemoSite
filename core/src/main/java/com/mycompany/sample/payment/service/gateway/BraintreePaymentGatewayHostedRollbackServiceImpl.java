package com.mycompany.sample.payment.service.gateway;

import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.common.payment.PaymentTransactionType;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.payment.dto.PaymentRequestDTO;
import org.broadleafcommerce.common.payment.dto.PaymentResponseDTO;
import org.broadleafcommerce.common.payment.service.PaymentGatewayRollbackService;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.springframework.stereotype.Service;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayHostedRollbackService")
public class BraintreePaymentGatewayHostedRollbackServiceImpl  implements PaymentGatewayRollbackService {

  protected static final Log LOG = LogFactory.getLog(BraintreePaymentGatewayHostedRollbackServiceImpl.class);

  @Override
  public PaymentResponseDTO rollbackAuthorize(PaymentRequestDTO transactionToBeRolledBack) throws PaymentException {
    if (LOG.isTraceEnabled()) {
      LOG.trace("Null Payment Hosted Gateway - Rolling back authorize transaction with amount: " + transactionToBeRolledBack.getTransactionTotal());
    }

    return new PaymentResponseDTO(PaymentType.THIRD_PARTY_ACCOUNT,
      BraintreePaymentGatewayType.BRAINTREE_HOSTED_GATEWAY)
      .rawResponse("rollback authorize - successful")
      .successful(true)
      .paymentTransactionType(PaymentTransactionType.REVERSE_AUTH)
      .amount(new Money(transactionToBeRolledBack.getTransactionTotal()));

  }

  @Override
  public PaymentResponseDTO rollbackCapture(PaymentRequestDTO transactionToBeRolledBack) throws PaymentException {
    throw new PaymentException("The Rollback Capture method is not supported for this module");
  }

  @Override
  public PaymentResponseDTO rollbackAuthorizeAndCapture(PaymentRequestDTO transactionToBeRolledBack) throws PaymentException {
    if (LOG.isTraceEnabled()) {
      LOG.trace("Null Payment Hosted Gateway - Rolling back authorize and capture transaction with amount: " + transactionToBeRolledBack.getTransactionTotal());
    }

    return new PaymentResponseDTO(PaymentType.THIRD_PARTY_ACCOUNT,
      BraintreePaymentGatewayType.BRAINTREE_HOSTED_GATEWAY)
      .rawResponse("rollback authorize and capture - successful")
      .successful(true)
      .paymentTransactionType(PaymentTransactionType.VOID)
      .amount(new Money(transactionToBeRolledBack.getTransactionTotal()));
  }

  @Override
  public PaymentResponseDTO rollbackRefund(PaymentRequestDTO transactionToBeRolledBack) throws PaymentException {
    throw new PaymentException("The Rollback Refund method is not supported for this module");
  }
}
