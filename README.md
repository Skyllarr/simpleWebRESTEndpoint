WildFly 15 Configuration
--

 1. Create an *elytron properties realm* "DemoPropsRealm":

        /subsystem=elytron/properties-realm=DemoPropsRealm:add(groups-attribute=groups,\
            groups-properties={path=demo-roles.properties,relative-to=jboss.server.config.dir},\
            users-properties={path=demo-users.properties,relative-to=jboss.server.config.dir,plain-text=true})
 
 2. Create an *elytron security domain* "DemoDomain" related to "DemoPropsRealm":
    
        /subsystem=elytron/security-domain=DemoDomain:add(realms=[{realm=DemoPropsRealm,role-decoder=groups-to-roles}],\
            default-realm=DemoPropsRealm,permission-mapper=default-permission-mapper)
    
 3. Create an *elytron http authentication* factory related to "DemoDomain":
 
        /subsystem=elytron/http-authentication-factory=demo-http-auth:add(\
            http-server-mechanism-factory=global,\
            security-domain=DemoDomain,\
            mechanism-configurations=[\
                {mechanism-name=BASIC, mechanism-realm-configurations=[{realm-name=DemoApplicationDomain}]}])
  
 4. Map an *ejb3 subsystem* application security domain to our DemoDomain:
           
        /subsystem=ejb3/application-security-domain=DemoApplicationDomain:add(security-domain=DemoDomain)
    
 5. Link an *undertow subsystem* application security domain to our http-authentication-factory:
 
        /subsystem=undertow/application-security-domain=DemoApplicationDomain:add(http-authentication-factory=demo-http-auth)


