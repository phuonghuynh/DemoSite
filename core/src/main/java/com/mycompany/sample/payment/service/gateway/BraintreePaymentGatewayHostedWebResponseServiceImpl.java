package com.mycompany.sample.payment.service.gateway;

import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayType;
import com.mycompany.sample.vendor.nullPaymentGateway.service.payment.NullPaymentGatewayConstants;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.common.payment.PaymentTransactionType;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.payment.dto.PaymentResponseDTO;
import org.broadleafcommerce.common.payment.service.PaymentGatewayWebResponsePrintService;
import org.broadleafcommerce.common.payment.service.PaymentGatewayWebResponseService;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Service("blBraintreePaymentGatewayHostedWebResponseService")
public class BraintreePaymentGatewayHostedWebResponseServiceImpl implements PaymentGatewayWebResponseService {

  @Resource(name = "blPaymentGatewayWebResponsePrintService")
  protected PaymentGatewayWebResponsePrintService webResponsePrintService;

  @Override
  public PaymentResponseDTO translateWebResponse(HttpServletRequest request) throws PaymentException {
    PaymentResponseDTO responseDTO = new PaymentResponseDTO(PaymentType.THIRD_PARTY_ACCOUNT,
      BraintreePaymentGatewayType.BRAINTREE_HOSTED_GATEWAY)
      .rawResponse(webResponsePrintService.printRequest(request));

    Map<String,String[]> paramMap = request.getParameterMap();

    Money amount = Money.ZERO;
    if (paramMap.containsKey(NullPaymentGatewayConstants.TRANSACTION_AMT)) {
      String amt = paramMap.get(NullPaymentGatewayConstants.TRANSACTION_AMT)[0];
      amount = new Money(amt);
    }

    responseDTO.successful(true)
      .completeCheckoutOnCallback(Boolean.parseBoolean(paramMap.get(NullPaymentGatewayConstants.COMPLETE_CHECKOUT_ON_CALLBACK)[0]))
      .amount(amount)
      .paymentTransactionType(PaymentTransactionType.UNCONFIRMED)
      .orderId(paramMap.get(NullPaymentGatewayConstants.ORDER_ID)[0])
      .responseMap(NullPaymentGatewayConstants.RESULT_MESSAGE,
        paramMap.get(NullPaymentGatewayConstants.RESULT_MESSAGE)[0]);

    return responseDTO;
  }

}
