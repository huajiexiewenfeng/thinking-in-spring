package com.huajie.thinking.in.spring.configuration.metadata;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UsersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UsersBeanDefinitionParser());
    }

    private static class UsersBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

        @Override
        protected Class<?> getBeanClass(Element element) {
            return User.class;
        }

        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            setPropertyValue("id", element, builder);
            setPropertyValue("name", element, builder);
            setPropertyValue("city", element, builder);
        }

        private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder) {
            String value = element.getAttribute(attributeName);
            if (StringUtils.hasText(value)) {
                builder.addPropertyValue(attributeName, value);
            }
        }

    }

}
