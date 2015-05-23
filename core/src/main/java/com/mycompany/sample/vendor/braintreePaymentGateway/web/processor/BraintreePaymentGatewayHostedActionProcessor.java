package com.mycompany.sample.vendor.braintreePaymentGateway.web.processor;

import com.mycompany.sample.vendor.braintree.service.payment.BraintreePaymentGatewayConstants;
import com.mycompany.sample.vendor.nullPaymentGateway.service.payment.NullPaymentGatewayConstants;
import org.broadleafcommerce.common.payment.dto.PaymentRequestDTO;
import org.broadleafcommerce.common.payment.dto.PaymentResponseDTO;
import org.broadleafcommerce.common.payment.service.PaymentGatewayHostedService;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.springframework.stereotype.Component;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
import org.thymeleaf.standard.expression.StandardExpressionProcessor;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by phuonghqh on 5/20/15.
 */
@Component("blBraintreePaymentGatewayHostedActionProcessor")
public class BraintreePaymentGatewayHostedActionProcessor extends AbstractAttributeModifierAttrProcessor {

  @Resource(name = "blBraintreePaymentGatewayHostedService")
  private PaymentGatewayHostedService paymentGatewayHostedService;

  /**
   * Sets the name of this processor to be used in Thymeleaf template
   */
  public BraintreePaymentGatewayHostedActionProcessor() {
    super("braintree_payment_hosted_action");
  }

  @Override
  public int getPrecedence() {
    return 10000;
  }

  @Override
  protected Map<String, String> getModifiedAttributeValues(Arguments arguments, Element element, String attributeName) {
    Map<String, String> attrs = new HashMap<String, String>();

    PaymentRequestDTO requestDTO = (PaymentRequestDTO) StandardExpressionProcessor.processExpression(arguments, element.getAttributeValue(attributeName));
    String url = "";

    if (requestDTO != null) {
      if (element.getAttributeValue("complete_checkout") != null) {
        Boolean completeCheckout = (Boolean) StandardExpressionProcessor.processExpression(arguments,
          element.getAttributeValue("complete_checkout"));
        element.removeAttribute("complete_checkout");
        requestDTO.completeCheckoutOnCallback(completeCheckout);
      }

      try {
        PaymentResponseDTO responseDTO = paymentGatewayHostedService.requestHostedEndpoint(requestDTO);
        url = responseDTO.getResponseMap().get(NullPaymentGatewayConstants.HOSTED_REDIRECT_URL).toString();
      }
      catch (PaymentException e) {
        throw new RuntimeException("Unable to Create Null Payment Gateway Hosted Link", e);
      }
    }

    attrs.put("action", url);
    return attrs;
  }

  @Override
  protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName, String newAttributeName) {
    return ModificationType.SUBSTITUTION;
  }

  @Override
  protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName, String newAttributeName) {
    return true;
  }

  @Override
  protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element, String attributeName) {
    return false;
  }
}
