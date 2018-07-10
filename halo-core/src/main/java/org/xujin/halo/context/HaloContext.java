package org.xujin.halo.context;

import org.xujin.halo.exception.BizException;

/**
 * 租户的上下文
 */
public class HaloContext {

   private static ThreadLocal<Tenant> tenantContext = new ThreadLocal<>();

   private static class Tenant{
       String tenantId;
       String bizCode;
       private Tenant(String tenantId, String bizCode) {
           this.tenantId = tenantId;
           this.bizCode = bizCode;
       }
   }

   public static boolean exist(){
       return null != tenantContext.get();
   }

   public static String getTenantId() {
       if (tenantContext.get() == null || tenantContext.get().tenantId == null) {
           throw new BizException("No tenantId in Context");
       }
       return tenantContext.get().tenantId;
   }

   public static String getBizCode() {
       if (tenantContext.get() == null || tenantContext.get().bizCode == null) {
           throw new BizException("No bizCode in Context");
       }
       return tenantContext.get().bizCode;
   }

   public static void set(String tenantId, String bizCode) {
       Tenant tenant = new Tenant(tenantId, bizCode);
       tenantContext.set(tenant);
   }

   public static void remove() {
       tenantContext.remove();
   }

}

