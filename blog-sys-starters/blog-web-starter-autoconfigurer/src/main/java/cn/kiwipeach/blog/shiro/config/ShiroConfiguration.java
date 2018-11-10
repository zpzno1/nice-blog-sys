/*
 * Copyright 2018 kiwipeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.blog.shiro.config;

import cn.kiwipeach.blog.shiro.factory.ResourceFilterMapFactory;
import cn.kiwipeach.blog.shiro.realam.CustomShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.Collection;
import java.util.Map;

/**
 * shiro权限验证组件配置思路:
 * 1) 配置shiro的核心拦截器DelegatingFilterProxy
 * 2) 配置shiro与拦截器有关联的ShiroFilterFactoryBean,
 * 3) 配置shiro的核心DefaultWebSecurityManager
 * 4) 配置自定义Realm，包括密码比对器
 * 5) 配置shiro缓存
 * 6) 配置记住我功能
 * 7) 启用shiro注解
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/05
 */
@Configuration/*声明这是配置类*/
@ConditionalOnWebApplication /*声明在web应用中才生效*/
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    //region 1.配置shiro的核心拦截器DelegatingFilterProxy
    /**
     * 核心:shiroFilter拦截器,ioc容器中的
     *
     * @return filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
    //endregion

    //region 2.配置shiro与拦截器有关联的ShiroFilterFactoryBean,
    /**
     * 核心:ShiroFilterFactoryBean 必须和拦截器DelegatingFilterProxy中的gargetBeanName保持一致
     * @param securityManager shiro安全管理器
     * @param resourceFilterMapFactory shiro权限规则加载实例工厂
     * @return shiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    @ConfigurationProperties(prefix = "blog.shiro.shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Autowired DefaultWebSecurityManager securityManager,
            @Autowired ResourceFilterMapFactory resourceFilterMapFactory) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //加载权限规则
        Map<String, String> filterChainDefinitionMap = resourceFilterMapFactory.loadResourceAccessRules();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    /**
     * shiro验证规则实例工厂
     *
     * @return resourceFilterMapFactory
     */
    @Bean("resourceFilterMapFactory")
    public ResourceFilterMapFactory resourceFilterMapFactory() {
        return new ResourceFilterMapFactory();
    }
    //endregion

    //region 3.配置shiro的核心DefaultWebSecurityManager
    /**
     * shiro安装管理器
     * @param cacheManager 缓存管理器
     * @param realmCollection 认证授权Realm
     * @param cookieRememberMeManager cookie管理器
     * @return securityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(
            @Autowired EhCacheManager cacheManager,
            @Autowired Collection<Realm> realmCollection,
            @Autowired CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //注入缓存
        defaultWebSecurityManager.setCacheManager(cacheManager);
        //注入认证授权Realm
        defaultWebSecurityManager.setRealms(realmCollection);
        //注入记住我
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        return defaultWebSecurityManager;
    }
    //endregion

    //region 4.配置自定义Realm
    /**
     * 自定义的shiro控制器,包括密码比对器
     *
     * @param credentialsMatcher 密码比对器
     * @return customShiroRealm
     */
    @Bean("customShiroRealm")
    public CustomShiroRealm customShiroRealm(CredentialsMatcher credentialsMatcher) {
        return new CustomShiroRealm(credentialsMatcher);
    }

    /**
     * shiro密码比对器
     *
     * @return credentialsMatcher
     */
    @Bean
    @ConfigurationProperties(prefix = "blog.shiro.credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new HashedCredentialsMatcher();
    }
    //endregion

    //region 5.配置shiro缓存
    /**
     * 配置shiro缓存管理器
     *
     * @return cacheManager
     */
    @ConfigurationProperties(prefix = "blog.shiro.cacheManager")
    @Bean
    public EhCacheManager cacheManager() {
        return new EhCacheManager();
    }
    //endregion

    //region 6.配置记住我功能
    /**
     * shiro cookie
     *
     * @return cookie
     */
    @ConfigurationProperties(prefix = "blog.shiro.cookie")
    @Bean
    public Cookie shiroCookie() {
        return new SimpleCookie();
    }

    /**
     * shiro cookie 管理器
     *
     * @return cookieRememberMeManager
     */
    @Bean
    @ConfigurationProperties(prefix = "blog.shiro.cookieRememberMeManager")
    public CookieRememberMeManager cookieRememberMeManager(@Autowired Cookie cookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }
    //endregion

    //region 7.启用shiro注解
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreatora = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreatora.setProxyTargetClass(true);
        return advisorAutoProxyCreatora;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }
    //endregion
}
